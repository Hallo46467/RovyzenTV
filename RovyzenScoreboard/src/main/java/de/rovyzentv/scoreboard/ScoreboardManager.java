package de.rovyzentv.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardManager {

    private final RovyzenScoreboard plugin;

    public ScoreboardManager(RovyzenScoreboard plugin) {
        this.plugin = plugin;
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
        // Scoreboard-System wird im nächsten Schritt ergänzt
    }
}
