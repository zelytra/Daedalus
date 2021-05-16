package fr.zelytra.daedalus.managers.team;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamManager {

    private List<Team> teamList;
    private Scoreboard scoreboard;

    public TeamManager() {

        this.teamList = new ArrayList<>();
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        setupTeams();
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setupTeams() {

        teamList.add(new Team(TeamsEnum.RED, this.scoreboard));
        teamList.add(new Team(TeamsEnum.BLUE, this.scoreboard));
        teamList.add(new Team(TeamsEnum.GREEN, this.scoreboard));
        teamList.add(new Team(TeamsEnum.YELLOW, this.scoreboard));
        teamList.add(new Team(TeamsEnum.SPECTATOR, this.scoreboard));
        teamList.add(new Team(TeamsEnum.MINOS, this.scoreboard));

    }

    public Team getTeamOfEnum(TeamsEnum teamsEnum) {
        for (Team team : teamList) {
            if (team.getTeamEnum() == teamsEnum) {
                return team;
            }
        }
        return null;
    }

    public Team getTeamOfPlayer(UUID uuid) {
        for (Team team : getTeamList()) {
            if (team.getPlayerList().contains(uuid))
                return team;
        }
        return null;
    }

    public void removePlayerFromAnyTeam(UUID uuid) {
        for (Team team : getTeamList()) {
            if (team.getPlayerList().contains(uuid))
                team.removePlayer(uuid);
        }
    }

    public Team getSpectatorTeam(){
        for (Team team : getTeamList()) {
            if(team.getTeamEnum().equals(TeamsEnum.SPECTATOR)){
                return team;
            }
        }
        return null;
    }
}
