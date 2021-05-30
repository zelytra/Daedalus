package fr.zelytra.daedalus.managers.faction;

import fr.zelytra.daedalus.managers.game.time.TimeManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class FactionScoreBoard {
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final Faction faction;

    public FactionScoreBoard(Faction faction) {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective("ServerName", "dummy", "§6§lDAEDALUS");
        this.faction = faction;
        initialize();
    }

    public void update(TimeManager timeManager) {
        for (Player player : faction.getPlayerList())
            player.setScoreboard(scoreboard);


        scoreboard.getTeam("episode").setPrefix("Episode §6: §a" + timeManager.getEpisode());
        scoreboard.getTeam("timer").setPrefix("Timer §6: §a" + timeManager.getTimer());
        scoreboard.getTeam("alive").setPrefix("Folks §6: §a" + faction.getAliveCount() + "§6/§a" + faction.getPlayerList().size());
        if (faction.getGodsEnum() != null)
            scoreboard.getTeam("divinity").setPrefix(" §6• §b" + faction.getGodsEnum().getName());
        else
            scoreboard.getTeam("divinity").setPrefix(" §6• §cNone");

    }

    private void initialize() {

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        addLine("", "a", 13);

        addLine("Episode §6: §a1", "episode", 12);
        addLine("Timer §6: §a00:00", "timer", 11);

        addLine("", "b", 10);

        addLine("Folks §6: §a1§6/§a10", "alive", 9);

        addLine("", "c", 8);

        addLine("Team Divinity", "text", 7);
        addLine(" §6• §cNone", "divinity", 6);

        addLine("", "d", 5);

        addLine("§6• §lMINOTAUR§6 •", "textMino", 4);
        addLine("State §6: §aAlive", "stateMino", 3);

        addLine("", "e", 2);

        addLine("Border §6: §a600", "border", 1);


    }

    /**
     * @param content String content of the line
     * @param tag     Unique tag of the line
     * @param line    Line number
     */
    private void addLine(String content, String tag, int line) {
        Team team = scoreboard.registerNewTeam(tag);
        team.addEntry(tag);
        team.setPrefix(content);
        objective.getScore(tag).setScore(line);

    }

}
