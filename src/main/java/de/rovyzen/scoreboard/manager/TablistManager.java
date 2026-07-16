package de.rovyzen.scoreboard.manager;

import de.rovyzen.scoreboard.RovyzenScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TablistManager {

    private final RovyzenScoreboard plugin;

    public TablistManager(RovyzenScoreboard plugin) {
        this.plugin = plugin;
    }

    public void update(Player player) {
        if (!plugin.getConfig().getBoolean("tablist.enabled", true)) {
            return;
        }

        String header = plugin.getConfig().getString("tablist.header", "&6Rovyzen");
        String footer = plugin.getConfig().getString("tablist.footer", "&7Willkommen");

        player.setPlayerListHeaderFooter(
                color(header.replace("%player%", player.getName())),
                color(footer.replace("%player%", player.getName()))
        );
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
