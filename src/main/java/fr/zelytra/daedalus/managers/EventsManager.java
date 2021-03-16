package fr.zelytra.daedalus.managers;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.waiting.entities.EntityDamageListener;
import fr.zelytra.daedalus.events.waiting.entities.EntityTargetListener;
import fr.zelytra.daedalus.events.waiting.environment.BlockPlaceListener;
import fr.zelytra.daedalus.events.waiting.inventory.InventoryListener;
import fr.zelytra.daedalus.events.waiting.players.*;
import org.bukkit.Bukkit;
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

        /* Entities */
        pm.registerEvents(new EntityTargetListener(), pl);
        pm.registerEvents(new EntityDamageListener(), pl);

    }
}
