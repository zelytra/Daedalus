package fr.zelytra.daedalus.managers.game;

import fr.zelytra.daedalus.managers.game.settings.TemplesGenerationEnum;
import fr.zelytra.daedalus.managers.team.TeamManager;

public class GameManager {

    private TeamManager tm;
    private GameStatesEnum state;
    private TemplesGenerationEnum generation;

    public GameManager(){

        this.tm = new TeamManager();
        this.state = GameStatesEnum.RUNNING;
        this.generation = TemplesGenerationEnum.RANDOM;

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

    public TemplesGenerationEnum getTemplesGeneration() {
        return generation;
    }

    // FONCTION DE DEBUT DE PARTIE
    public void start(){



    }

    // FONCTION DE FIN DE PARTIE
    public void stop(){

    }
}
