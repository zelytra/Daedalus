package fr.zelytra.daedalus.events.running.environnement.mobCutclean;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.maze.Maze;
import fr.zelytra.daedalus.managers.maze.Vector2;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.ArrayList;
import java.util.List;

public class MobSpawn implements Listener {
    private List<EntityType> whitelist = new ArrayList<>();

    {
        whitelist.add(EntityType.DROPPED_ITEM);
        whitelist.add(EntityType.ARROW);
        whitelist.add(EntityType.SPECTRAL_ARROW);
        whitelist.add(EntityType.SPLASH_POTION);
        whitelist.add(EntityType.ITEM_FRAME);
        whitelist.add(EntityType.EXPERIENCE_ORB);
        whitelist.add(EntityType.FALLING_BLOCK);
        whitelist.add(EntityType.FIREWORK);
        whitelist.add(EntityType.TRIDENT);

        whitelist.add(EntityType.VINDICATOR);

        whitelist.add(EntityType.EVOKER_FANGS);
        whitelist.add(EntityType.FIREBALL);

    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent e) {
        if (!Daedalus.getInstance().getGameManager().isRunning()) {
            e.setCancelled(true);
            return;
        }
        if (Daedalus.getInstance().getStructureManager().getStructuresPosition().isEmpty()) {
            return;
        }

        Maze maze = Daedalus.getInstance().getStructureManager().getMaze();
        if (maze == null) {
            return;
        }
        if (whitelist.contains(e.getEntity().getType())) {
            return;
        }
        if (e.getEntity().getType() == EntityType.ENDERMAN) {
            e.setCancelled(true);
            return;
        }
        try {
            Vector2 matrixCoordinate = new Vector2((int) (e.getLocation().getX() - maze.getOrigin().getX() + 1), (int) (e.getLocation().getZ() - maze.getOrigin().getZ() + 1));
            switch (maze.getMaze()[matrixCoordinate.x][matrixCoordinate.z]) {
                //Structure case
                case -1:
                case -2:
                case -3:
                case -4:
                case -5:
                case -6:
                    if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CUSTOM || e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER) {
                        break;
                    }
                    e.setCancelled(true);
                case 1:
                    e.setCancelled(true);
                default:
                    break;
            }
        }catch (Exception ignored){
            e.setCancelled(true);
        }
    }
}
