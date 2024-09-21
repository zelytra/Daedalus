package fr.zelytra.daedalus.events.running.pvp;

import fr.zelytra.daedalus.Daedalus;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamagerHandler implements Listener {
	List<Material> axes = new ArrayList<>();

	{
		axes.add(Material.STONE_AXE);
		axes.add(Material.IRON_AXE);
		axes.add(Material.GOLDEN_AXE);
		axes.add(Material.DIAMOND_AXE);
		axes.add(Material.NETHERITE_AXE);
	}

	@EventHandler
	public void damageListener(EntityDamageByEntityEvent e) {
		if (Daedalus.getInstance().getGameManager().isRunning()) {
			switch (e.getCause()) {
				case PROJECTILE :
					e.setDamage(e.getDamage() * 0.75);
				case ENTITY_ATTACK :
					if (e.getDamager() instanceof Player) {
						Player player = (Player) e.getDamager();
						if (axes.contains(player.getInventory().getItemInMainHand().getType())) {
							e.setDamage(e.getDamage() * 0.5);
						}
					}
			}
		}
	}
}
