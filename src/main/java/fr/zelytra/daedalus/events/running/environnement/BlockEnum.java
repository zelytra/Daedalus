package fr.zelytra.daedalus.events.running.environnement;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum BlockEnum {

    IRON_ORE(GameSettings.IRON_COOLDOWN, Material.IRON_ORE, new ItemStack(Material.IRON_INGOT)),
    GOLD_ORE(GameSettings.GOLD_COOLDOWN, Material.GOLD_ORE, new ItemStack(Material.GOLD_INGOT)),
    END_STONE(GameSettings.END_STONE_COOLDOWN, Material.END_STONE),
    OBSIDIAN(GameSettings.OBSIDIAN_COOLDOWN, Material.OBSIDIAN),
    DIAMOND_ORE(GameSettings.DIAMOND_COOLDOWN, Material.DIAMOND_ORE),
    EMERALD_ORE(GameSettings.EMERALD_COOLDOWN, Material.EMERALD_ORE),
    COAL_ORE(GameSettings.COAL_COOLDOWN, Material.COAL_ORE),
    LAPIS_ORE(GameSettings.LAPIS_COOLDOWN, Material.LAPIS_ORE),
    ANCIENT_DEBRIS(GameSettings.ANCIENT_DEBRIS_COOLDOWN, Material.ANCIENT_DEBRIS),
    REDSTONE_ORE(GameSettings.REDSTONE_COOLDOWN, Material.REDSTONE_ORE),
    OAK_LOG(GameSettings.WOOD_LEAVES, Material.OAK_LOG, new ItemStack(Material.OAK_PLANKS, 4)),
    SPRUCE_LOG(GameSettings.WOOD_LEAVES, Material.SPRUCE_LOG, new ItemStack(Material.SPRUCE_PLANKS, 4)),
    DARK_OAK_LOG(GameSettings.WOOD_LEAVES, Material.DARK_OAK_LOG, new ItemStack(Material.DARK_OAK_PLANKS, 4)),
    ACACIA_LOG(GameSettings.WOOD_LEAVES, Material.ACACIA_LOG, new ItemStack(Material.ACACIA_PLANKS, 4)),
    BIRCH_LOG(GameSettings.WOOD_LEAVES, Material.BIRCH_LOG, new ItemStack(Material.BIRCH_PLANKS, 4)),
    JUNGLE_LOG(GameSettings.WOOD_LEAVES, Material.JUNGLE_LOG, new ItemStack(Material.JUNGLE_PLANKS, 4)),
    OAK_WOOD(GameSettings.WOOD_LEAVES, Material.OAK_WOOD, new ItemStack(Material.OAK_PLANKS, 4)),
    SPRUCE_WOOD(GameSettings.WOOD_LEAVES, Material.SPRUCE_WOOD, new ItemStack(Material.SPRUCE_PLANKS, 4)),
    DARK_OAK_WOOD(GameSettings.WOOD_LEAVES, Material.DARK_OAK_WOOD, new ItemStack(Material.DARK_OAK_PLANKS, 4)),
    ACACIA_WOOD(GameSettings.WOOD_LEAVES, Material.ACACIA_WOOD, new ItemStack(Material.ACACIA_PLANKS, 4)),
    BIRCH_WOOD(GameSettings.WOOD_LEAVES, Material.BIRCH_WOOD, new ItemStack(Material.BIRCH_PLANKS, 4)),
    JUNGLE_WOOD(GameSettings.WOOD_LEAVES, Material.JUNGLE_WOOD, new ItemStack(Material.JUNGLE_PLANKS, 4)),
    JUNGLE_LEAVES(GameSettings.WOOD_LEAVES, Material.JUNGLE_LEAVES, new ItemStack(Material.APPLE)),
    OAK_LEAVES(GameSettings.WOOD_LEAVES, Material.OAK_LEAVES, new ItemStack(Material.APPLE)),
    DARK_OAK_LEAVES(GameSettings.WOOD_LEAVES, Material.DARK_OAK_LEAVES, new ItemStack(Material.APPLE)),
    ACACIA_LEAVES(GameSettings.WOOD_LEAVES, Material.ACACIA_LEAVES, new ItemStack(Material.APPLE)),
    SPRUCE_LEAVES(GameSettings.WOOD_LEAVES, Material.SPRUCE_LEAVES, new ItemStack(Material.APPLE)),
    BIRCH_LEAVES(GameSettings.WOOD_LEAVES, Material.BIRCH_LEAVES, new ItemStack(Material.APPLE));

    private final int ticks;
    private final Material material;
    private ItemStack itemStack;

    BlockEnum(int ticks, Material material, ItemStack itemStack){
        this.ticks = ticks;
        this.itemStack = itemStack;
        this.material = material;
    }

    BlockEnum(int ticks, Material material){
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

    public boolean hasItemStack(){
        return itemStack != null;
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
