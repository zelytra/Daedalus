package fr.zelytra.daedalus.builders.guiBuilder;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class CustomGUI implements InventoryHolder {
    @NotNull
    @Override
    public Inventory getInventory() {
        return null;
    }
}
