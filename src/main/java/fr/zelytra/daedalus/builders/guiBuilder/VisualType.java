package fr.zelytra.daedalus.builders.guiBuilder;

import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum VisualType {
    BLANK_BLACK_GLASSE(new VisualItemStack(Material.BLACK_STAINED_GLASS_PANE, " ", false)),
    BLANK_BLUE_GLASSE(new VisualItemStack(Material.BLUE_STAINED_GLASS_PANE, " ", false)),
    BLANK_CYAN_GLASSE(new VisualItemStack(Material.CYAN_STAINED_GLASS_PANE, " ", false)),
    BLANK_LIGHT_BLUE_GLASSE(new VisualItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, " ", false)),
    BLANK_RED_GLASSE(new VisualItemStack(Material.RED_STAINED_GLASS_PANE, " ", false)),
    BLANK_GREEN_GLASSE(new VisualItemStack(Material.GREEN_STAINED_GLASS_PANE, " ", false)),
    BLANK_GRAY_GLASSE(new VisualItemStack(Material.GRAY_STAINED_GLASS_PANE, " ", false)),
    BLANK_LIGHT_GRAY_GLASSE(new VisualItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ", false)),

    NEXT_ARROW(new VisualItemStack(CustomMaterial.NEXT_ARROW, "§bNext", false)),
    PREVIOUS_ARROW(new VisualItemStack(CustomMaterial.PREVIOUS_ARROW, "§bPrevious", false)),
    RETURN(new VisualItemStack(Material.BARRIER, "§cBack", false,"Back to main page"));

    private final VisualItemStack item;

    VisualType(VisualItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return item.getItem();
    }
}
