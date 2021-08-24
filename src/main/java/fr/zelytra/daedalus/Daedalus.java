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
import net.minecraft.server.v1_16_R3.DedicatedServer;
import net.minecraft.server.v1_16_R3.DedicatedServerProperties;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public final class Daedalus extends JavaPlugin {

    public static Daedalus instance;
    private GameManager gameManager;
    private StructureManager structureManager;
    private CraftManager craftManager;
    public static String WORLD_NAME = "daedalus";
    public static Daedalus getInstance() {
        return instance;
    }

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
        editFAWE();

        /* Init managers */
        gameManager = new GameManager();
        structureManager = new StructureManager();
        craftManager = new CraftManager();
        getServer().getConsoleSender().sendMessage("§a   ___    ___     ____  ___    ___     __     __  __  ____§r");
        getServer().getConsoleSender().sendMessage("§a  / _ \\  / _ |   / __/ / _ \\  / _ |   / /    / / / / / __/§r");
        getServer().getConsoleSender().sendMessage("§a / // / / __ |  / _/  / // / / __ |  / /__  / /_/ / _\\ \\  §r");
        getServer().getConsoleSender().sendMessage("§a/____/ /_/ |_| /___/ /____/ /_/ |_| /____/  \\____/ /___/  §r");
        getServer().getConsoleSender().sendMessage("                                                          ");
        getServer().getConsoleSender().sendMessage("§e[DAEDALUS] §6STATUS §7>> §2loaded");
    }

    private void editFAWE() {
        File configLegacy = new File(Bukkit.getPluginManager().getPlugin("FastAsyncWorldEdit").getDataFolder().getPath() + File.separator + "config-legacy.yml");
        FileConfiguration yml = YamlConfiguration.loadConfiguration(configLegacy);
        boolean modifications = false;
        if (!yml.getString(("navigation-wand.item")).equals("minecraft:lead")) {
            yml.set("navigation-wand.item", "minecraft:lead");
            modifications = true;

        }else if(!yml.getString(("wand-item")).equals("minecraft:soul_torch")){
            yml.set("wand-item", "minecraft:soul_torch");
            modifications = true;
        }

        if (modifications){
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


    public GameManager getGameManager() {
        return gameManager;
    }

    public StructureManager getStructureManager() {
        return structureManager;
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

        Bukkit.getWorld(Daedalus.WORLD_NAME).setTime(23250);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setDifficulty(Difficulty.NORMAL);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.DISABLE_RAIDS, true);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.DO_FIRE_TICK, false);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.DO_ENTITY_DROPS, false);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.DO_INSOMNIA, false);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.DO_PATROL_SPAWNING, false);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.MOB_GRIEFING, false);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.MAX_ENTITY_CRAMMING, 50);

        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.DO_TILE_DROPS, true);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.DO_ENTITY_DROPS, true);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.DO_MOB_LOOT, true);
        Bukkit.getWorld(Daedalus.WORLD_NAME).setGameRule(GameRule.KEEP_INVENTORY, false);

        try {
            DedicatedServer server = ((CraftServer) Bukkit.getServer()).getServer();
            DedicatedServerProperties properties = server.getDedicatedServerProperties();

            Field spawnProtectionField = properties.getClass().getField("spawnProtection");
            spawnProtectionField.setAccessible(true);
            spawnProtectionField.set(properties, 0);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }

        for (World world : Bukkit.getWorlds()) {
            world.setMonsterSpawnLimit(60);
            world.setAnimalSpawnLimit(10);
            world.setAmbientSpawnLimit(15);
            world.setWaterAnimalSpawnLimit(5);
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
