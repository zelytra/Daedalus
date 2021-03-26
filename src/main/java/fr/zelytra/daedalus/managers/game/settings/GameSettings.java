package fr.zelytra.daedalus.managers.game.settings;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.gods.GodsEnum;

import java.util.ArrayList;

public final class GameSettings {

    public static final int COAL_COOLDOWN = 30;
    public static final int IRON_COOLDOWN = 45;
    public static final int GOLD_COOLDOWN = 120;
    public static final int REDSTONE_COOLDOWN = 85;
    public static final int LAPIS_COOLDOWN = 120;
    public static final int DIAMOND_COOLDOWN = 300;
    public static final int EMERALD_COOLDOWN = 240;
    public static final int ANCIENT_DEBRIS_COOLDOWN = -1;
    public static final int END_STONE_COOLDOWN = 120;
    public static final int OBSIDIAN_COOLDOWN = 60;
    public static final int WOOD_LEAVES = 5;

    public static ArrayList<GodsEnum> getGodsList() {
        return Daedalus.getInstance().getGameManager().getSelectedGods();
    }

    public static int getGodLimit() {
        return 1;
        //return Daedalus.getInstance().getGameManager().getGodLimit();
    }

    public static TemplesGenerationEnum getGenerationType() {
        return TemplesGenerationEnum.RANDOM;
        //return Daedalus.getInstance().getGameManager().getTemplesGeneration();
    }

    public static int getHesperidesGardenCount(){

        return 1;
    }

    public static int getCirceeIslandCount(){

        return 5;
    }

    public static int getMinesCount(){

        return 15;
    }

    public static int getDungeonsCount(){

        return 10;
    }

    public static void reset(){

        Daedalus.getInstance().getGameManager().resetGodsSelection();
        Daedalus.getInstance().getGameManager().resetGodLimit();
        Daedalus.getInstance().getGameManager().setTempleGeneration(TemplesGenerationEnum.RANDOM);

    }
}
 