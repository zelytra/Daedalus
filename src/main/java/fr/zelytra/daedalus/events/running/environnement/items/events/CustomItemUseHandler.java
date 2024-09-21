package fr.zelytra.daedalus.events.running.environnement.items.events;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class CustomItemUseHandler implements Listener {

	@EventHandler
	public void onCustomItemUse(PlayerInteractEvent e) {

		if (Daedalus.getInstance().getGameManager().isRunning()) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

				if (e.getPlayer().isOp()
						&& (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)
						&& e.getPlayer().getInventory().getItemInMainHand().getType() == Material.COMPASS) {
					e.setCancelled(true);
				}

				if (e.getHand() == EquipmentSlot.OFF_HAND
						&& CustomItemStack.hasTag(e.getPlayer().getInventory().getItemInOffHand())) {

					CustomItemUseEvent customItemUseEvent = new CustomItemUseEvent(e.getPlayer(),
							CustomItemStack.getCustomMaterial(e.getPlayer().getInventory().getItemInOffHand()),
							e.getPlayer().getInventory().getItemInOffHand(), e);
					Bukkit.getPluginManager().callEvent(customItemUseEvent);

					return;

				} else if ((e.getHand() == EquipmentSlot.HAND
						&& CustomItemStack.hasTag(e.getPlayer().getInventory().getItemInMainHand()))) {

					CustomItemUseEvent customItemUseEvent = new CustomItemUseEvent(e.getPlayer(),
							CustomItemStack.getCustomMaterial(e.getPlayer().getInventory().getItemInMainHand()),
							e.getPlayer().getInventory().getItemInMainHand(), e);
					Bukkit.getPluginManager().callEvent(customItemUseEvent);

					return;
				}
			}
		}
	}
}
