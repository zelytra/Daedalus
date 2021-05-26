package fr.zelytra.daedalus.events.running.environnement.respawnable;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class AxeStripped implements Listener {
    private List<Material> blackList = new ArrayList<>();

    {
        blackList.add(Material.WOODEN_AXE);
        blackList.add(Material.STONE_AXE);
        blackList.add(Material.IRON_AXE);
        blackList.add(Material.GOLDEN_AXE);
        blackList.add(Material.DIAMOND_AXE);
        blackList.add(Material.NETHERITE_AXE);
    }

    @EventHandler
    public void onAxeUse(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            switch (e.getHand()) {
                case HAND:
                    if (e.getPlayer().getInventory().getItemInMainHand() != null && blackList.contains(e.getPlayer().getInventory().getItemInMainHand().getType())) {
                        e.setCancelled(true);
                    }
                case OFF_HAND:
                    if (e.getPlayer().getInventory().getItemInOffHand() != null && blackList.contains(e.getPlayer().getInventory().getItemInOffHand().getType())) {
                        e.setCancelled(true);
                    }
            }
        }
    }
}
