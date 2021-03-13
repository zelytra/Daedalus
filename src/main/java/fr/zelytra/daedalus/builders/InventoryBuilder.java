package fr.zelytra.daedalus.builders;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryBuilder {

    private Inventory inventory;

    public InventoryBuilder(String name, int size){
        this.inventory = Bukkit.createInventory(null, size, name);
    }

    public InventoryBuilder(String name, InventoryType type){
        this.inventory = Bukkit.createInventory(null, type, name);
    }

    public Inventory getInventory(){
        return inventory;
    }

}
