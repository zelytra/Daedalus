package fr.zelytra.daedalus.events.running.environnement;

import fr.zelytra.daedalus.managers.game.settings.BlockCooldown;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum BlockEnum {

    IRON_ORE(BlockCooldown.IRON_ORE.getSeconds(), Material.IRON_ORE, new ItemStack(Material.IRON_INGOT)),
    GOLD_ORE(BlockCooldown.GOLD_ORE.getSeconds(), Material.GOLD_ORE, new ItemStack(Material.GOLD_INGOT)),
    END_STONE(BlockCooldown.END_STONE.getSeconds(), Material.END_STONE),
    OBSIDIAN(BlockCooldown.OBSIDIAN.getSeconds(), Material.OBSIDIAN),
    DIAMOND_ORE(BlockCooldown.DIAMOND_ORE.getSeconds(), Material.DIAMOND_ORE),
    EMERALD_ORE(BlockCooldown.EMERALD_ORE.getSeconds(), Material.EMERALD_ORE),
    COAL_ORE(BlockCooldown.COAL_ORE.getSeconds(), Material.COAL_ORE),
    LAPIS_ORE(BlockCooldown.LAPIS_ORE.getSeconds(), Material.LAPIS_ORE),
    ANCIENT_DEBRIS(BlockCooldown.ANCIENT_DEBRIS.getSeconds(), Material.ANCIENT_DEBRIS),
    REDSTONE_ORE(BlockCooldown.REDSTONE_ORE.getSeconds(), Material.REDSTONE_ORE),
    OAK_LOG(BlockCooldown.WOOD.getSeconds(), Material.OAK_LOG, new ItemStack(Material.OAK_PLANKS, 4)),
    SPRUCE_LOG(BlockCooldown.WOOD.getSeconds(), Material.SPRUCE_LOG, new ItemStack(Material.SPRUCE_PLANKS, 4)),
    DARK_OAK_LOG(BlockCooldown.WOOD.getSeconds(), Material.DARK_OAK_LOG, new ItemStack(Material.DARK_OAK_PLANKS, 4)),
    ACACIA_LOG(BlockCooldown.WOOD.getSeconds(), Material.ACACIA_LOG, new ItemStack(Material.ACACIA_PLANKS, 4)),
    BIRCH_LOG(BlockCooldown.WOOD.getSeconds(), Material.BIRCH_LOG, new ItemStack(Material.BIRCH_PLANKS, 4)),
    JUNGLE_LOG(BlockCooldown.WOOD.getSeconds(), Material.JUNGLE_LOG, new ItemStack(Material.JUNGLE_PLANKS, 4)),
    OAK_WOOD(BlockCooldown.WOOD.getSeconds(), Material.OAK_WOOD, new ItemStack(Material.OAK_PLANKS, 4)),
    SPRUCE_WOOD(BlockCooldown.WOOD.getSeconds(), Material.SPRUCE_WOOD, new ItemStack(Material.SPRUCE_PLANKS, 4)),
    DARK_OAK_WOOD(BlockCooldown.WOOD.getSeconds(), Material.DARK_OAK_WOOD, new ItemStack(Material.DARK_OAK_PLANKS, 4)),
    ACACIA_WOOD(BlockCooldown.WOOD.getSeconds(), Material.ACACIA_WOOD, new ItemStack(Material.ACACIA_PLANKS, 4)),
    BIRCH_WOOD(BlockCooldown.WOOD.getSeconds(), Material.BIRCH_WOOD, new ItemStack(Material.BIRCH_PLANKS, 4)),
    JUNGLE_WOOD(BlockCooldown.WOOD.getSeconds(), Material.JUNGLE_WOOD, new ItemStack(Material.JUNGLE_PLANKS, 4)),
    JUNGLE_LEAVES(BlockCooldown.LEAVES.getSeconds(), Material.JUNGLE_LEAVES, new ItemStack(Material.APPLE)),
    OAK_LEAVES(BlockCooldown.LEAVES.getSeconds(), Material.OAK_LEAVES, new ItemStack(Material.APPLE)),
    DARK_OAK_LEAVES(BlockCooldown.LEAVES.getSeconds(), Material.DARK_OAK_LEAVES, new ItemStack(Material.APPLE)),
    ACACIA_LEAVES(BlockCooldown.LEAVES.getSeconds(), Material.ACACIA_LEAVES, new ItemStack(Material.APPLE)),
    SPRUCE_LEAVES(BlockCooldown.LEAVES.getSeconds(), Material.SPRUCE_LEAVES, new ItemStack(Material.APPLE)),
    BIRCH_LEAVES(BlockCooldown.LEAVES.getSeconds(), Material.BIRCH_LEAVES, new ItemStack(Material.APPLE));

    private final int seconds;
    private final Material material;
    private ItemStack itemStack;

    BlockEnum(int seconds, Material material, ItemStack itemStack){
        this.seconds = seconds;
        this.itemStack = itemStack;
        this.material = material;
    }

    BlockEnum(int seconds, Material material){
        this.seconds = seconds;
        this.material = material;
    }

    public int getSeconds() {
        return seconds;
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
