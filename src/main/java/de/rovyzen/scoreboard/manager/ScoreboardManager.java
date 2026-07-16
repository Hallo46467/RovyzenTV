package de.rovyzen.scoreboard.manager;

import de.rovyzen.scoreboard.RovyzenScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {

    private final RovyzenScoreboard plugin;

    public ScoreboardManager(RovyzenScoreboard plugin) {
        this.plugin = plugin;
    }

    public void update(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = board.registerNewObjective(
                "rovyzen",
                "dummy",
                color(plugin.getConfig().getString("scoreboard.title", "&6Rovyzen"))
        );

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        int score = plugin.getConfig().getStringList("scoreboard.lines").size();

        for (String line : plugin.getConfig().getStringList("scoreboard.lines")) {
            line = replacePlaceholders(player, line);
            objective.getScore(color(line)).setScore(score--);
        }

        player.setScoreboard(board);
    }

    public void updateAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            update(player);
        }
    }

    private String replacePlaceholders(Player player, String text) {
        return text
                .replace("%player%", player.getName())
                .replace("%players%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("%ping%", String.valueOf(player.getPing()));
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
