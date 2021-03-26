package fr.zelytra.daedalus.managers.team;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeamManager {

    private HashMap<DyeColor,Team> teamList;
    private Scoreboard scoreboard;

    public TeamManager(){

        this.teamList = new HashMap<>();
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        setupTeams();
    }

    public HashMap<DyeColor,Team> getTeamList() {
        return teamList;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setupTeams(){

        teamList.put(DyeColor.RED, new Team(TeamsEnum.RED, this.scoreboard));
        teamList.put(DyeColor.BLUE, new Team(TeamsEnum.BLUE, this.scoreboard));
        teamList.put(DyeColor.GREEN, new Team(TeamsEnum.GREEN, this.scoreboard));
        teamList.put(DyeColor.YELLOW, new Team(TeamsEnum.YELLOW, this.scoreboard));
        teamList.put(DyeColor.GRAY, new Team(TeamsEnum.MINOS, this.scoreboard));

    }

    public Team getTeamOfColor(DyeColor color){
        return getTeamList().get(color);
    }

    public Team getTeamOfPlayer(UUID uuid){
        for(Map.Entry<DyeColor, Team> map : getTeamList().entrySet()){

            if(map.getValue().getPlayerList().contains(uuid))
                return map.getValue();

        }
        return null;
    }

    public void removePlayerFromAnyTeam(UUID uuid){

        for(Map.Entry<DyeColor, Team> map : getTeamList().entrySet()){

            if(map.getValue().getPlayerList().contains(uuid))
                map.getValue().removePlayer(uuid);

        }

    }

}
