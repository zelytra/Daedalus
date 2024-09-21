package fr.zelytra.daedalus.events.running.environnement.mobCutclean;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class WitherSpawn implements Listener {
	@EventHandler
	public void onWitherSpawn(EntitySpawnEvent e) {
		if (e.getEntityType() == EntityType.WITHER) {
			e.setCancelled(true);
		}
	}
}
