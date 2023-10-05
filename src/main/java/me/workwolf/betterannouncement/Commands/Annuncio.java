package me.workwolf.betterannouncement.Commands;

import de.themoep.minedown.MineDown;
import me.workwolf.betterannouncement.BetterAnnouncement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static java.lang.Long.parseLong;

public class Annuncio implements CommandExecutor {

    private final BetterAnnouncement plugin;

    public Annuncio(BetterAnnouncement plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender cs, Command command, String s, String[] args) {
        if (!(cs instanceof Player)) {
            return true;
        }

        Player player = (Player) cs;

        if (args.length < 1) {
            player.spigot().sendMessage(new MineDown("&x&f&b&7&3&1&0P&x&f&b&7&f&1&3A&x&f&b&8&a&1&7L&x&f&c&9&6&1&aL&x&f&c&a&2&1&eA&x&f&c&a&d&2&1D&x&f&c&b&9&2&5I&x&f&d&c&5&2&8U&x&f&d&d&0&2&cM&x&f&d&d&c&2&fS &7| &r&7 Inserisci il messaggio da inviare!").toComponent());
            return true;
        }

        List<String> playerListDb = new ArrayList<>(plugin.getSql().getAllPlayers());

        String messaggio = "";

        for (int i = 0; i < args.length; i++) {
            messaggio += args[i];

            if (i < args.length - 1) {
                messaggio += " ";
            }
        }


            if (player.hasPermission("betterannouncement.announce")) {
                if (!playerListDb.contains(String.valueOf(player.getUniqueId()))) {
                    plugin.getSql().addPlayer(String.valueOf(player.getUniqueId()), System.currentTimeMillis() / 1000);

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---»--*-------------------------------------*--«---"));
                    String finalMessaggio = messaggio;
                    Bukkit.getOnlinePlayers().forEach(playeR -> playeR.spigot().sendMessage(new MineDown(finalMessaggio).toComponent()));
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---»--*-------------------------------------*--«---"));


                } else {
                    if (player.hasPermission(plugin.getConfig().getString("groups.1.permission"))) {

                        long timeElapsed1 = parseLong(plugin.getConfig().getString("groups.1.time")) * 60 * 60 - plugin.getSql().getMs(player.getUniqueId());

                        if (timeElapsed1 >= parseLong(plugin.getConfig().getString("groups.1.time")) * 60 * 60) {

                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---»--*-------------------------------------*--«---"));
                            String finalMessaggio1 = messaggio;
                            Bukkit.getOnlinePlayers().forEach(playeR -> playeR.spigot().sendMessage(new MineDown(finalMessaggio1).toComponent()));
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---»--*-------------------------------------*--«---"));

                            try {
                                plugin.getSql().updateMs(player.getUniqueId(), System.currentTimeMillis());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }

                        } else {
                            long timeRemaining = parseLong(plugin.getConfig().getString("groups.1.time")) * 60 * 60 - (System.currentTimeMillis() / 1000 - plugin.getSql().getMs(player.getUniqueId()));

                            long hours = timeRemaining / 3600;
                            long minutes = (timeRemaining % 3600) / 60;
                            long seconds = timeRemaining % 60;

                            player.spigot().sendMessage(new MineDown("&x&f&b&7&3&1&0P&x&f&b&7&f&1&3A&x&f&b&8&a&1&7L&x&f&c&9&6&1&aL&x&f&c&a&2&1&eA&x&f&c&a&d&2&1D&x&f&c&b&9&2&5I&x&f&d&c&5&2&8U&x&f&d&d&0&2&cM&x&f&d&d&c&2&fS &7| &r&7 Devi ancora aspettare: " + String.valueOf(hours + " ore, " + minutes + " minuti, " + seconds + " secondi")).toComponent());
                        }
                    } else if (player.hasPermission(plugin.getConfig().getString("groups.2.permission"))) {

                        long timeElapsed2 = parseLong(plugin.getConfig().getString("groups.2.time")) * 60 * 60 - plugin.getSql().getMs(player.getUniqueId());

                        if (timeElapsed2 >= parseLong(plugin.getConfig().getString("groups.2.time")) * 60 * 60) {

                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---»--*-------------------------------------*--«---"));
                            String finalMessaggio2 = messaggio;
                            Bukkit.getOnlinePlayers().forEach(playeR -> playeR.spigot().sendMessage(new MineDown(finalMessaggio2).toComponent()));
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---»--*-------------------------------------*--«---"));

                            try {
                                plugin.getSql().updateMs(player.getUniqueId(), System.currentTimeMillis());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            long timeRemaining = parseLong(plugin.getConfig().getString("groups.2.time")) * 60 * 60 - (System.currentTimeMillis() / 1000 - plugin.getSql().getMs(player.getUniqueId()));

                            long hours = timeRemaining / 3600;
                            long minutes = (timeRemaining % 3600) / 60;
                            long seconds = timeRemaining % 60;

                            player.spigot().sendMessage(new MineDown("&x&f&b&7&3&1&0P&x&f&b&7&f&1&3A&x&f&b&8&a&1&7L&x&f&c&9&6&1&aL&x&f&c&a&2&1&eA&x&f&c&a&d&2&1D&x&f&c&b&9&2&5I&x&f&d&c&5&2&8U&x&f&d&d&0&2&cM&x&f&d&d&c&2&fS &7| &r&7 Devi ancora aspettare: " + String.valueOf(hours + " ore, " + minutes + " minuti, " + seconds + " secondi")).toComponent());
                        }
                    } else if (player.hasPermission(plugin.getConfig().getString("groups.3.permission"))) {

                        long timeElapsed3 = parseLong(plugin.getConfig().getString("groups.3.time")) * 60 * 60 - plugin.getSql().getMs(player.getUniqueId());

                        if (timeElapsed3 >= parseLong(plugin.getConfig().getString("groups.3.time")) * 60 * 60) {

                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---»--*-------------------------------------*--«---"));
                            String finalMessaggio3 = messaggio;
                            Bukkit.getOnlinePlayers().forEach(playeR -> playeR.spigot().sendMessage(new MineDown(finalMessaggio3).toComponent()));
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---»--*-------------------------------------*--«---"));

                            try {
                                plugin.getSql().updateMs(player.getUniqueId(), System.currentTimeMillis());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        } else {

                            long timeRemaining = parseLong(plugin.getConfig().getString("groups.3.time")) * 60 * 60 - (System.currentTimeMillis() / 1000 - plugin.getSql().getMs(player.getUniqueId()));

                            long hours = timeRemaining / 3600;
                            long minutes = (timeRemaining % 3600) / 60;
                            long seconds = timeRemaining % 60;

                            player.spigot().sendMessage(new MineDown("&x&f&b&7&3&1&0P&x&f&b&7&f&1&3A&x&f&b&8&a&1&7L&x&f&c&9&6&1&aL&x&f&c&a&2&1&eA&x&f&c&a&d&2&1D&x&f&c&b&9&2&5I&x&f&d&c&5&2&8U&x&f&d&d&0&2&cM&x&f&d&d&c&2&fS &7| &r&7 Devi ancora aspettare: " + String.valueOf(hours + " ore, " + minutes + " minuti, " + seconds + " secondi")).toComponent());
                        }
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "Non hai il permesso per eseguire questo comando");
            }
            return true;
        }
    }

