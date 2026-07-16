package de.rovyzen.scoreboard.listener;

import de.rovyzen.scoreboard.manager.ScoreboardManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final ScoreboardManager scoreboardManager;

    public JoinListener(ScoreboardManager scoreboardManager) {
        this.scoreboardManager = scoreboardManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        scoreboardManager.update(event.getPlayer());
    }
}
