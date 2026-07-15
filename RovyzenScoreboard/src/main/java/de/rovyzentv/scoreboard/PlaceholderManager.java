package de.rovyzentv.scoreboard;

import org.bukkit.entity.Player;

public class PlaceholderManager {

    private final RovyzenScoreboard plugin;

    public PlaceholderManager(RovyzenScoreboard plugin) {
        this.plugin = plugin;
    }

    public String replace(Player player, String text) {
        text = text.replace("%player_name%", player.getName());
        text = text.replace("%world%", player.getWorld().getName());

        if (plugin.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            text = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, text);
        }

        return text.replace("&", "§");
    }
}
