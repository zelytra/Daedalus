package fr.zelytra.daedalus.events.running.environnement.structure;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.GameStatesEnum;
import fr.zelytra.daedalus.managers.maze.Maze;
import fr.zelytra.daedalus.managers.maze.Vector2;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {
  private static final int wallHigh = 20;
  private static final int limit = 4;

  @EventHandler
  public void onInteract(PlayerInteractEvent e) {

    if (Daedalus.getInstance().getGameManager().getState() != GameStatesEnum.RUNNING) return;

    if (Daedalus.getInstance().getStructureManager().getStructuresPosition().isEmpty()) return;

    Maze maze = Daedalus.getInstance().getStructureManager().getMaze();

    if (maze == null) return;

    int groundY = (int) maze.getOrigin().getY();

    if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

      Vector2 matrixCoordinate =
          new Vector2(
              (int) (e.getClickedBlock().getX() - maze.getOrigin().getX() + 1),
              (int) (e.getClickedBlock().getZ() - maze.getOrigin().getZ() + 1));

      if (maze.getMaze()[matrixCoordinate.x][matrixCoordinate.z] >= 1
          && e.getClickedBlock().getY() >= groundY + (wallHigh - limit)) e.setCancelled(true);
    }
  }
}
