package fr.zelytra.daedalus.managers.loottable;

import org.bukkit.inventory.ItemStack;

public class Loot {

    private ItemStack item;
    private double luck;

    /**
     * @param item Item looted
     * @param luck Luck of apparition in the container [0-1]
     */

    public Loot(ItemStack item, double luck) {
        this.item = item;
        this.luck = luck/100.0;
    }

    public double getLuck() {
        return luck;
    }


    public ItemStack getItem() {
        return item;
    }
}
