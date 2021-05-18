package fr.zelytra.daedalus.events.running.environnement;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.maze.Maze;
import fr.zelytra.daedalus.managers.maze.Vector2;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.ArrayList;
import java.util.List;

public class MobSpawn implements Listener {
    private List<EntityType> whitelist = new ArrayList<>();

    {
        whitelist.add(EntityType.CHICKEN);
        whitelist.add(EntityType.COW);
        whitelist.add(EntityType.PIG);
        whitelist.add(EntityType.SHEEP);
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
    }

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {
        if(!Daedalus.getInstance().getGameManager().isRunning()){
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
        if (e.getEntityType() == EntityType.VEX) {
            ((LivingEntity) e.getEntity()).setMaxHealth(1);
            return;
        }

        Vector2 matrixCoordinate = new Vector2((int) (e.getLocation().getX() - maze.getOrigin().getX() + 1), (int) (e.getLocation().getZ() - maze.getOrigin().getZ() + 1));
        if (matrixCoordinate.x > (4 * maze.getSize() - 3) || matrixCoordinate.x < 0 || matrixCoordinate.z < 0 || matrixCoordinate.z > (4 * maze.getSize() - 3)) {
            e.setCancelled(true);
            return;
        }
        switch (maze.getMaze()[matrixCoordinate.x][matrixCoordinate.z]) {
            //Structure case
            case -1:
            case -2:
            case -3:
            case -4:
            case -5:
            case -6:
                if (e.getEntity().getCustomName() != null && !e.getEntity().getCustomName().isEmpty()) {
                    break;
                }
                e.setCancelled(true);
            case 1:
                e.setCancelled(true);
            default:
                break;
        }
    }
}
