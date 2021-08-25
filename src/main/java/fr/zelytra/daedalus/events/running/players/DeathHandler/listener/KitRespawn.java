package fr.zelytra.daedalus.events.running.players.DeathHandler.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.players.DeathHandler.events.PartielDeathEvent;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class KitRespawn implements Listener {
    private final int episode = 3;
    private static final List<String> playerNames = new ArrayList<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPartielDeath(PartielDeathEvent e) {

        if (Daedalus.getInstance().getGameManager().getTimeManager().getEpisode() == episode)
            if (!playerNames.contains(e.getPlayer().getName())) {
                giveKit(e.getPlayer());
                playerNames.add(e.getPlayer().getName());
            }

    }

    private void giveKit(Player player) {
        player.getInventory().setHelmet(Utils.EnchantedItemStack(Material.DIAMOND_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        player.getInventory().setChestplate(Utils.EnchantedItemStack(Material.DIAMOND_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        player.getInventory().setLeggings(Utils.EnchantedItemStack(Material.DIAMOND_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 1));
        player.getInventory().setBoots(Utils.EnchantedItemStack(Material.DIAMOND_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 1));

        player.getInventory().addItem(Utils.EnchantedItemStack(Material.DIAMOND_SWORD, Enchantment.DAMAGE_ALL, 2));
        player.getInventory().addItem(Utils.EnchantedItemStack(Material.IRON_PICKAXE, Enchantment.DIG_SPEED, 2));
        player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 20));
        player.getInventory().addItem(new ItemStack(Material.BOW));
        player.getInventory().addItem(new ItemStack(Material.ARROW, 16));
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 4));
    }
}
