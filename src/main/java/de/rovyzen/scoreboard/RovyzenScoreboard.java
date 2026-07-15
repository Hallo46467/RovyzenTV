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
        String title = color(config.getString("scoreboard.title", "&5&lRovyzenTV"));
        Objective obj = board.registerNewObjective("rovyzen", "dummy", title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        int score = config.getStringList("scoreboard.lines").size();
        for (String line : config.getStringList("scoreboard.lines")) {
            line = line.replace("%players%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                    .replace("%player%", player.getName())
                    .replace("%ping%", String.valueOf(player.getPing()))
                    .replace("%rank%", "Spieler");
            obj.getScore(color(line)).setScore(score--);
        }

        player.setScoreboard(board);
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
