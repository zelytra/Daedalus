package fr.zelytra.daedalus.events.running.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropListener implements Listener {

	private final List<CustomMaterial> whitelist = new ArrayList<>();

	{
		whitelist.add(CustomMaterial.DIVINE_FRAGMENT);
		whitelist.add(CustomMaterial.DIVINE_HEART);
		whitelist.add(CustomMaterial.DIVINE_TRACKER);
		whitelist.add(CustomMaterial.WALL_BREAKER);

		whitelist.add(CustomMaterial.APHRODITE_TOTEM);
		whitelist.add(CustomMaterial.ARES_TOTEM);
		whitelist.add(CustomMaterial.ARTEMIS_TOTEM);
		whitelist.add(CustomMaterial.ATHENA_TOTEM);
		whitelist.add(CustomMaterial.DEMETER_TOTEM);
		whitelist.add(CustomMaterial.DIONYSUS_TOTEM);
		whitelist.add(CustomMaterial.HADES_TOTEM);
		whitelist.add(CustomMaterial.HERMES_TOTEM);
		whitelist.add(CustomMaterial.MINOTAUR_TOTEM);
		whitelist.add(CustomMaterial.POSEIDON_TOTEM);
		whitelist.add(CustomMaterial.ZEUS_TOTEM);
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {

		if (!Daedalus.getInstance().getGameManager().isRunning())
			return;

		if (e.getPlayer().getGameMode() != GameMode.SURVIVAL)
			return;

		if (CustomItemStack.hasTag(e.getItemDrop().getItemStack())
				&& !whitelist.contains(CustomItemStack.getCustomMaterial(e.getItemDrop().getItemStack())))
			e.setCancelled(true);
	}

	@EventHandler
	public void onItemMove(InventoryClickEvent e) {

		if (!Daedalus.getInstance().getGameManager().isRunning())
			return;

		if (e.getWhoClicked().getGameMode() != GameMode.SURVIVAL)
			return;

		if (e.getWhoClicked().getOpenInventory().getType() == InventoryType.CRAFTING
				|| e.getWhoClicked().getOpenInventory().getType() == InventoryType.ANVIL)
			return;

		if (e.getCurrentItem() == null)
			return;

		if (CustomItemStack.hasTag(e.getCurrentItem())
				&& !whitelist.contains(CustomItemStack.getCustomMaterial(e.getCurrentItem())))
			e.setCancelled(true);
	}
}
