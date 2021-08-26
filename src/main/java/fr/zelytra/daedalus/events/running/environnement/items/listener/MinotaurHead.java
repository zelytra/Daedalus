package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class MinotaurHead implements Listener {

    @EventHandler
    public void onHeadRemove(InventoryClickEvent e) {
        if (!Daedalus.getInstance().getGameManager().isRunning()) return;

        if (e.getWhoClicked().getGameMode() != GameMode.SURVIVAL) return;

        if (e.getInventory().getType() != InventoryType.CRAFTING) return;

        if (e.getCurrentItem() == null) return;

        if (CustomItemStack.getCustomMaterial(e.getCurrentItem()) == CustomMaterial.MINOTAUR_HEAD)
            e.setCancelled(true);

    }
}
