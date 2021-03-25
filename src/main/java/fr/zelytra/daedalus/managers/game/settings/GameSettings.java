package fr.zelytra.daedalus.managers.game.settings;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.gods.GodsEnum;

import java.util.ArrayList;

public final class GameSettings {

    public static ArrayList<GodsEnum> getGodsList() {
        return Daedalus.getInstance().getGameManager().getSelectedGods();
    }

    public static int getGodLimit() {
        return Daedalus.getInstance().getGameManager().getGodLimit();
    }

    public static TemplesGenerationEnum getGenerationType() {
        return Daedalus.getInstance().getGameManager().getTemplesGeneration();
    }

    public static void reset(){

        Daedalus.getInstance().getGameManager().resetGodsSelection();
        Daedalus.getInstance().getGameManager().resetGodLimit();
        Daedalus.getInstance().getGameManager().setTempleGeneration(TemplesGenerationEnum.RANDOM);

    }
}
 