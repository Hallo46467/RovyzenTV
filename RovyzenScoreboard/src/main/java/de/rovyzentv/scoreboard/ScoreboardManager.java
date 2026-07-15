package de.rovyzentv.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class ScoreboardManager {

    private final RovyzenScoreboard plugin;
    private final PlaceholderManager placeholders;

    public ScoreboardManager(RovyzenScoreboard plugin) {
        this.plugin = plugin;
        this.placeholders = new PlaceholderManager(plugin);
    }

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    update(player);
                }
            }
        }.runTaskTimer(plugin, 20L, plugin.getConfig().getInt("scoreboard.update-seconds") * 20L);
    }

    private void update(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = board.registerNewObjective("rovy", "dummy", color(plugin.getConfig().getString("scoreboard.title")));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        List<String> lines = plugin.getConfig().getStringList("scoreboard.lines");
        int score = lines.size();

        for (String line : lines) {
            objective.getScore(placeholders.replace(player, line) + " ".repeat(score)).setScore(score--);
        }

        player.setScoreboard(board);
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text == null ? "RovyzenTV" : text);
    }
}
