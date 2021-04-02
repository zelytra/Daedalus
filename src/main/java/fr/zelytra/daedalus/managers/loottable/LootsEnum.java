package fr.zelytra.daedalus.managers.loottable;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum LootsEnum {
    COOKED_PORKCHOP(new ItemStack(Material.COOKED_PORKCHOP,5), 0.05),
    COOKED_FISH(new ItemStack(Material.COOKED_COD,3), 0.05),
    COOKED_SALMON(new ItemStack(Material.COOKED_SALMON,3), 0.05),
    COOKED_BEEF(new ItemStack(Material.COOKED_BEEF,5), 0.05),
    COOKED_CHICKEN(new ItemStack(Material.COOKED_CHICKEN,1), 0.05),
    COOKED_MUTTON(new ItemStack(Material.COOKED_MUTTON,1), 0.05),
    COOKED_RABBIT(new ItemStack(Material.COOKED_RABBIT,1), 0.05),

    OAK_PLANKS(new ItemStack(Material.OAK_PLANKS,1), 0.05),
    STICK(new ItemStack(Material.STICK,1), 0.05),
    STRING(new ItemStack(Material.STRING,1), 0.04),
    LEATHER(new ItemStack(Material.LEATHER,1), 0.04),
    PAPER(new ItemStack(Material.PAPER,1), 0.04),
    WHEAT(new ItemStack(Material.WHEAT,1), 0.04),
    FEATHER(new ItemStack(Material.FEATHER,1), 0.03),
    FLINT(new ItemStack(Material.FLINT,1), 0.03),
    ARROW(new ItemStack(Material.ARROW,1), 0.03),
    GLASS_BOTTLE(new ItemStack(Material.GLASS_BOTTLE,2), 0.03),
    APPLE(new ItemStack(Material.APPLE,2), 0.029),
    IRON_NUGGET(new ItemStack(Material.IRON_NUGGET,6), 0.02),
    COAL(new ItemStack(Material.COAL,1), 0.02),
    BOOK(new ItemStack(Material.BOOK,1), 0.02),
    PUMPKIN_SEEDS(new ItemStack(Material.PUMPKIN_SEEDS,1), 0.02),
    MELON_SEEDS(new ItemStack(Material.MELON_SEEDS,1), 0.02),
    COCOA_BEANS(new ItemStack(Material.COCOA_BEANS,1), 0.02),
    IRON_INGOT(new ItemStack(Material.IRON_INGOT,1), 0.015),
    GOLD_NUGGET(new ItemStack(Material.GOLD_NUGGET,1), 0.01),
    BUCKET(new ItemStack(Material.BUCKET,1), 0.01),
    EGG(new ItemStack(Material.EGG,1), 0.01),
    BOW(new ItemStack(Material.BOW,1), 0.01),
    CROSSBOW(new ItemStack(Material.CROSSBOW,1), 0.01),
    SHIELD(new ItemStack(Material.SHIELD,1), 0.01),
    IRON_PICKAXE(new ItemStack(Material.IRON_PICKAXE,1), 0.007),
    IRON_AXE(new ItemStack(Material.IRON_AXE,1), 0.007),
    GOLD_INGOT(new ItemStack(Material.GOLD_INGOT,1), 0.005),
    IRON_SWORD(new ItemStack(Material.IRON_SWORD,1), 0.005),
    EXPERIENCE_BOTTLE(new ItemStack(Material.EXPERIENCE_BOTTLE,1), 0.005),

    CHAINMAIL_HELMET(new ItemStack(Material.CHAINMAIL_HELMET,1), 0.003),
    CHAINMAIL_CHESTPLATE(new ItemStack(Material.CHAINMAIL_CHESTPLATE,1), 0.003),
    CHAINMAIL_LEGGINGS(new ItemStack(Material.CHAINMAIL_LEGGINGS,1), 0.003),
    CHAINMAIL_BOOTS(new ItemStack(Material.CHAINMAIL_BOOTS,1), 0.003),

    IRON_HELMET(new ItemStack(Material.IRON_HELMET,1), 0.001),
    IRON_CHESTPLATE(new ItemStack(Material.IRON_CHESTPLATE,1), 0.001),
    IRON_LEGGINGS(new ItemStack(Material.IRON_LEGGINGS,1), 0.001),
    IRON_BOOTS(new ItemStack(Material.IRON_BOOTS,1), 0.001),
    GOLDEN_APPLE(new ItemStack(Material.GOLDEN_APPLE,1), 0.001);

    private ItemStack item;
    private double luck;

    LootsEnum(ItemStack item, double luck) {
        this.item = item;
        this.luck = luck;
    }

    public ItemStack getItem() {
        return item;
    }

    public double getLuck() {
        return luck;
    }
}
