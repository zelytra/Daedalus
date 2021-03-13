package fr.zelytra.daedalus.managers;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.waiting.environment.BlockPlaceListener;
import fr.zelytra.daedalus.events.waiting.inventory.InventoryListener;
import fr.zelytra.daedalus.events.waiting.players.PlayerInteractListener;
import fr.zelytra.daedalus.events.waiting.players.PlayerJoinListener;
import fr.zelytra.daedalus.events.waiting.players.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class EventsManager {

    public static void registerEvents(Daedalus pl) {
        PluginManager pm = Bukkit.getPluginManager();

        /* Environment */
        pm.registerEvents(new BlockPlaceListener(), pl);

        /* Inventory */
        pm.registerEvents(new InventoryListener(), pl);

        /* Players */
        pm.registerEvents(new PlayerJoinListener(), pl);
        pm.registerEvents(new PlayerQuitListener(), pl);
        pm.registerEvents(new PlayerInteractListener(), pl);

    }

    public static void registerEvent(Listener listener) {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(listener, Daedalus.getInstance());
    }
}
