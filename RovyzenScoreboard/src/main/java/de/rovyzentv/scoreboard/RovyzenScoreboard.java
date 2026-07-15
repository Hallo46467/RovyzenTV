package de.rovyzentv.scoreboard;

import org.bukkit.plugin.java.JavaPlugin;

public class RovyzenScoreboard extends JavaPlugin {

    private static RovyzenScoreboard instance;
    private ScoreboardManager scoreboardManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        scoreboardManager = new ScoreboardManager(this);
        scoreboardManager.start();

        getLogger().info("RovyzenScoreboard wurde aktiviert!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RovyzenScoreboard wurde deaktiviert!");
    }

    public static RovyzenScoreboard getInstance() {
        return instance;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }
}
