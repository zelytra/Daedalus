package fr.zelytra.daedalus.events.waiting.item;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class GameStarter implements Listener {

	@EventHandler
	public void onItemClick(PlayerInteractEvent e) {
		if (Daedalus.getInstance().getGameManager().isWaiting() && e.getAction() == Action.RIGHT_CLICK_BLOCK
				|| e.getAction() == Action.RIGHT_CLICK_AIR) {
			if (Daedalus.getInstance().getGameManager().isWaiting()) {
				if (e.getItem() != null && e.getItem().getType() == Material.BELL) {
					Daedalus.getInstance().getGameManager().preStart();
				}
			}
		}
	}
}
