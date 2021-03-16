package fr.zelytra.daedalus.managers.game;

import fr.zelytra.daedalus.managers.team.TeamManager;

public class GameManager {

    private TeamManager tm;
    private GameStatesEnum state;

    public GameManager(){

        this.tm = new TeamManager();
        this.state = GameStatesEnum.WAIT;

    }

    public TeamManager getTeamManager() {
        return tm;
    }

    public GameStatesEnum getState() {
        return state;
    }

    public void setState(GameStatesEnum state) {
        this.state = state;
    }

    public boolean isWaiting(){
        return state == GameStatesEnum.WAIT;
    }

    public boolean isRunning(){
        return state == GameStatesEnum.RUNNING;
    }

    public boolean isFinished(){
        return state == GameStatesEnum.FINISHED;
    }
}
