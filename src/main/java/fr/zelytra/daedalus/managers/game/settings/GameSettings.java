package fr.zelytra.daedalus.managers.game.settings;

import fr.zelytra.daedalus.managers.gods.GodsEnum;

import java.util.ArrayList;

public final class GameSettings {

    public final static ArrayList<GodsEnum> GOD_LIST = new ArrayList<>();
    public static final int GOD_MINIMUM = 4;
    public static final int GOD_MAXIMUM = 10;
    public static int GOD_LIMIT = 6;
    public static TemplesGenerationEnum GOD_SELECTION = TemplesGenerationEnum.RANDOM;
    public static int HESPERIDES_GARDEN_COUNT = 1;
    public static int MINES_COUNT = 15;
    public static int DUNGEONS_COUNT = 10;
    public static int CIRCEE_ISLANDS_COUNT = 5;

    public static void reset() {

        GOD_LIST.clear();
        GOD_LIMIT = 6;
        GOD_SELECTION = TemplesGenerationEnum.RANDOM;

    }
}
 