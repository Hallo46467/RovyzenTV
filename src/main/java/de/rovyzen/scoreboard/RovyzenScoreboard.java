package de.rovyzen.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class RovyzenScoreboard extends JavaPlugin {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        getLogger().info("RovyzenScoreboard aktiviert!");

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                createScoreboard(player);
            }
        }, 0L, config.getLong("scoreboard.update-time", 40L));
    }

    private void createScoreboard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("rovyzen", "dummy", color(config.getString("scoreboard.title", "&5&lRovyzenTV")));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        int score = config.getStringList("scoreboard.lines").size();
        for (String line : config.getStringList("scoreboard.lines")) {
            line = line.replace("%players%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                    .replace("%player%", player.getName())
                    .replace("%ping%", String.valueOf(player.getPing()))
                    .replace("%rank%", getRank(player));
            line = applyPlaceholderAPI(player, line);
            obj.getScore(color(line)).setScore(score--);
        }

        player.setScoreboard(board);
    }

    private String applyPlaceholderAPI(Player player, String text) {
        try {
            Class<?> api = Class.forName("me.clip.placeholderapi.PlaceholderAPI");
            return (String) api.getMethod("setPlaceholders", Player.class, String.class)
                    .invoke(null, player, text);
        } catch (Exception ignored) {
            return text;
        }
    }

    private String getRank(Player player) {
        if (player.hasPermission("rovyzen.owner")) return "Owner";
        if (player.hasPermission("rovyzen.admin")) return "Admin";
        if (player.hasPermission("rovyzen.mod")) return "Moderator";
        return "Spieler";
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
