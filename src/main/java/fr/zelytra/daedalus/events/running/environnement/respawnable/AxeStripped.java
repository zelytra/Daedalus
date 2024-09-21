package fr.zelytra.daedalus.events.running.environnement.respawnable;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

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
          if (e.getPlayer().getInventory().getItemInMainHand() != null
              && blackList.contains(e.getPlayer().getInventory().getItemInMainHand().getType())) {
            if (isWood(e.getClickedBlock().getType())) e.setCancelled(true);
          }
        case OFF_HAND:
          if (e.getPlayer().getInventory().getItemInOffHand() != null
              && blackList.contains(e.getPlayer().getInventory().getItemInOffHand().getType())) {
            if (isWood(e.getClickedBlock().getType())) e.setCancelled(true);
          }
      }
    }
  }

  private boolean isWood(Material material) {
    ArrayList<Material> wood = new ArrayList<>();
    wood.add(Material.ACACIA_WOOD);
    wood.add(Material.BIRCH_WOOD);
    wood.add(Material.DARK_OAK_WOOD);
    wood.add(Material.JUNGLE_WOOD);
    wood.add(Material.OAK_WOOD);
    wood.add(Material.SPRUCE_WOOD);

    wood.add(Material.ACACIA_LOG);
    wood.add(Material.BIRCH_LOG);
    wood.add(Material.DARK_OAK_LOG);
    wood.add(Material.JUNGLE_LOG);
    wood.add(Material.OAK_LOG);
    wood.add(Material.SPRUCE_LOG);

    return wood.contains(material);
  }
}
