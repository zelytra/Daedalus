package fr.zelytra.daedalus.builders;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder(ItemStack itemStack){
        this.itemStack = itemStack;
    }

    public ItemBuilder(Material material){

        this.itemStack = new ItemStack(material);

    }

    public ItemBuilder(Material material, String name){

        this.itemStack = new ItemStack(material);
        ItemMeta meta = this.itemStack.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(name);
        this.itemStack.setItemMeta(meta);

    }

    public void setName(String name){
        ItemMeta meta = this.itemStack.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(name);
        this.itemStack.setItemMeta(meta);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

}
