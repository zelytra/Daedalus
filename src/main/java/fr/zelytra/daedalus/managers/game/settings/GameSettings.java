package fr.zelytra.daedalus.managers.game.settings;

import fr.zelytra.daedalus.managers.gods.GodsEnum;

import java.util.ArrayList;

public final class GameSettings {

    public final static ArrayList<GodsEnum> GOD_LIST = new ArrayList<>();
    public static final int GOD_MINIMUM = 4;
    public static final int GOD_MAXIMUM = 10;
    public static int GOD_LIMIT = 10;
    public static TemplesGenerationEnum GOD_SELECTION = TemplesGenerationEnum.RANDOM;
    public static int HESPERIDES_GARDEN_COUNT = 1;
    public static int MINES_COUNT = 15;
    public static int DUNGEONS_COUNT = 10;
    public static int CIRCEE_ISLANDS_COUNT = 5;
    public static boolean FRIENDLY_FIRE = false;
    public static boolean HARDCORE = false;
    public static boolean ABSORPTION = true;
    public static boolean CUT_CLEAN = true;
    public static float APPLE_DROP = 0.08f;
    public static DayCycleEnum DAY_CYCLE = DayCycleEnum.ETERNAL_DAY;

    public static void reset() {

        GOD_LIST.clear();
        GOD_LIMIT = 6;
        GOD_SELECTION = TemplesGenerationEnum.RANDOM;
        HESPERIDES_GARDEN_COUNT = 1;
        MINES_COUNT = 15;
        DUNGEONS_COUNT = 10;
        CIRCEE_ISLANDS_COUNT = 5;
        FRIENDLY_FIRE = false;
        HARDCORE = false;
        ABSORPTION = true;
        CUT_CLEAN = true;
        APPLE_DROP = 0.08f;
    }
}
 