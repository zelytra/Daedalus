package fr.zelytra.daedalus.managers.loottable;

import org.bukkit.inventory.ItemStack;

public class Loot {

    private ItemStack item;
    private int amount;
    private double luck;

    /**
     * @param item   Item looted
     * @param amount Amount of the item
     * @param luck   Luck of apparition in the container [0-1]
     */

    public Loot(ItemStack item, int amount, double luck) {
        this.item = item;
        this.amount = amount;
        this.luck = luck;
    }

    public double getLuck() {
        return luck;
    }

    public int getAmount() {
        return amount;
    }

    public ItemStack getItem() {
        return item;
    }
}
