package fr.zelytra.daedalus.managers;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.commands.wiki.Wiki;
import fr.zelytra.daedalus.events.ForcedTexturePack;
import fr.zelytra.daedalus.events.running.players.DeathHandler;
import fr.zelytra.daedalus.events.running.environnement.*;
import fr.zelytra.daedalus.events.running.environnement.gods.*;
import fr.zelytra.daedalus.events.running.environnement.items.*;
import fr.zelytra.daedalus.events.running.environnement.mobCutclean.MobCutClean;
import fr.zelytra.daedalus.events.running.environnement.respawnable.PlayerBreakBlockListener;
import fr.zelytra.daedalus.events.running.environnement.respawnable.TreeGrowthListener;
import fr.zelytra.daedalus.events.running.environnement.structure.BreakBlockListener;
import fr.zelytra.daedalus.events.running.environnement.structure.InteractListener;
import fr.zelytra.daedalus.events.running.environnement.structure.PlaceBlockListener;
import fr.zelytra.daedalus.events.running.players.PlayerChatRListener;
import fr.zelytra.daedalus.events.running.pvp.CoolDown;
import fr.zelytra.daedalus.events.running.pvp.DamagerHandler;
import fr.zelytra.daedalus.events.running.pvp.PlayerRegen;
import fr.zelytra.daedalus.events.waiting.entities.EntityDamageListener;
import fr.zelytra.daedalus.events.waiting.entities.EntityTargetListener;
import fr.zelytra.daedalus.events.waiting.environment.BlockPlaceListener;
import fr.zelytra.daedalus.events.waiting.inventory.InventoryListener;
import fr.zelytra.daedalus.events.waiting.players.*;
import fr.zelytra.daedalus.managers.guardian.GuardianListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventsManager {

    public static void registerEvents(Daedalus pl) {
        PluginManager pm = Bukkit.getPluginManager();

        /* Maze */
        pm.registerEvents(new BreakBlockListener(),pl);
        pm.registerEvents(new PlaceBlockListener(),pl);
        pm.registerEvents(new InteractListener(),pl);

        /* Guardian */
        pm.registerEvents(new GuardianListener(),pl);

        /* Texture Pack */
        pm.registerEvents(new ForcedTexturePack(),pl);

        /*Custom Items*/
        pm.registerEvents(new ZeusLightning(),pl);
        pm.registerEvents(new HadesScepter(),pl);
        pm.registerEvents(new AphroditeHeart(),pl);
        pm.registerEvents(new DemeterSickle(),pl);
        pm.registerEvents(new DionysosCup(),pl);
        pm.registerEvents(new MinotaurCharge(),pl);
        pm.registerEvents(new DivineTracker(),pl);
        //pm.registerEvents(new MedusaHead(),pl);

        /*Gods Summoning*/
        pm.registerEvents(new ZeusHandler(),pl);
        pm.registerEvents(new PoseidonHandler(),pl);
        pm.registerEvents(new HadesHandler(),pl);
        pm.registerEvents(new AresHandler(),pl);
        pm.registerEvents(new AphroditeHandler(),pl);
        pm.registerEvents(new HermesHandler(),pl);
        pm.registerEvents(new ArtemisHandler(),pl);
        pm.registerEvents(new AthenaHandler(),pl);
        pm.registerEvents(new DionysosHandler(),pl);
        pm.registerEvents(new DemeterHandler(),pl);
        pm.registerEvents(new MinotaureHandler(),pl);

        /* Environment */
        pm.registerEvents(new BlockPlaceListener(), pl);
        pm.registerEvents(new TreeGrowthListener(), pl);
        pm.registerEvents(new MilkDrink(), pl);
        pm.registerEvents(new PortalListener(), pl);
        pm.registerEvents(new MobCutClean(), pl);
        pm.registerEvents(new WitherSpawn(), pl);
        pm.registerEvents(new DeathHandler(), pl);
        pm.registerEvents(new MobSpawn(), pl);
        pm.registerEvents(new HostileMob(), pl);

        /* Inventory */
        pm.registerEvents(new InventoryListener(), pl);
        pm.registerEvents(new Wiki(), pl);

        /* Players */
        pm.registerEvents(new PlayerJoinListener(), pl);
        pm.registerEvents(new PlayerQuitListener(), pl);
        pm.registerEvents(new PlayerInteractListener(), pl);
        pm.registerEvents(new PlayerBreakBlockListener(), pl);
        pm.registerEvents(new PlayerChatWListener(), pl);
        pm.registerEvents(new PlayerChatRListener(), pl);
        pm.registerEvents(new PlayerFoodListener(), pl);
        pm.registerEvents(new PlayerOpCommandListener(), pl);

        /* Entities */
        pm.registerEvents(new EntityTargetListener(), pl);
        pm.registerEvents(new EntityDamageListener(), pl);

        /* PVP */
        pm.registerEvents(new CoolDown(),pl);
        pm.registerEvents(new PlayerRegen(),pl);
        pm.registerEvents(new DamagerHandler(),pl);

    }
}
