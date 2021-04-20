package fr.zelytra.daedalus.managers;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.*;
import fr.zelytra.daedalus.events.running.environnement.respawnable.PlayerBreakBlockListener;
import fr.zelytra.daedalus.events.running.environnement.respawnable.TreeGrowthListener;
import fr.zelytra.daedalus.events.running.environnement.items.*;
import fr.zelytra.daedalus.events.running.environnement.structure.BreakBlockListener;
import fr.zelytra.daedalus.events.running.environnement.structure.PlaceBlockListener;
import fr.zelytra.daedalus.events.running.players.PlayerDeathListener;
import fr.zelytra.daedalus.events.waiting.entities.EntityDamageListener;
import fr.zelytra.daedalus.events.waiting.entities.EntityTargetListener;
import fr.zelytra.daedalus.events.waiting.environment.BlockPlaceListener;
import fr.zelytra.daedalus.events.waiting.inventory.InventoryListener;
import fr.zelytra.daedalus.events.waiting.players.PlayerInteractListener;
import fr.zelytra.daedalus.events.waiting.players.PlayerJoinListener;
import fr.zelytra.daedalus.events.waiting.players.PlayerQuitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventsManager {

    public static void registerEvents(Daedalus pl) {
        PluginManager pm = Bukkit.getPluginManager();

        /* Maze */
        pm.registerEvents(new BreakBlockListener(),pl);
        pm.registerEvents(new PlaceBlockListener(),pl);

        /*Custom Items*/
        pm.registerEvents(new ZeusLightning(),pl);
        pm.registerEvents(new HadesScepter(),pl);
        pm.registerEvents(new AphroditeHeart(),pl);
        pm.registerEvents(new DemeterSickle(),pl);
        pm.registerEvents(new DionysosCup(),pl);
        pm.registerEvents(new MinotaurCharge(),pl);
        pm.registerEvents(new MedusaHead(),pl);

        /*Gods Summoning*/
        pm.registerEvents(new ZeusHandler(),pl);
        pm.registerEvents(new PoseidonHandler(),pl);
        pm.registerEvents(new HadesHandler(),pl);
        pm.registerEvents(new AresHandler(),pl);
        pm.registerEvents(new AphroditeHandler(),pl);
        pm.registerEvents(new HermesHandler(),pl);

        /* Environment */
        pm.registerEvents(new BlockPlaceListener(), pl);
        pm.registerEvents(new TreeGrowthListener(), pl);

        /* Inventory */
        pm.registerEvents(new InventoryListener(), pl);

        /* Players */
        pm.registerEvents(new PlayerJoinListener(), pl);
        pm.registerEvents(new PlayerQuitListener(), pl);
        pm.registerEvents(new PlayerInteractListener(), pl);
        pm.registerEvents(new PlayerBreakBlockListener(), pl);
        pm.registerEvents(new PlayerDeathListener(), pl);

        /* Entities */
        pm.registerEvents(new EntityTargetListener(), pl);
        pm.registerEvents(new EntityDamageListener(), pl);

    }
}
