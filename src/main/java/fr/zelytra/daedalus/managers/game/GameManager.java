package fr.zelytra.daedalus.managers.game;

import fr.zelytra.daedalus.managers.game.settings.TemplesGenerationEnum;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.team.TeamManager;
import fr.zelytra.daedalus.structure.StructureManager;

import java.util.ArrayList;

public class GameManager {

    public final int GOD_MINIMUM = 4;
    public final int GOD_MAXIMUM = 10;
    private final TeamManager tm;
    private final StructureManager sm;
    private GameStatesEnum state;
    private TemplesGenerationEnum generation;
    private final ArrayList<GodsEnum> selectedGods;
    private int godLimit;

    public GameManager() {

        this.tm = new TeamManager();
        this.sm = new StructureManager();
        this.state = GameStatesEnum.RUNNING;
        this.generation = TemplesGenerationEnum.RANDOM;
        this.selectedGods = new ArrayList<>();
        this.godLimit = 6;

    }

    public TeamManager getTeamManager() {
        return tm;
    }

    public StructureManager getStructureManager() {
        return this.sm;
    }

    public GameStatesEnum getState() {
        return state;
    }

    public void setState(GameStatesEnum state) {
        this.state = state;
    }

    public boolean isWaiting() {
        return state == GameStatesEnum.WAIT;
    }

    public boolean isRunning() {
        return state == GameStatesEnum.RUNNING;
    }

    public boolean isFinished() {
        return state == GameStatesEnum.FINISHED;
    }

    public TemplesGenerationEnum getTemplesGeneration() {
        return generation;
    }

    public void reverseTempleGeneration() {

        if (getTemplesGeneration() == TemplesGenerationEnum.CHOSEN)
            generation = TemplesGenerationEnum.RANDOM;
        else
            generation = TemplesGenerationEnum.CHOSEN;

    }

    public ArrayList<GodsEnum> getSelectedGods() {
        return selectedGods;
    }

    public void resetGodsSelection() {
        selectedGods.clear();
    }

    public int getGodLimit() {
        return godLimit;
    }

    public boolean increaseGodLimit() {

        if (godLimit >= GOD_MAXIMUM)
            return false;
        else
            godLimit += 1;

        return true;
    }

    public boolean decreaseGodLimit() {

        if (godLimit <= GOD_MINIMUM)
            return false;
        else
            godLimit -= 1;

        return true;
    }

    public void resetGodLimit() {
        godLimit = 6;
    }

    public void setTempleGeneration(TemplesGenerationEnum generation) {
        this.generation = generation;
    }

    // FONCTION DE DEBUT DE PARTIE
    public void start() {


    }

    // FONCTION DE FIN DE PARTIE
    public void stop() {

    }
}
