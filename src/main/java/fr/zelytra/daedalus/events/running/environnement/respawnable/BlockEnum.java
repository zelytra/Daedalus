package fr.zelytra.daedalus.events.running.environnement.respawnable;

import fr.zelytra.daedalus.managers.game.settings.BlockCooldownEnum;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum BlockEnum {

    IRON_ORE(BlockCooldownEnum.IRON_ORE.getSeconds(), Material.IRON_ORE, new ItemStack(Material.IRON_INGOT)),
    GOLD_ORE(BlockCooldownEnum.GOLD_ORE.getSeconds(), Material.GOLD_ORE, new ItemStack(Material.GOLD_INGOT)),
    END_STONE(BlockCooldownEnum.END_STONE.getSeconds(), Material.END_STONE, new CustomItemStack(CustomMaterial.DIVINE_FRAGMENT).getItem()),
    OBSIDIAN(BlockCooldownEnum.OBSIDIAN.getSeconds(), Material.OBSIDIAN),
    DIAMOND_ORE(BlockCooldownEnum.DIAMOND_ORE.getSeconds(), Material.DIAMOND_ORE),
    EMERALD_ORE(BlockCooldownEnum.EMERALD_ORE.getSeconds(), Material.EMERALD_ORE),
    COAL_ORE(BlockCooldownEnum.COAL_ORE.getSeconds(), Material.COAL_ORE),
    LAPIS_ORE(BlockCooldownEnum.LAPIS_ORE.getSeconds(), Material.LAPIS_ORE),
    ANCIENT_DEBRIS(BlockCooldownEnum.ANCIENT_DEBRIS.getSeconds(), Material.ANCIENT_DEBRIS, new ItemStack(Material.NETHERITE_SCRAP)),
    REDSTONE_ORE(BlockCooldownEnum.REDSTONE_ORE.getSeconds(), Material.REDSTONE_ORE),
    SAND(BlockCooldownEnum.SAND.getSeconds(), Material.SAND, new ItemStack(Material.GLASS)),
    RED_SAND(BlockCooldownEnum.RED_SAND.getSeconds(), Material.RED_SAND, new ItemStack(Material.GLASS)),
    STONE(BlockCooldownEnum.STONE.getSeconds(), Material.STONE, new ItemStack(Material.COBBLESTONE)),
    OAK_LOG(BlockCooldownEnum.WOOD.getSeconds(), Material.OAK_LOG, new ItemStack(Material.OAK_PLANKS, 4)),
    SPRUCE_LOG(BlockCooldownEnum.WOOD.getSeconds(), Material.SPRUCE_LOG, new ItemStack(Material.SPRUCE_PLANKS, 4)),
    DARK_OAK_LOG(BlockCooldownEnum.WOOD.getSeconds(), Material.DARK_OAK_LOG, new ItemStack(Material.DARK_OAK_PLANKS, 4)),
    ACACIA_LOG(BlockCooldownEnum.WOOD.getSeconds(), Material.ACACIA_LOG, new ItemStack(Material.ACACIA_PLANKS, 4)),
    BIRCH_LOG(BlockCooldownEnum.WOOD.getSeconds(), Material.BIRCH_LOG, new ItemStack(Material.BIRCH_PLANKS, 4)),
    JUNGLE_LOG(BlockCooldownEnum.WOOD.getSeconds(), Material.JUNGLE_LOG, new ItemStack(Material.JUNGLE_PLANKS, 4)),
    OAK_WOOD(BlockCooldownEnum.WOOD.getSeconds(), Material.OAK_WOOD, new ItemStack(Material.OAK_PLANKS, 4)),
    SPRUCE_WOOD(BlockCooldownEnum.WOOD.getSeconds(), Material.SPRUCE_WOOD, new ItemStack(Material.SPRUCE_PLANKS, 4)),
    DARK_OAK_WOOD(BlockCooldownEnum.WOOD.getSeconds(), Material.DARK_OAK_WOOD, new ItemStack(Material.DARK_OAK_PLANKS, 4)),
    ACACIA_WOOD(BlockCooldownEnum.WOOD.getSeconds(), Material.ACACIA_WOOD, new ItemStack(Material.ACACIA_PLANKS, 4)),
    BIRCH_WOOD(BlockCooldownEnum.WOOD.getSeconds(), Material.BIRCH_WOOD, new ItemStack(Material.BIRCH_PLANKS, 4)),
    JUNGLE_WOOD(BlockCooldownEnum.WOOD.getSeconds(), Material.JUNGLE_WOOD, new ItemStack(Material.JUNGLE_PLANKS, 4)),
    JUNGLE_LEAVES(BlockCooldownEnum.LEAVES.getSeconds(), Material.JUNGLE_LEAVES, new ItemStack(Material.APPLE)),
    OAK_LEAVES(BlockCooldownEnum.LEAVES.getSeconds(), Material.OAK_LEAVES, new ItemStack(Material.APPLE)),
    DARK_OAK_LEAVES(BlockCooldownEnum.LEAVES.getSeconds(), Material.DARK_OAK_LEAVES, new ItemStack(Material.APPLE)),
    ACACIA_LEAVES(BlockCooldownEnum.LEAVES.getSeconds(), Material.ACACIA_LEAVES, new ItemStack(Material.APPLE)),
    SPRUCE_LEAVES(BlockCooldownEnum.LEAVES.getSeconds(), Material.SPRUCE_LEAVES, new ItemStack(Material.APPLE)),
    BIRCH_LEAVES(BlockCooldownEnum.LEAVES.getSeconds(), Material.BIRCH_LEAVES, new ItemStack(Material.APPLE));

    private final int seconds;
    private final Material material;
    private ItemStack itemStack;

    BlockEnum(int seconds, Material material, ItemStack itemStack) {
        this.seconds = seconds;
        this.itemStack = itemStack;
        this.material = material;
    }

    BlockEnum(int seconds, Material material) {
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

    public boolean hasItemStack() {
        return itemStack != null;
    }

    public static boolean containType(Material material) {
        for (BlockEnum block : BlockEnum.values()) {
            if (block.getMaterial() == material) {
                return true;
            }
        }
        return false;
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
