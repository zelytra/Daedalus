package fr.zelytra.daedalus;

import fr.zelytra.daedalus.commands.SettingsCommand;
import fr.zelytra.daedalus.managers.EventsManager;
import fr.zelytra.daedalus.managers.game.GameManager;
import fr.zelytra.daedalus.commands.MazeCommands;
import fr.zelytra.daedalus.commands.MazeTabCommands;
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
    }
}
