package fr.zelytra.daedalus;

import fr.zelytra.daedalus.commands.broadcast.Broadcast;
import fr.zelytra.daedalus.commands.broadcast.BroadcastTab;
import fr.zelytra.daedalus.commands.checkpoint.Checkpoint;
import fr.zelytra.daedalus.commands.godSummon.GodSummon;
import fr.zelytra.daedalus.commands.godSummon.GodSummonTab;
import fr.zelytra.daedalus.commands.item.ItemsCommands;
import fr.zelytra.daedalus.commands.item.ItemsTabs;
import fr.zelytra.daedalus.commands.location.ShareLocation;
import fr.zelytra.daedalus.commands.maze.MazeCommands;
import fr.zelytra.daedalus.commands.maze.MazeTabCommands;
import fr.zelytra.daedalus.commands.maze.StructureCommands;
import fr.zelytra.daedalus.commands.pause.PauseCommand;
import fr.zelytra.daedalus.commands.revive.HadesRevive;
import fr.zelytra.daedalus.commands.revive.Revive;
import fr.zelytra.daedalus.commands.revive.ReviveTabs;
import fr.zelytra.daedalus.commands.state.StateCommands;
import fr.zelytra.daedalus.commands.state.StateTabs;
import fr.zelytra.daedalus.commands.test;
import fr.zelytra.daedalus.commands.wiki.Wiki;
import fr.zelytra.daedalus.events.EventsManager;
import fr.zelytra.daedalus.managers.game.GameManager;
import fr.zelytra.daedalus.managers.items.CraftManager;
import fr.zelytra.daedalus.managers.setup.StartupManager;
import fr.zelytra.daedalus.managers.structure.StructureManager;
import java.io.File;
import java.io.IOException;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.plugin.java.JavaPlugin;

public final class Daedalus extends JavaPlugin {

	@Getter
	public static Daedalus instance;

	@Getter
	private GameManager gameManager;

	@Getter
	private StructureManager structureManager;

	public static String WORLD_NAME = "daedalus";

	@Override
	public void onLoad() {
		instance = this;
		new StartupManager();
	}

	@Override
	public void onEnable() {
		/* Init world */
		Bukkit.createWorld(new WorldCreator("daedalus"));
		checkFAWE();

		/* Init registers */
		EventsManager.registerEvents(this);
		regCommands();
		setupServer();
		// editFAWE();

		/* Init managers */
		gameManager = new GameManager();
		structureManager = new StructureManager();
		new CraftManager();
		getServer().getConsoleSender().sendMessage("§a   ___    ___     ____  ___    ___     __     __  __  ____§r");
		getServer().getConsoleSender().sendMessage("§a  / _ \\  / _ |   / __/ / _ \\  / _ |   / /    / / / / / __/§r");
		getServer().getConsoleSender().sendMessage("§a / // / / __ |  / _/  / // / / __ |  / /__  / /_/ / _\\ \\  §r");
		getServer().getConsoleSender().sendMessage("§a/____/ /_/ |_| /___/ /____/ /_/ |_| /____/  \\____/ /___/  §r");
		getServer().getConsoleSender().sendMessage("                                                          ");
		getServer().getConsoleSender().sendMessage("§e[DAEDALUS] §6STATUS §7>> §2loaded");
	}

	private void editFAWE() {
		File configLegacy = new File(Bukkit.getPluginManager().getPlugin("FastAsyncWorldEdit").getDataFolder().getPath()
				+ File.separator + "config-legacy.yml");
		FileConfiguration yml = YamlConfiguration.loadConfiguration(configLegacy);
		boolean modifications = false;
		if (!yml.getString(("navigation-wand.item")).equals("minecraft:lead")) {
			yml.set("navigation-wand.item", "minecraft:lead");
			modifications = true;

		} else if (!yml.getString(("wand-item")).equals("minecraft:soul_torch")) {
			yml.set("wand-item", "minecraft:soul_torch");
			modifications = true;
		}

		if (modifications) {
			try {
				yml.save(configLegacy);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Bukkit.getConsoleSender().sendMessage("§c----------------------------------");
			Bukkit.getConsoleSender().sendMessage("§e           ! WARNING !            ");
			Bukkit.getConsoleSender().sendMessage("§e      Server shutdown to apply    ");
			Bukkit.getConsoleSender().sendMessage("§e           modifications          ");
			Bukkit.getConsoleSender().sendMessage("                                    ");
			Bukkit.getConsoleSender().sendMessage("§c    ! PLEASE RESTART SERVER !     ");
			Bukkit.getConsoleSender().sendMessage("§c----------------------------------");
			Bukkit.shutdown();
		}
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§e[DAEDALUS] §6STATUS §7>> §cunloaded");
		Bukkit.unloadWorld(WORLD_NAME, false);
	}

	private void regCommands() {

		getCommand("maze").setExecutor(new MazeCommands());
		getCommand("maze").setTabCompleter(new MazeTabCommands());

		getCommand("structure").setExecutor(new StructureCommands());
		getCommand("wiki").setExecutor(new Wiki());
		getCommand("coordinates").setExecutor(new ShareLocation());
		getCommand("test").setExecutor(new test());
		getCommand("checkpoint").setExecutor(new Checkpoint());

		getCommand("revive").setExecutor(new Revive());
		getCommand("revive").setTabCompleter(new ReviveTabs());

		getCommand("state").setExecutor(new StateCommands());
		getCommand("state").setTabCompleter(new StateTabs());

		getCommand("dgive").setExecutor(new ItemsCommands());
		getCommand("dgive").setTabCompleter(new ItemsTabs());

		getCommand("hadesrevive").setExecutor(new HadesRevive());

		getCommand("pause").setExecutor(new PauseCommand());

		getCommand("godsummon").setExecutor(new GodSummon());
		getCommand("godsummon").setTabCompleter(new GodSummonTab());

		getCommand("broadcast").setExecutor(new Broadcast());
		getCommand("broadcast").setTabCompleter(new BroadcastTab());
	}

	private void setupServer() {
		World world = Bukkit.getWorld(Daedalus.WORLD_NAME);
		assert world != null;

		world.setTime(23250);
		world.setDifficulty(Difficulty.NORMAL);
		world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
		world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
		world.setGameRule(GameRule.DISABLE_RAIDS, true);
		world.setGameRule(GameRule.DO_FIRE_TICK, false);
		world.setGameRule(GameRule.DO_ENTITY_DROPS, false);
		world.setGameRule(GameRule.DO_INSOMNIA, false);
		world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
		world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
		world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
		world.setGameRule(GameRule.MOB_GRIEFING, false);
		world.setGameRule(GameRule.MAX_ENTITY_CRAMMING, 50);

		world.setGameRule(GameRule.DO_TILE_DROPS, true);
		world.setGameRule(GameRule.DO_ENTITY_DROPS, true);
		world.setGameRule(GameRule.DO_MOB_LOOT, true);
		world.setGameRule(GameRule.KEEP_INVENTORY, false);

		world.setGameRule(GameRule.SPAWN_RADIUS, 0);
		world.setGameRule(GameRule.SPAWN_CHUNK_RADIUS, 0);

		for (World w : Bukkit.getWorlds()) {
			w.setSpawnLimit(SpawnCategory.MONSTER, 60);
			w.setSpawnLimit(SpawnCategory.ANIMAL, 10);
			w.setSpawnLimit(SpawnCategory.AMBIENT, 15);
			w.setSpawnLimit(SpawnCategory.WATER_ANIMAL, 5);
		}
	}

	public static boolean checkFAWE() {
		if (Bukkit.getServer().getPluginManager().getPlugin("FastAsyncWorldEdit") == null) {
			Bukkit.getConsoleSender().sendMessage("§c----------------------------------");
			Bukkit.getConsoleSender().sendMessage("§e           ! WARNING !            ");
			Bukkit.getConsoleSender().sendMessage("§e FastAsyncWorldEdit is not install");
			Bukkit.getConsoleSender().sendMessage("§e  Please refer to github tutorial ");
			Bukkit.getConsoleSender().sendMessage("                                    ");
			Bukkit.getConsoleSender().sendMessage("§c       ! SERVER SHUTDOWN !        ");
			Bukkit.getConsoleSender().sendMessage("§c----------------------------------");
			Bukkit.getServer().shutdown();
			return false;
		}
		return true;
	}
}
