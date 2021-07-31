package fr.zelytra.daedalus.events;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.commands.wiki.Wiki;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnHandler;
import fr.zelytra.daedalus.events.running.environnement.gods.listener.*;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemDropHandler;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseHandler;
import fr.zelytra.daedalus.events.running.environnement.items.listener.*;
import fr.zelytra.daedalus.events.running.environnement.items.listener.athenaMap.AthenaMap;
import fr.zelytra.daedalus.events.running.environnement.mobCutclean.*;
import fr.zelytra.daedalus.events.running.environnement.respawnable.AxeStripped;
import fr.zelytra.daedalus.events.running.environnement.respawnable.PlayerBreakBlockListener;
import fr.zelytra.daedalus.events.running.environnement.respawnable.TreeGrowthListener;
import fr.zelytra.daedalus.events.running.environnement.structure.*;
import fr.zelytra.daedalus.events.running.players.*;
import fr.zelytra.daedalus.events.running.players.DeathHandler.listener.DeathListener;
import fr.zelytra.daedalus.events.running.players.DeathHandler.listener.DefinitiveDeathListener;
import fr.zelytra.daedalus.events.running.players.DeathHandler.listener.PartielDeathListener;
import fr.zelytra.daedalus.events.running.pvp.CoolDown;
import fr.zelytra.daedalus.events.running.pvp.DamagerHandler;
import fr.zelytra.daedalus.events.running.pvp.EnchantRemover;
import fr.zelytra.daedalus.events.running.pvp.PlayerRegen;
import fr.zelytra.daedalus.events.waiting.entities.EntityDamageListener;
import fr.zelytra.daedalus.events.waiting.entities.EntityTargetListener;
import fr.zelytra.daedalus.events.waiting.environment.BlockPlaceListener;
import fr.zelytra.daedalus.events.waiting.environment.JumpCheckPoint;
import fr.zelytra.daedalus.events.waiting.gui.GameSettingsInterface;
import fr.zelytra.daedalus.events.waiting.gui.TeamSelector;
import fr.zelytra.daedalus.events.waiting.item.GameStarter;
import fr.zelytra.daedalus.events.waiting.players.*;
import fr.zelytra.daedalus.managers.guardian.GuardianBossBar;
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
        new GuardianBossBar();
        new StructureListener();


        /* Texture Pack */
        pm.registerEvents(new ForcedTexturePack(),pl);

        /*Custom Items*/
        pm.registerEvents(new CustomItemUseHandler(),pl);
        pm.registerEvents(new CustomItemDropHandler(),pl);
        pm.registerEvents(new ZeusLightning(),pl);
        pm.registerEvents(new HadesScepter(),pl);
        pm.registerEvents(new AphroditeHeart(),pl);
        pm.registerEvents(new DemeterSickle(),pl);
        pm.registerEvents(new DionysosCup(),pl);
        pm.registerEvents(new DionysosMug(),pl);
        pm.registerEvents(new MinotaurCharge(),pl);
        pm.registerEvents(new DivineTracker(),pl);
        pm.registerEvents(new AthenaMap(),pl);
        pm.registerEvents(new HermesCaduceus(),pl);
        pm.registerEvents(new AresWarBanner(),pl);
        //pm.registerEvents(new MedusaHead(),pl);

        /*Gods Handlers*/
        pm.registerEvents(new GodSpawnHandler(),pl);
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
        pm.registerEvents(new JumpCheckPoint(), pl);
        pm.registerEvents(new TreeGrowthListener(), pl);
        pm.registerEvents(new MilkDrink(), pl);
        pm.registerEvents(new PortalListener(), pl);
        pm.registerEvents(new MobCutClean(), pl);
        pm.registerEvents(new WitherSpawn(), pl);
        pm.registerEvents(new MobSpawn(), pl);
        pm.registerEvents(new HostileMob(), pl);
        pm.registerEvents(new AxeStripped(), pl);
        pm.registerEvents(new BucketListener(), pl);
        pm.registerEvents(new PistonListener(), pl);
        pm.registerEvents(new EnchantRemover(), pl);

        /* Death */
        pm.registerEvents(new DeathListener(), pl);
        pm.registerEvents(new DefinitiveDeathListener(), pl);
        pm.registerEvents(new PartielDeathListener(), pl);


        /* Interface */
        pm.registerEvents(new ServerPingListener(), pl);
        pm.registerEvents(new Wiki(), pl);
        pm.registerEvents(new TeamSelector(), pl);
        pm.registerEvents(new GameStarter(), pl);
        pm.registerEvents(new GameSettingsInterface(), pl);
        pm.registerEvents(new InventoryLooker(), pl);

        /* Players */
        pm.registerEvents(new PlayerJoinListener(), pl);
        pm.registerEvents(new PlayerInventoryMoveListener(), pl);
        pm.registerEvents(new PlayerEatGoldenApple(), pl);
        pm.registerEvents(new PlayerPreLoginListener(), pl);
        pm.registerEvents(new PlayerQuitListener(), pl);
        pm.registerEvents(new PlayerBreakBlockListener(), pl);
        pm.registerEvents(new PlayerChatWListener(), pl);
        pm.registerEvents(new PlayerChatRListener(), pl);
        pm.registerEvents(new PlayerFoodListener(), pl);
        pm.registerEvents(new PlayerOpCommandListener(), pl);
        pm.registerEvents(new PrivateMessageListener(), pl);
        pm.registerEvents(new PlayerHotelClick(), pl);
        pm.registerEvents(new PlayerInteractListener(), pl);
        pm.registerEvents(new LifeSharing(), pl);

        /* Entities */
        pm.registerEvents(new EntityTargetListener(), pl);
        pm.registerEvents(new EntityDamageListener(), pl);

        /* PVP */
        pm.registerEvents(new CoolDown(),pl);
        pm.registerEvents(new PlayerRegen(),pl);
        pm.registerEvents(new DamagerHandler(),pl);

    }
}
