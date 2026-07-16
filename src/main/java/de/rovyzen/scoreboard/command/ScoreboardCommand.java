package de.rovyzen.scoreboard.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ScoreboardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("rovyzen.scoreboard")) {
            sender.sendMessage(ChatColor.RED + "Keine Berechtigung!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.GOLD + "RovyzenScoreboard v2.0");
            sender.sendMessage(ChatColor.GRAY + "/scoreboard reload");
            sender.sendMessage(ChatColor.GRAY + "/scoreboard toggle");
            return true;
        }

        return true;
    }
}
