package fr.zelytra.daedalus.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

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

    public static boolean isFullInv(Player player) {
        if (player.getInventory().firstEmpty() == -1) {
            return true;
        }
        return false;
    }
}
