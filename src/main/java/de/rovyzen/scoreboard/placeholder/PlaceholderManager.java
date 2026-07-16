package de.rovyzen.scoreboard.placeholder;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlaceholderManager {

    public String replace(Player player, String text) {
        return text
                .replace("%player%", player.getName())
                .replace("%players%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("%ping%", String.valueOf(player.getPing()))
                .replace("%rank%", getRank(player));
    }

    private String getRank(Player player) {
        if (player.hasPermission("rovyzen.owner")) return "Owner";
        if (player.hasPermission("rovyzen.admin")) return "Admin";
        if (player.hasPermission("rovyzen.mod")) return "Moderator";
        return "Spieler";
    }
}
