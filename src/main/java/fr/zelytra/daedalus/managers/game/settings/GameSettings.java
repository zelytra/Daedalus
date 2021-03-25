package fr.zelytra.daedalus.managers.game.settings;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.gods.GodsEnum;

import java.util.ArrayList;

public final class GameSettings {

    public static final int COAL_COOLDOWN = 20 * 30;
    public static final int IRON_COOLDOWN = 20 * 45;
    public static final int GOLD_COOLDOWN = 20 * 120;
    public static final int REDSTONE_COOLDOWN = 20 * 85;
    public static final int LAPIS_COOLDOWN = 20 * 120;
    public static final int DIAMOND_COOLDOWN = 20 * 300;
    public static final int EMERALD_COOLDOWN = 20 * 240;
    public static final int ANCIENT_DEBRIS_COOLDOWN = -1;
    public static final int END_STONE_COOLDOWN = 20 * 120;
    public static final int OBSIDIAN_COOLDOWN = 20 * 60;

    public static ArrayList<GodsEnum> getGodsList() {
        return Daedalus.getInstance().getGameManager().getSelectedGods();
    }

    public static int getGodLimit() {
        return Daedalus.getInstance().getGameManager().getGodLimit();
    }

    public static TemplesGenerationEnum getGenerationType() {
        return Daedalus.getInstance().getGameManager().getTemplesGeneration();
    }

    public static int getHesperidesGardenCount(){

        return 0;
    }

    public static int getCerseiIslandsCount(){

        return 0;
    }

    public static int getMinesCount(){

        return 0;
    }

    public static int getDungeonsCount(){

        return 0;
    }

    public static void reset(){

        Daedalus.getInstance().getGameManager().resetGodsSelection();
        Daedalus.getInstance().getGameManager().resetGodLimit();
        Daedalus.getInstance().getGameManager().setTempleGeneration(TemplesGenerationEnum.RANDOM);

    }
}
 