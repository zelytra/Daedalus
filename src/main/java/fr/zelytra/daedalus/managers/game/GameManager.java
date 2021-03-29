package fr.zelytra.daedalus.managers.game;

import fr.zelytra.daedalus.managers.game.settings.DayCycleEnum;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.game.settings.TemplesGenerationEnum;
import fr.zelytra.daedalus.managers.gods.MinosObject;
import fr.zelytra.daedalus.managers.structure.StructureManager;
import fr.zelytra.daedalus.managers.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;

public class GameManager {

    private final TeamManager tm;
    private final StructureManager sm;
    private GameStatesEnum state;
    private MinosObject minos;

    public GameManager() {

        this.tm = new TeamManager();
        this.sm = new StructureManager();
        this.minos = new MinosObject();
        this.state = GameStatesEnum.RUNNING;

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

    public void reverseTempleGeneration() {

        if (GameSettings.GOD_SELECTION == TemplesGenerationEnum.CHOSEN)
            GameSettings.GOD_SELECTION = TemplesGenerationEnum.RANDOM;
        else
            GameSettings.GOD_SELECTION = TemplesGenerationEnum.CHOSEN;

    }

    public boolean increaseGodLimit() {

        if (GameSettings.GOD_LIMIT >= GameSettings.GOD_MAXIMUM)
            return false;
        else
            GameSettings.GOD_LIMIT  += 1;

        return true;
    }

    public boolean decreaseGodLimit() {

        if (GameSettings.GOD_LIMIT  <= GameSettings.GOD_MINIMUM)
            return false;
        else
            GameSettings.GOD_LIMIT  -= 1;

        return true;
    }

    public MinosObject getMinos() {
        return minos;
    }

    private void applySettings(){

        Bukkit.getWorld("world").setTime(GameSettings.DAY_CYCLE.getTicks());
        if(GameSettings.DAY_CYCLE == DayCycleEnum.DEFAULT)
            Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
        else
            Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

        if(GameSettings.HARDCORE)
            Bukkit.getWorld("world").setGameRule(GameRule.NATURAL_REGENERATION, false);
        else
            Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);

    }

    // FONCTION DE DEBUT DE PARTIE
    public void start() {

    }

    // FONCTION DE FIN DE PARTIE
    public void stop() {

    }

}
