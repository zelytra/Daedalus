package fr.zelytra.daedalus.events.running.environnement.items;

import fr.zelytra.daedalus.managers.items.CustomItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ZEUS_LIGNTING implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() != null || e.getPlayer().getInventory().getItemInOffHand() != null) {
            if(CustomItemStack.hasTag(e.getPlayer().getInventory().getItemInMainHand())){

            }
            if(e.getAction()== Action.RIGHT_CLICK_AIR ||e.getAction()== Action.RIGHT_CLICK_BLOCK){

            }
        }
    }
}
