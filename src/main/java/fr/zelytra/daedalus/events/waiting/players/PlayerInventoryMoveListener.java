package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerInventoryMoveListener implements Listener {

    @EventHandler
    public void onMoveItem(InventoryClickEvent e) {
        if (Daedalus.getInstance().getGameManager().isWaiting()) {
            if (e.getClickedInventory() != null && e.getClickedInventory().getType() == InventoryType.PLAYER) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onItemDrop (PlayerDropItemEvent e ){
        if(Daedalus.getInstance().getGameManager().isWaiting()){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop (PlayerSwapHandItemsEvent e ){
        if(Daedalus.getInstance().getGameManager().isWaiting()){
            e.setCancelled(true);
        }
    }
}
