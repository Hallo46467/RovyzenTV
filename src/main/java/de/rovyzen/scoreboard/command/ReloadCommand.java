package de.rovyzen.scoreboard.command;

import de.rovyzen.scoreboard.RovyzenScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final RovyzenScoreboard plugin;

    public ReloadCommand(RovyzenScoreboard plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("rovyzen.reload")) {
            sender.sendMessage(ChatColor.RED + "Keine Berechtigung!");
            return true;
        }

        plugin.getConfigManager().load();
        sender.sendMessage(ChatColor.GREEN + "RovyzenScoreboard Config wurde neu geladen!");
        return true;
    }
}
