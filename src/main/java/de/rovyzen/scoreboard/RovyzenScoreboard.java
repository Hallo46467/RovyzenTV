package de.rovyzen.scoreboard;

import de.rovyzen.scoreboard.config.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RovyzenScoreboard extends JavaPlugin {

    private static RovyzenScoreboard instance;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager(this);
        configManager.load();

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
}
