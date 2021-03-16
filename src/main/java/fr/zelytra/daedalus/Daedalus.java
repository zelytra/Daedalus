package fr.zelytra.daedalus;

import fr.zelytra.daedalus.maze.MazeCommands;
import fr.zelytra.daedalus.maze.MazeTabCommands;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.plugin.Plugin;
import fr.zelytra.daedalus.managers.EventsManager;
import fr.zelytra.daedalus.managers.game.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Daedalus extends JavaPlugin {

    public static Daedalus instance;
    private GameManager gameManager;

    public static Daedalus getInstance(){
        return instance;
    }

    @Override
    public void onLoad(){
        instance = this;
    }

    @Override
    public void onEnable() {
        Message.startup();
        getCommand("maze").setExecutor(new MazeCommands());
        getCommand("maze").setTabCompleter(new MazeTabCommands());
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§e[DAEDALUS] §6STATUS §7>> §cunloaded");
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
