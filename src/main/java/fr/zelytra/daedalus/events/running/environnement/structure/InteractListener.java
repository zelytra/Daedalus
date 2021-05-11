package fr.zelytra.daedalus.events.running.environnement.structure;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.GameStatesEnum;
import fr.zelytra.daedalus.managers.maze.Maze;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {
    private static final int wallHigh = 20;
    private static final int limit = 4;

    @EventHandler
    public void onBreak(PlayerInteractEvent e) {
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
        if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getClickedBlock().getY() >= groundY + (wallHigh - limit))
                e.setCancelled(true);
        }

    }
}
