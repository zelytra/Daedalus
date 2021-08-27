package fr.zelytra.daedalus.events.running.environnement.structure;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.maze.Maze;
import fr.zelytra.daedalus.managers.maze.Vector2;
import fr.zelytra.daedalus.managers.structure.Structure;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.Map;

public class BreakBlockListener implements Listener {
    private static final ArrayList<Material> blacklist = new ArrayList<>();

    static {
        blacklist.add(Material.SMOOTH_SANDSTONE);
        blacklist.add(Material.BROWN_MUSHROOM_BLOCK);
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onBreak(BlockBreakEvent e) {
        if (!Daedalus.getInstance().getGameManager().isRunning()) {
            return;
        }
        if (Daedalus.getInstance().getStructureManager().getStructuresPosition().isEmpty()) {
            return;
        }

        Maze maze = Daedalus.getInstance().getStructureManager().getMaze();
        if (maze == null) {
            return;
        }

        Vector2 matrixCoordinate = new Vector2((int) (e.getBlock().getX() - maze.getOrigin().getX() + 1), (int) (e.getBlock().getZ() - maze.getOrigin().getZ() + 1));
        switch (maze.getMaze()[matrixCoordinate.x][matrixCoordinate.z]) {
            //Structure case
            case -1:
            case -2:
            case -3:
            case -4:
            case -5:
            case -6:
            case -7:
                //Loop structures
                for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {
                    if (entry.getKey().contains(e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ())) {
                        if (entry.getValue().canBlock()) {
                            if (blacklist.contains(e.getBlock().getType())) {
                                e.setCancelled(true);
                                break;
                            }
                           break;
                        }
                        e.setCancelled(true);
                        break;
                    }
                }
                break;
            //Wall case
            case 1:
                e.setCancelled(true);
                break;
            //Corridor case
            default:
                if (blacklist.contains(e.getBlock().getType())) 
                    e.setCancelled(true);

                int groundY = (int) maze.getOrigin().getY();
                if (e.getBlock().getY() <= groundY - 1)
                    e.setCancelled(true);
                break;

        }

    }
}
