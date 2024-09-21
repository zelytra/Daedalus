package fr.zelytra.daedalus.events.running.environnement.mobCutclean;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class HostileMob implements Listener {

	@EventHandler
	public void mobLoot(EntityDeathEvent e) {
		switch (e.getEntityType()) {
			case SKELETON :
				e.getDrops().add(new ItemStack(Material.ARROW, 3));
				break;
			case SPIDER :
				e.getDrops().add(new ItemStack(Material.STRING, 1));
				break;
			case CREEPER :
				e.getDrops().add(new ItemStack(Material.GUNPOWDER, 2));
				break;
			case EVOKER :
				List<ItemStack> loots = new ArrayList<>();
				for (ItemStack item : e.getDrops()) {
					if (item.getType() == Material.TOTEM_OF_UNDYING) {
						continue;
					}
					loots.add(item);
				}
				e.getDrops().clear();
				e.getDrops().addAll(loots);
				break;
		}
	}
}
