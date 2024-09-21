package fr.zelytra.daedalus.events.running.pvp;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantRemover implements Listener {
  private final Map<Enchantment, List<Integer>> blackList = new HashMap<>();

  {
    blackList.put(Enchantment.PROTECTION, List.of(4));
    blackList.put(Enchantment.FORTUNE, List.of(3, 2, 1));
    blackList.put(Enchantment.FIRE_ASPECT, List.of(2));
    blackList.put(Enchantment.THORNS, List.of(3));
  }

  @EventHandler
  public void onEnchant(EnchantItemEvent e) {
    for (var entry : e.getEnchantsToAdd().entrySet()) {
      if (isForbiddenEnchant(entry.getKey(), entry.getValue())) {
        e.setCancelled(true);
        e.getEnchanter().sendMessage(GameSettings.LANG.textOf("enchant.cantEnchant"));
      }
    }
  }

  @EventHandler
  public void onAnvil(PrepareAnvilEvent e) {
    if (e.getResult() == null) return;
    ItemMeta meta = e.getResult().getItemMeta();
    for (var entry : meta.getEnchants().entrySet())
      if (isForbiddenEnchant(entry.getKey(), entry.getValue())) {
        e.setResult(new ItemStack(Material.AIR));
        e.getView().getPlayer().sendMessage(GameSettings.LANG.textOf("enchant.cantFusion"));
      }
  }

  private boolean isForbiddenEnchant(Enchantment enchantment, int lvl) {
    if (!blackList.containsKey(enchantment)) {
      return false;
    }
    return blackList.get(enchantment).contains(lvl);
  }
}
