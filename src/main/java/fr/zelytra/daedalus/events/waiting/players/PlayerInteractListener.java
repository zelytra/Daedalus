package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {

		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && Daedalus.getInstance().getGameManager().isWaiting()
				&& e.getClickedBlock().getType() != Material.BROWN_SHULKER_BOX)
			e.setCancelled(true);
	}
}
