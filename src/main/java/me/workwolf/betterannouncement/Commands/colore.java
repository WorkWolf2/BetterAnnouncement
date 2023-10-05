package me.workwolf.betterannouncement.Commands;

import de.themoep.minedown.MineDown;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class colore implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        player.spigot().sendMessage(new MineDown(args[0]).toComponent());
        return true;
    }
}
