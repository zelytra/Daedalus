package fr.zelytra.daedalus;

import fr.zelytra.daedalus.commands.SettingsCommand;
import fr.zelytra.daedalus.commands.item.ItemsCommands;
import fr.zelytra.daedalus.commands.item.ItemsTabs;
import fr.zelytra.daedalus.commands.location.ShareLocation;
import fr.zelytra.daedalus.commands.maze.MazeCommands;
import fr.zelytra.daedalus.commands.maze.MazeTabCommands;
import fr.zelytra.daedalus.commands.maze.StructureCommands;
import fr.zelytra.daedalus.commands.state.StateCommands;
import fr.zelytra.daedalus.commands.state.StateTabs;
import fr.zelytra.daedalus.commands.test;
import fr.zelytra.daedalus.commands.wiki.Wiki;
import fr.zelytra.daedalus.managers.EventsManager;
import fr.zelytra.daedalus.managers.game.GameManager;
import fr.zelytra.daedalus.managers.items.CraftManager;
import fr.zelytra.daedalus.managers.structure.StructureManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class Daedalus extends JavaPlugin {

    public static Daedalus instance;
    private GameManager gameManager;
    private StructureManager structureManager;
    private CraftManager craftManager;

    public static Daedalus getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        //Init registers
        EventsManager.registerEvents(this);
        regCommands();
        setupServer();
        //Init managers
        gameManager = new GameManager();
        structureManager = new StructureManager();
        craftManager = new CraftManager();
        getServer().getConsoleSender().sendMessage(
                "§a   ___    ___     ____  ___    ___     __     __  __  ____§r\n" +
                "§a  / _ \\  / _ |   / __/ / _ \\  / _ |   / /    / / / / / __/§r\n" +
                "§a / // / / __ |  / _/  / // / / __ |  / /__  / /_/ / _\\ \\  §r\n" +
                "§a/____/ /_/ |_| /___/ /____/ /_/ |_| /____/  \\____/ /___/  §r\n" +
                "                                                          ");
        getServer().getConsoleSender().sendMessage("§e[DAEDALUS] §6STATUS §7>> §2loaded");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§e[DAEDALUS] §6STATUS §7>> §cunloaded");
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public StructureManager getStructureManager() {
        return structureManager;
    }

    private void regCommands() {
        getCommand("settings").setExecutor(new SettingsCommand());

        getCommand("maze").setExecutor(new MazeCommands());
        getCommand("maze").setTabCompleter(new MazeTabCommands());

        getCommand("structure").setExecutor(new StructureCommands());
        getCommand("wiki").setExecutor(new Wiki());
        getCommand("coordinate").setExecutor(new ShareLocation());
        getCommand("test").setExecutor(new test());

        getCommand("state").setExecutor(new StateCommands());
        getCommand("state").setTabCompleter(new StateTabs());

        getCommand("dgive").setExecutor(new ItemsCommands());
        getCommand("dgive").setTabCompleter(new ItemsTabs());

    }

    private void setupServer() {

        Bukkit.getWorld("world").setTime(6000);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        Bukkit.getWorld("world").setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        Bukkit.getWorld("world").setGameRule(GameRule.DISABLE_RAIDS, true);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_FIRE_TICK, false);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_ENTITY_DROPS, false);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_INSOMNIA, false);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_PATROL_SPAWNING, false);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        Bukkit.getWorld("world").setGameRule(GameRule.MOB_GRIEFING, false);
        Bukkit.getWorld("world").setGameRule(GameRule.MAX_ENTITY_CRAMMING, 50);

        Bukkit.getWorld("world").setGameRule(GameRule.DO_TILE_DROPS, true);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_ENTITY_DROPS, true);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_MOB_LOOT, true);
        Bukkit.getWorld("world").setGameRule(GameRule.KEEP_INVENTORY, false);

        for (World world : Bukkit.getWorlds()) {
            world.setMonsterSpawnLimit(60);
            world.setAnimalSpawnLimit(10);
            world.setAmbientSpawnLimit(15);
            world.setWaterAnimalSpawnLimit(5);
        }

    }
}
