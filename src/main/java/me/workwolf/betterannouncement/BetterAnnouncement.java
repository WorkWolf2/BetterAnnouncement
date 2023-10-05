package me.workwolf.betterannouncement;

import me.workwolf.betterannouncement.Commands.Annuncio;
import me.workwolf.betterannouncement.Commands.colore;
import me.workwolf.betterannouncement.Database.Database;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public final class BetterAnnouncement extends JavaPlugin {

    public Database sql;

    public Database getSql() {
        return sql;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Conn();
        getCommand("annuncio").setExecutor(new Annuncio(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void Conn() {
        File file = new File(getDataFolder() + "/database.db");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            sql = new Database(this, file.getAbsolutePath());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
