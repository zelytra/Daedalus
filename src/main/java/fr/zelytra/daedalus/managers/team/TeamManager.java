package fr.zelytra.daedalus.managers.team;

import fr.zelytra.daedalus.objects.Team;

import java.util.ArrayList;

public class TeamManager {

    private ArrayList<Team> teamList;

    public TeamManager(){

        this.teamList = new ArrayList<>();
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }
}
