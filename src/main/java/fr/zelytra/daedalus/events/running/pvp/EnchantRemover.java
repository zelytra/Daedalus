package fr.zelytra.daedalus.events.running.pvp;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class EnchantRemover implements Listener {
    private final HashMap<Enchantment, Integer> blackList = new HashMap<>();

    {
        blackList.put(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        blackList.put(Enchantment.THORNS, 3);
    }

    @EventHandler
    public void onEnchant(EnchantItemEvent e) {
        for (Map.Entry<Enchantment, Integer> entry : e.getEnchantsToAdd().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());

            if (blackList.containsKey(entry.getKey()) && blackList.get(entry.getKey()) == entry.getValue()) {
                e.setCancelled(true);
                e.getEnchanter().sendMessage(GameSettings.LANG.textOf("enchant.cantEnchant"));
            }
        }
    }

    @EventHandler
    public void onAnvil(PrepareAnvilEvent e) {
        if (e.getResult() == null) return;
        ItemMeta meta = e.getResult().getItemMeta();
        for (Map.Entry<Enchantment, Integer> enchants : meta.getEnchants().entrySet())

            if (blackList.containsKey(enchants.getKey()) && blackList.get(enchants.getKey()) == enchants.getValue()) {
                e.setResult(new ItemStack(Material.AIR));
                e.getView().getPlayer().sendMessage(GameSettings.LANG.textOf("enchant.cantFusion"));
            }

    }
}
