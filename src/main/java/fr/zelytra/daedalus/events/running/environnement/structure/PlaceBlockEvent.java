package fr.zelytra.daedalus.events.running.environnement.structure;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.BlockEnum;
import fr.zelytra.daedalus.managers.game.GameStatesEnum;
import fr.zelytra.daedalus.managers.maze.Maze;
import fr.zelytra.daedalus.managers.maze.Vector2;
import fr.zelytra.daedalus.managers.structure.Structure;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.BoundingBox;

import java.util.Map;

public class PlaceBlockEvent implements Listener {
    private static final int wallHigh = 20;
    private static final int limit = 4;


    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (Daedalus.getInstance().getGameManager().getState() != GameStatesEnum.RUNNING) {
            return;
        }
        if (Daedalus.getInstance().getStructureManager().getStructuresPosition().isEmpty()) {
            return;
        }

        Maze maze = Daedalus.getInstance().getStructureManager().getMaze();
        if (maze == null) {
            return;
        }
        int groundY = (int) maze.getOrigin().getY();
        Vector2 matrixCoordinate = new Vector2((int) (e.getBlock().getX() - maze.getOrigin().getX() + 1), (int) (e.getBlock().getZ() - maze.getOrigin().getZ() + 1));

        switch (maze.getMaze()[matrixCoordinate.x][matrixCoordinate.z]) {
            //Structure case
            case -1:
                if (BlockEnum.containType(e.getBlock().getType())) {
                    break;
                }
                //Loop structures
                for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {

                    if (entry.getKey().contains(e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ())) {
                        if (!entry.getValue().canBlock()) {
                            e.setCancelled(true);
                        }
                        if (e.getBlock().getY() >= groundY + (wallHigh - limit) || e.getBlock().getY() <= groundY - 1)
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
                if (e.getBlock().getY() >= groundY + (wallHigh - limit) || e.getBlock().getY() <= groundY - 1)
                    e.setCancelled(true);
                break;

        }

    }
}
