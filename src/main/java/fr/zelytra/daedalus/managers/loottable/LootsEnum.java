package fr.zelytra.daedalus.managers.loottable;

import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum LootsEnum {

    /*STRUCTURE LOOTS*/
    COOKED_PORKCHOP(new ItemStack(Material.COOKED_PORKCHOP, 5), 5),
    COOKED_FISH(new ItemStack(Material.COOKED_COD, 3), 5),
    COOKED_SALMON(new ItemStack(Material.COOKED_SALMON, 3), 5),
    COOKED_BEEF(new ItemStack(Material.COOKED_BEEF, 5), 5),
    COOKED_CHICKEN(new ItemStack(Material.COOKED_CHICKEN, 1), 5),
    COOKED_MUTTON(new ItemStack(Material.COOKED_MUTTON, 1), 5),
    COOKED_RABBIT(new ItemStack(Material.COOKED_RABBIT, 1), 5),
    OAK_PLANKS(new ItemStack(Material.OAK_PLANKS, 1), 5),
    STICK(new ItemStack(Material.STICK, 1), 5),
    STRING(new ItemStack(Material.STRING, 1), 4),
    LEATHER(new ItemStack(Material.LEATHER, 1), 4),
    PAPER(new ItemStack(Material.PAPER, 1), 4),
    WHEAT(new ItemStack(Material.WHEAT, 1), 4),
    FEATHER(new ItemStack(Material.FEATHER, 1), 3),
    FLINT(new ItemStack(Material.FLINT, 1), 3),
    ARROW(new ItemStack(Material.ARROW, 1), 3),
    GLASS_BOTTLE(new ItemStack(Material.GLASS_BOTTLE, 2), 3),
    APPLE(new ItemStack(Material.APPLE, 2), 0.029),
    IRON_NUGGET(new ItemStack(Material.IRON_NUGGET, 6), 2),
    COAL(new ItemStack(Material.COAL, 1), 2),
    BOOK(new ItemStack(Material.BOOK, 1), 2),
    PUMPKIN_SEEDS(new ItemStack(Material.PUMPKIN_SEEDS, 1), 2),
    MELON_SEEDS(new ItemStack(Material.MELON_SEEDS, 1), 2),
    COCOA_BEANS(new ItemStack(Material.COCOA_BEANS, 1), 2),
    IRON_INGOT(new ItemStack(Material.IRON_INGOT, 1), 2),
    GOLD_NUGGET(new ItemStack(Material.GOLD_NUGGET, 1), 2),
    BUCKET(new ItemStack(Material.BUCKET, 1), 1),
    EGG(new ItemStack(Material.EGG, 1), 1),
    BOW(new ItemStack(Material.BOW, 1), 1),
    CROSSBOW(new ItemStack(Material.CROSSBOW, 1), 1),
    SHIELD(new ItemStack(Material.SHIELD, 1), 1),
    IRON_PICKAXE(new ItemStack(Material.IRON_PICKAXE, 1), 0.7),
    IRON_AXE(new ItemStack(Material.IRON_AXE, 1), 0.7),
    GOLD_INGOT(new ItemStack(Material.GOLD_INGOT, 1), 0.5),
    IRON_SWORD(new ItemStack(Material.IRON_SWORD, 1), 0.5),
    EXPERIENCE_BOTTLE(new ItemStack(Material.EXPERIENCE_BOTTLE, 1), 0.5),

    CHAINMAIL_HELMET(new ItemStack(Material.CHAINMAIL_HELMET, 1), 0.3),
    CHAINMAIL_CHESTPLATE(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1), 0.3),
    CHAINMAIL_LEGGINGS(new ItemStack(Material.CHAINMAIL_LEGGINGS, 1), 0.3),
    CHAINMAIL_BOOTS(new ItemStack(Material.CHAINMAIL_BOOTS, 1), 0.3),

    IRON_HELMET(new ItemStack(Material.IRON_HELMET, 1), 0.1),
    IRON_CHESTPLATE(new ItemStack(Material.IRON_CHESTPLATE, 1), 0.1),
    IRON_LEGGINGS(new ItemStack(Material.IRON_LEGGINGS, 1), 0.1),
    IRON_BOOTS(new ItemStack(Material.IRON_BOOTS, 1), 0.1),
    GOLDEN_APPLE(new ItemStack(Material.GOLDEN_APPLE, 1), 0.1),

    /*ARTEMIS LOOTS*/
    TIER1_DEPTHSTRIDER1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DEPTH_STRIDER, 1)),
    TIER2_DEPTHSTRIDER2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DEPTH_STRIDER, 2)),
    TIER3_DEPTHSTRIDER3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DEPTH_STRIDER, 3)),

    TIER1_EFFCIENCY1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DIG_SPEED, 1)),
    TIER2_EFFCIENCY2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DIG_SPEED, 2)),
    TIER3_EFFCIENCY3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DIG_SPEED, 3)),

    TIER1_FEATHERFALLING1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION_FALL, 1)),
    TIER2_FEATHERFALLING2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION_FALL, 2)),
    TIER3_FEATHERFALLING3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION_FALL, 3)),

    TIER3_FIREASPECT(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.FIRE_ASPECT, 1)),
    TIER3_FLAME(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.ARROW_FIRE, 1)),
    TIER3_INFINITY(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.ARROW_INFINITE, 1)),
    TIER2_MULTISHOT(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.MULTISHOT, 1)),

    TIER1_FIREPROTECTION1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION_FIRE, 1)),
    TIER2_FIREPROTECTION2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION_FIRE, 2)),
    TIER3_FIREPROTECTION3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION_FIRE, 3)),

    TIER1_KNOCKBACK1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.KNOCKBACK, 1)),
    TIER2_KNOCKBACK2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.KNOCKBACK, 2)),

    TIER1_SEALUCK1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.LUCK, 1)),
    TIER2_SEALUCK2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.LUCK, 2)),
    TIER3_SEALUCK3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.LUCK, 3)),

    TIER1_LURE1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.LURE, 1)),
    TIER2_LURE2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.LURE, 2)),
    TIER3_LURE3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.LURE, 3)),

    TIER1_PIERCING1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PIERCING, 1)),
    TIER2_PIERCING2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PIERCING, 2)),
    TIER3_PIERCING3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PIERCING, 3)),

    TIER1_POWER1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.ARROW_DAMAGE, 1)),
    TIER2_POWER2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.ARROW_DAMAGE, 2)),
    TIER3_POWER3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.ARROW_DAMAGE, 3)),

    TIER1_PROJECTILPROTECTION1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION_PROJECTILE, 1)),
    TIER2_PROJECTILPROTECTION2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION_PROJECTILE, 2)),
    TIER3_PROJECTILPROTECTION3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION_PROJECTILE, 3)),

    TIER1_PROTECTION1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION_ENVIRONMENTAL, 1)),
    TIER2_PROTECTION2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION_ENVIRONMENTAL, 2)),
    TIER3_PROTECTION3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION_ENVIRONMENTAL, 3)),

    TIER2_PUNCH1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.ARROW_KNOCKBACK, 1)),
    TIER3_PUNCH2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.ARROW_KNOCKBACK, 2)),

    TIER1_QUICKCHARGE1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.QUICK_CHARGE, 1)),
    TIER2_QUICKCHARGE2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.QUICK_CHARGE, 2)),
    TIER3_QUICKCHARGE3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.QUICK_CHARGE, 3)),

    TIER1_SHARPNESS1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DAMAGE_ALL, 1)),
    TIER2_SHARPNESS2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DAMAGE_ALL, 2)),
    TIER3_SHARPNESS3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DAMAGE_ALL, 3)),

    TIER1_SWEEPING1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.SWEEPING_EDGE, 1)),
    TIER2_SWEEPING2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.SWEEPING_EDGE, 2)),
    TIER3_SWEEPING3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.SWEEPING_EDGE, 3)),

    TIER2_THORNS1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.THORNS, 1)),
    TIER3_THORNS2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.THORNS, 2)),

    TIER1_UNBREAKING1(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DURABILITY, 1)),
    TIER2_UNBREAKING2(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DURABILITY, 2)),
    TIER3_UNBREAKING3(Utils.EnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DURABILITY, 3)),

    /*DIONYSOS POTION LOOTS*/
    TIER1_INSTANTHEAL1(new PotionEffect(PotionEffectType.HEAL, 1, 0, true, false, true)),
    TIER2_INSTANTHEAL2(new PotionEffect(PotionEffectType.HEAL, 1, 1, true, false, true)),

    TIER1_NIGHTVISION1(new PotionEffect(PotionEffectType.NIGHT_VISION, 1200, 0, true, false, true)),
    TIER2_NIGHTVISION2(new PotionEffect(PotionEffectType.NIGHT_VISION, 1800, 0, true, false, true)),

    TIER1_SPEED1(new PotionEffect(PotionEffectType.SPEED, 1200, 0, true, false, true)),
    TIER2_SPEED2(new PotionEffect(PotionEffectType.SPEED, 1200, 1, true, false, true)),

    TIER1_STRENGTH1(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 900, 0, true, false, true)),
    TIER2_STRENGTH2(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 0, true, false, true)),

    TIER1_REGEN1(new PotionEffect(PotionEffectType.REGENERATION, 400, 0, true, false, true)),
    TIER2_REGEN2(new PotionEffect(PotionEffectType.REGENERATION, 400, 1, true, false, true)),

    TIER1_FIRERESSISTANCE1(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1200, 0, true, false, true)),
    TIER2_FIRERESSISTANCE2(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1800, 0, true, false, true)),

    TIER1_JUMP1(new PotionEffect(PotionEffectType.JUMP, 1800, 0, true, false, true)),
    TIER2_JUMP2(new PotionEffect(PotionEffectType.JUMP, 1800, 1, true, false, true)),

    WATER(new PotionEffect(PotionEffectType.WATER_BREATHING, 2400, 0, true, false, true)),

    TIER1_HAST1(new PotionEffect(PotionEffectType.FAST_DIGGING, 1800, 0, true, false, true)),
    TIER2_HAST2(new PotionEffect(PotionEffectType.FAST_DIGGING, 2400, 1, true, false, true)),

    TIER2_INVISIBILITY1(new PotionEffect(PotionEffectType.INVISIBILITY, 1200, 0, true, false, true)),
    NAUSEA(new PotionEffect(PotionEffectType.CONFUSION, 300, 4, true, false, true));


    private ItemStack item;
    private double luck;
    private PotionEffect potionEffect;

    LootsEnum(ItemStack item, double luck) {
        this.item = item;
        this.luck = luck / 100;
    }

    LootsEnum(ItemStack item) {
        this.item = item;

    }

    LootsEnum(PotionEffect potion) {
        this.potionEffect = potion;
    }

    public ItemStack getItem() {
        return item;
    }

    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    public double getLuck() {
        return luck;
    }
}
