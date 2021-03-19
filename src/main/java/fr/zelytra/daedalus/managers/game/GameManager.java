package fr.zelytra.daedalus.managers.game;

import fr.zelytra.daedalus.managers.game.settings.TemplesGenerationEnum;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.team.TeamManager;

import java.util.ArrayList;

public class GameManager {

    private TeamManager tm;
    private GameStatesEnum state;
    private TemplesGenerationEnum generation;
    private ArrayList<GodsEnum> selectedGods;
    private int godLimit;
    public final int GOD_MINIMUM = 4;
    public final int GOD_MAXIMUM = 10;

    public GameManager(){

        this.tm = new TeamManager();
        this.state = GameStatesEnum.RUNNING;
        this.generation = TemplesGenerationEnum.RANDOM;
        this.selectedGods = new ArrayList<>();
        this.godLimit = 6;

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

    public void reverseTempleGeneration(){

        if(getTemplesGeneration() == TemplesGenerationEnum.CHOSEN)
            generation = TemplesGenerationEnum.RANDOM;
        else
            generation = TemplesGenerationEnum.CHOSEN;

    }

    public ArrayList<GodsEnum> getSelectedGods() {
        return selectedGods;
    }

    public void resetGodsSelection(){
        selectedGods.clear();
    }

    public int getGodLimit() {
        return godLimit;
    }

    // FONCTION DE DEBUT DE PARTIE
    public void start(){



    }

    // FONCTION DE FIN DE PARTIE
    public void stop(){

    }
}
