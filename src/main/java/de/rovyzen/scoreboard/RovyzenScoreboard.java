package de.rovyzen.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class RovyzenScoreboard extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("RovyzenScoreboard aktiviert!");
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                createScoreboard(player);
            }
        }, 0L, 40L);
    }

    private void createScoreboard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("rovyzen", "dummy", ChatColor.DARK_PURPLE + "RovyzenTV");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore(ChatColor.GRAY + "------------").setScore(5);
        obj.getScore(ChatColor.WHITE + "Spieler: " + ChatColor.GREEN + Bukkit.getOnlinePlayers().size()).setScore(4);
        obj.getScore(ChatColor.WHITE + "Name: " + ChatColor.AQUA + player.getName()).setScore(3);
        obj.getScore(ChatColor.GOLD + "Rovyzen Network").setScore(2);
        obj.getScore(ChatColor.GRAY + "------------").setScore(1);

        player.setScoreboard(board);
    }
}
