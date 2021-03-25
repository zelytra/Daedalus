package fr.zelytra.daedalus.events.running.environnement;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum OreEnum {

    IRON_ORE(GameSettings.IRON_COOLDOWN, Material.IRON_ORE, new ItemStack(Material.IRON_INGOT)),
    GOLD_ORE(GameSettings.GOLD_COOLDOWN, Material.GOLD_ORE, new ItemStack(Material.GOLD_INGOT)),
    END_STONE(GameSettings.END_STONE_COOLDOWN, Material.END_STONE),
    OBSIDIAN(GameSettings.OBSIDIAN_COOLDOWN, Material.OBSIDIAN),
    DIAMOND_ORE(GameSettings.DIAMOND_COOLDOWN, Material.DIAMOND_ORE),
    EMERALD_ORE(GameSettings.EMERALD_COOLDOWN, Material.EMERALD_ORE),
    COAL_ORE(GameSettings.COAL_COOLDOWN, Material.COAL_ORE),
    LAPIS_ORE(GameSettings.LAPIS_COOLDOWN, Material.LAPIS_ORE),
    ANCIENT_DEBRIS(GameSettings.ANCIENT_DEBRIS_COOLDOWN, Material.ANCIENT_DEBRIS),
    REDSTONE_ORE(GameSettings.REDSTONE_COOLDOWN, Material.REDSTONE_ORE);

    private final int ticks;
    private final Material material;
    private ItemStack itemStack;

    OreEnum(int ticks, Material material, ItemStack itemStack){
        this.ticks = ticks;
        this.itemStack = itemStack;
        this.material = material;
    }

    OreEnum(int ticks, Material material){
        this.ticks = ticks;
        this.material = material;
    }

    public int getTicks() {
        return ticks;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}



/*
*
* CYCLE JOUR/NUIT: TRUE || FALSE
*
* SI FALSE -> MIDI || MINUIT
*
* SI TRUE -> CYCLE DE 20min (durée classique)
*
*
* */
