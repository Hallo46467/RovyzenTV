package de.rovyzen.scoreboard;

import de.rovyzen.scoreboard.command.ReloadCommand;
import de.rovyzen.scoreboard.command.ScoreboardCommand;
import de.rovyzen.scoreboard.command.ToggleCommand;
import de.rovyzen.scoreboard.config.ConfigManager;
import de.rovyzen.scoreboard.listener.JoinListener;
import de.rovyzen.scoreboard.listener.QuitListener;
import de.rovyzen.scoreboard.manager.ScoreboardManager;
import de.rovyzen.scoreboard.manager.TablistManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RovyzenScoreboard extends JavaPlugin {

    private static RovyzenScoreboard instance;
    private ConfigManager configManager;
    private ScoreboardManager scoreboardManager;
    private TablistManager tablistManager;

    @Override
    public void onEnable() {
        instance = this;

        configManager = new ConfigManager(this);
        configManager.load();

        scoreboardManager = new ScoreboardManager(this);
        tablistManager = new TablistManager(this);

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);

        getCommand("scoreboard").setExecutor(new ScoreboardCommand());
        getCommand("scoreboardreload").setExecutor(new ReloadCommand(this));
        getCommand("toggle").setExecutor(new ToggleCommand());

        getLogger().info("RovyzenScoreboard v2.0 aktiviert!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RovyzenScoreboard v2.0 deaktiviert!");
    }

    public static RovyzenScoreboard getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public TablistManager getTablistManager() {
        return tablistManager;
    }
}
