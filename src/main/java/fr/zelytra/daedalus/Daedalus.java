package fr.zelytra.daedalus;

import fr.zelytra.daedalus.commands.*;
import fr.zelytra.daedalus.managers.EventsManager;
import fr.zelytra.daedalus.managers.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.plugin.java.JavaPlugin;

public final class Daedalus extends JavaPlugin {

    public static Daedalus instance;
    private GameManager gameManager;

    public static Daedalus getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

        EventsManager.registerEvents(this);
        regCommands();
        setupServer();
        gameManager = new GameManager();
        getServer().getConsoleSender().sendMessage("§a   ___    ___     ____  ___    ___     __     __  __  ____§r\n" +
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

    private void regCommands() {
        getCommand("settings").setExecutor(new SettingsCommand());
        getCommand("maze").setExecutor(new MazeCommands());
        getCommand("maze").setTabCompleter(new MazeTabCommands());
        getCommand("structure").setExecutor(new StructureCommands());
        getCommand("state").setExecutor(new StateCommands());
    }

    private void setupServer(){

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

    }
}
