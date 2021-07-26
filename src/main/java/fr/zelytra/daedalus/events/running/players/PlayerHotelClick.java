package fr.zelytra.daedalus.events.running.players;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerHotelClick implements Listener {

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.LODESTONE) {
            if (e.getHand() == EquipmentSlot.HAND && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING)
                return;
            e.getPlayer().openWorkbench(e.getClickedBlock().getLocation(), true);

        }
    }
}
