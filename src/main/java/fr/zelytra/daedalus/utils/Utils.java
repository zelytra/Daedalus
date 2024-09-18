package fr.zelytra.daedalus.utils;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Utils {

    public static List<String> dynamicTab(List<String> list, String arg) {
        List<String> finalList = new ArrayList<String>(list);
        for (String s : list) {
            if (!s.toLowerCase().startsWith(arg.toLowerCase())) {
                finalList.remove(s);
            }
        }
        Collections.sort(finalList);
        return finalList;
    }

    public static int getEmptySlots(Player player) {
        int i = 0;
        for (ItemStack is : player.getInventory().getContents()) {
            if (!(is == null))
                continue;
            i++;
        }
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (!(item == null))
                continue;
            i--;
        }
        return i;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            @SuppressWarnings("unused")
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static ItemStack BookEnchantedItemStack(Material material, Enchantment enchantment, int lvl) {
        ItemStack item = new ItemStack(material);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchantment, lvl, false);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack EnchantedItemStack(Material material, Enchantment enchantment, int lvl) {
        ItemStack item = new ItemStack(material);
        item.addUnsafeEnchantment(enchantment, lvl);
        return item;
    }

    public static void runTotemDisplay(Player player) {
        player.playEffect(EntityEffect.TOTEM_RESURRECT);
    }

    public static boolean isFullInv(Player player) {
        if (player.getInventory().firstEmpty() == -1) {
            return true;
        }
        return false;
    }

    public static void setTabFX(int amount) {
        for (Player player : Daedalus.getInstance().getServer().getOnlinePlayers()) {
            player.setPlayerListHeader("§6「 §8<< §6Daedalus §8>>§6 」\n§8[ §f" + amount + "§8/§f" + Daedalus.getInstance().getServer().getMaxPlayers() + "§8 ]\n§r");
            player.setPlayerListFooter("\n§8<< §7Developed by : §6Zelytra §8>>§6\n§8<< §7Designed by : §6Spirit | Ichabodt | Minucia §8>>§6\n\n§8[V1.0]");
        }

    }
}
