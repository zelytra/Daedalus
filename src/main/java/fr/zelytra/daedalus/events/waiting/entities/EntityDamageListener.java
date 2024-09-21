package fr.zelytra.daedalus.events.waiting.entities;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

	@EventHandler
	public void playerDamage(EntityDamageEvent e) {

		if (Daedalus.getInstance().getGameManager().isWaiting()
				|| Daedalus.getInstance().getGameManager().isStarted()) {
			e.setCancelled(true);
		}
	}
}
