package de.rovyzen.scoreboard.config;

import de.rovyzen.scoreboard.RovyzenScoreboard;

public class ConfigManager {

    private final RovyzenScoreboard plugin;

    public ConfigManager(RovyzenScoreboard plugin) {
        this.plugin = plugin;
    }

    public void load() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
    }
}
