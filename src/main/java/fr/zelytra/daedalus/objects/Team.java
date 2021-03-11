package fr.zelytra.daedalus.objects;

import fr.zelytra.daedalus.managers.team.TeamsEnum;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

import java.util.ArrayList;
import java.util.UUID;

public class Team {

    private ArrayList<UUID> playerList;
    private TeamsEnum team;

    public Team(TeamsEnum team){

        this.playerList = new ArrayList<>();
        this.team = team;

    }

    public void addPlayer(UUID uuid){
        if(!hasPlayer(uuid))
            getPlayerList().add(uuid);
    }

    public void removePlayer(UUID uuid){
        if(hasPlayer(uuid))
            getPlayerList().remove(uuid);
    }

    private boolean hasPlayer(UUID uuid){
        return getPlayerList().contains(uuid);
    }

    public ArrayList<UUID> getPlayerList() {
        return playerList;
    }

    public TeamsEnum getTeam() {
        return team;
    }

    public DyeColor getTeamColor(){
        return getTeam().getTeamColor();
    }

    public ChatColor getChatColor(){
        return getTeam().getChatColor();
    }
}
