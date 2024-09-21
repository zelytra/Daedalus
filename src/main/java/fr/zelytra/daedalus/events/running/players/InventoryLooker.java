package fr.zelytra.daedalus.events.running.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.guiBuilder.CustomGUI;
import fr.zelytra.daedalus.builders.guiBuilder.InterfaceBuilder;
import fr.zelytra.daedalus.builders.guiBuilder.VisualType;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryLooker implements Listener {

	@EventHandler
	public void onPlayerLookInventory(PlayerInteractEntityEvent e) {
		if (Daedalus.getInstance().getGameManager().isRunning()) {

			if (e.getPlayer().getGameMode() == GameMode.SPECTATOR && e.getRightClicked() instanceof Player) {
				Player target = (Player) e.getRightClicked();

				if (Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(target).isAlive(target)) {

					openInventoryLooker(e.getPlayer(), target);
				}
			}
		}
	}

	@EventHandler
	public void onInventoryInteract(InventoryClickEvent e) {
		if (e.getInventory() instanceof CustomGUI) {
			if (e.getView().getTitle().contains(" Inventory")) {
				e.setCancelled(true);
			}
		}
	}

	private void openInventoryLooker(Player player, Player target) {
		InterfaceBuilder inventory = new InterfaceBuilder(54, target.getName() + " Inventory");

		// Create interface
		ItemStack[] content = new ItemStack[54];

		for (int x = 0; x <= 8; x++) {
			content[x] = VisualType.BLANK_ORANGE_GLASSE.getItem();
		}
		for (int x = 36; x <= 44; x++) {
			content[x] = VisualType.BLANK_ORANGE_GLASSE.getItem();
		}
		for (int x = 1; x <= 4; x++) {
			content[x] = new ItemStack(Material.AIR);
		}
		content[6] = target.getInventory().getItemInOffHand();
		int count = 1;
		for (ItemStack armor : target.getInventory().getArmorContents()) {
			content[count] = armor;
			count++;
		}
		for (int x = 9; x <= 35; x++) {
			content[x] = target.getInventory().getContents()[x];
		}
		count = 45;
		for (int x = 0; x <= 8; x++) {
			content[count] = target.getInventory().getContents()[x];
			count++;
		}

		inventory.setContent(content);
		inventory.open(player);
	}
}
