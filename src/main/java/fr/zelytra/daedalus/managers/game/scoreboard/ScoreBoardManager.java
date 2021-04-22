package fr.zelytra.daedalus.managers.game.scoreboard;

import fr.zelytra.daedalus.managers.game.time.TimeManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreBoardManager {

    private final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    private final Objective obj = scoreboard.registerNewObjective("ServerName", "dummy", "Daedalus");
    private final TimeManager timeManager;

    public ScoreBoardManager(TimeManager timeManager){
        this.timeManager = timeManager;
        initialize();
    }

    private void initialize(){

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        addLine("§3Episode §7>> §r"+ timeManager.getEpisode(), "episode", 0);
        addLine("§cTimer §7>> §r"+timeManager.getTimer(), "timer", -1);
        addLine("", "§r", -2);
        addLine("Divinity §7>> §cnone", "god", -3);
        addLine("", "§r§r", -4);
        addLine("§aAlive §7>> §r1/1", "alive", -5);

        for(Player p : Bukkit.getOnlinePlayers())
            p.setScoreboard(scoreboard);

    }

    public void update(){
        for(Player p : Bukkit.getOnlinePlayers())
            p.setScoreboard(scoreboard);

        scoreboard.getTeam("episode").setPrefix("§3Episode §7>> §r"+ timeManager.getEpisode());
        scoreboard.getTeam("timer").setPrefix("§cTimer §7>> §r"+timeManager.getTimer());
        scoreboard.getTeam("alive").setPrefix("§aAlive §7>> §r1/1");
    }

    private void addLine(String line, String id, int score){

        Team moneyCounter = scoreboard.registerNewTeam(id);
        moneyCounter.addEntry(getID());
        moneyCounter.setPrefix(line);
        obj.getScore(getID()).setScore(score);

    }

    private String getID(){

        StringBuilder ID = new StringBuilder("§r");

        for(int i = 0; i < scoreboard.getTeams().size(); i++)
            ID.append("§r");

        return ID.toString();
    }
}

 