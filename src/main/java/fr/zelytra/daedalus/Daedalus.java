package fr.zelytra.daedalus;

import fr.zelytra.daedalus.maze.MazeCommands;
import fr.zelytra.daedalus.maze.MazeTabCommands;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Daedalus extends JavaPlugin {
    public static Plugin plugin;
    @Override
    public void onEnable() {
        plugin = this;
        Message.startup();
        getCommand("maze").setExecutor(new MazeCommands());
        getCommand("maze").setTabCompleter(new MazeTabCommands());
    }

    @Override
    public void onDisable() {

    }
}
