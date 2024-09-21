package fr.zelytra.daedalus.events.running.environnement.structure;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.GameStatesEnum;
import fr.zelytra.daedalus.managers.maze.Maze;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

public class BucketListener implements Listener {
	private static final int wallHigh = 20;
	private static final int limit = 4;

	@EventHandler
	public void onBucket(PlayerBucketFillEvent e) {
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

		if (e.getBlock().getY() >= groundY + (wallHigh - limit))
			e.setCancelled(true);
	}

	@EventHandler
	public void onBucket(PlayerBucketEmptyEvent e) {
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

		if (e.getBlock().getY() >= groundY + (wallHigh - limit))
			e.setCancelled(true);
	}
}
