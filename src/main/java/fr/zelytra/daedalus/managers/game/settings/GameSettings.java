package fr.zelytra.daedalus.managers.game.settings;

import fr.zelytra.daedalus.managers.languages.Lang;

public final class GameSettings {

	public static int GOD_LIMIT = 10;
	public static int HESPERIDES_GARDEN_COUNT = 1;
	public static int MINES_COUNT = 15;
	public static int DUNGEONS_COUNT = 10;
	public static int LIBRARY = 5;
	public static int CIRCEE_ISLANDS_COUNT = 5;
	public static boolean FRIENDLY_FIRE = false;
	public static boolean HARDCORE = false;
	public static boolean ABSORPTION = false;
	public static boolean CUT_CLEAN = true;
	public static int APPLE_DROP = 8;
	public static Lang LANG = Lang.EN;
	public static DayCycleEnum DAY_CYCLE = DayCycleEnum.DEFAULT;
	public static int TIME_PER_EPISODE = 1200; // seconds

	public static void reset() {
		LANG = Lang.EN;
		GOD_LIMIT = 10;
		HESPERIDES_GARDEN_COUNT = 1;
		MINES_COUNT = 15;
		DUNGEONS_COUNT = 10;
		CIRCEE_ISLANDS_COUNT = 5;
		FRIENDLY_FIRE = false;
		HARDCORE = false;
		ABSORPTION = true;
		CUT_CLEAN = true;
		APPLE_DROP = 8;
		DAY_CYCLE = DayCycleEnum.DEFAULT;
		TIME_PER_EPISODE = 1200; // seconds
	}
}
