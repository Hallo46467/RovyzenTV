package de.rovyzen.scoreboard.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ToggleCommand implements CommandExecutor {

    private static final Set<UUID> disabledPlayers = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl benutzen.");
            return true;
        }

        if (disabledPlayers.contains(player.getUniqueId())) {
            disabledPlayers.remove(player.getUniqueId());
            player.sendMessage(ChatColor.GREEN + "Scoreboard aktiviert.");
        } else {
            disabledPlayers.add(player.getUniqueId());
            player.sendMessage(ChatColor.RED + "Scoreboard deaktiviert.");
            player.setScoreboard(player.getServer().getScoreboardManager().getNewScoreboard());
        }

        return true;
    }

    public static boolean isDisabled(Player player) {
        return disabledPlayers.contains(player.getUniqueId());
    }
}
