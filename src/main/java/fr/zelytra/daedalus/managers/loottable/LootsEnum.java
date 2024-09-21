package fr.zelytra.daedalus.managers.loottable;

import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum LootsEnum {

  /*ARTEMIS LOOTS*/
  TIER1_DEPTHSTRIDER1(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DEPTH_STRIDER, 1)),
  TIER2_DEPTHSTRIDER2(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DEPTH_STRIDER, 2)),
  TIER3_DEPTHSTRIDER3(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.DEPTH_STRIDER, 3)),

  TIER1_EFFCIENCY1(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.EFFICIENCY, 1)),
  TIER2_EFFCIENCY2(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.EFFICIENCY, 2)),
  TIER3_EFFCIENCY3(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.EFFICIENCY, 3)),

  TIER1_FEATHERFALLING1(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.FEATHER_FALLING, 1)),
  TIER2_FEATHERFALLING2(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.FEATHER_FALLING, 2)),
  TIER3_FEATHERFALLING3(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.FEATHER_FALLING, 3)),

  TIER3_FIREASPECT(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.FIRE_ASPECT, 1)),
  TIER3_FLAME(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.FLAME, 1)),
  TIER3_INFINITY(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.FLAME, 1)),
  TIER2_MULTISHOT(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.MULTISHOT, 1)),

  TIER1_FIREPROTECTION1(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.FIRE_PROTECTION, 1)),
  TIER2_FIREPROTECTION2(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.FIRE_PROTECTION, 2)),
  TIER3_FIREPROTECTION3(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.FIRE_PROTECTION, 3)),

  TIER1_KNOCKBACK1(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.KNOCKBACK, 1)),
  TIER2_KNOCKBACK2(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.KNOCKBACK, 2)),

  TIER1_SEALUCK1(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.LUCK_OF_THE_SEA, 1)),
  TIER2_SEALUCK2(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.LUCK_OF_THE_SEA, 2)),
  TIER3_SEALUCK3(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.LUCK_OF_THE_SEA, 3)),

  TIER1_LURE1(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.LURE, 1)),
  TIER2_LURE2(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.LURE, 2)),
  TIER3_LURE3(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.LURE, 3)),

  TIER1_PIERCING1(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PIERCING, 1)),
  TIER2_PIERCING2(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PIERCING, 2)),
  TIER3_PIERCING3(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PIERCING, 3)),

  TIER1_POWER1(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.POWER, 1)),
  TIER2_POWER2(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.POWER, 2)),
  TIER3_POWER3(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.POWER, 3)),

  TIER1_PROJECTILPROTECTION1(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROJECTILE_PROTECTION, 1)),
  TIER2_PROJECTILPROTECTION2(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROJECTILE_PROTECTION, 2)),
  TIER3_PROJECTILPROTECTION3(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROJECTILE_PROTECTION, 3)),

  TIER1_PROTECTION1(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION, 1)),
  TIER2_PROTECTION2(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION, 2)),
  TIER3_PROTECTION3(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PROTECTION, 3)),

  TIER2_PUNCH1(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PUNCH, 1)),
  TIER3_PUNCH2(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.PUNCH, 2)),

  TIER1_QUICKCHARGE1(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.QUICK_CHARGE, 1)),
  TIER2_QUICKCHARGE2(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.QUICK_CHARGE, 2)),
  TIER3_QUICKCHARGE3(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.QUICK_CHARGE, 3)),

  TIER1_SHARPNESS1(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.SHARPNESS, 1)),
  TIER2_SHARPNESS2(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.SHARPNESS, 2)),
  TIER3_SHARPNESS3(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.SHARPNESS, 3)),

  TIER1_SWEEPING1(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.SWEEPING_EDGE, 1)),
  TIER2_SWEEPING2(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.SWEEPING_EDGE, 2)),
  TIER3_SWEEPING3(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.SWEEPING_EDGE, 3)),

  TIER2_THORNS1(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.THORNS, 1)),
  TIER3_THORNS2(Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.THORNS, 2)),

  TIER1_UNBREAKING1(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.UNBREAKING, 1)),
  TIER2_UNBREAKING2(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.UNBREAKING, 2)),
  TIER3_UNBREAKING3(
      Utils.BookEnchantedItemStack(Material.ENCHANTED_BOOK, Enchantment.UNBREAKING, 3)),

  /*DIONYSOS POTION LOOTS*/
  TIER1_INSTANTHEAL1(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0, true, false, true)),
  TIER2_INSTANTHEAL2(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 1, true, false, true)),

  TIER1_NIGHTVISION1(new PotionEffect(PotionEffectType.NIGHT_VISION, 1200, 0, true, false, true)),
  TIER2_NIGHTVISION2(new PotionEffect(PotionEffectType.NIGHT_VISION, 1800, 0, true, false, true)),

  TIER1_SPEED1(new PotionEffect(PotionEffectType.SPEED, 1200, 0, true, false, true)),
  TIER2_SPEED2(new PotionEffect(PotionEffectType.SPEED, 1200, 1, true, false, true)),

  TIER1_STRENGTH1(new PotionEffect(PotionEffectType.STRENGTH, 900, 0, true, false, true)),
  TIER2_STRENGTH2(new PotionEffect(PotionEffectType.STRENGTH, 1200, 0, true, false, true)),

  TIER1_REGEN1(new PotionEffect(PotionEffectType.REGENERATION, 400, 0, true, false, true)),
  TIER2_REGEN2(new PotionEffect(PotionEffectType.REGENERATION, 400, 1, true, false, true)),

  TIER1_FIRERESSISTANCE1(
      new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1200, 0, true, false, true)),
  TIER2_FIRERESSISTANCE2(
      new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1800, 0, true, false, true)),

  TIER1_JUMP1(new PotionEffect(PotionEffectType.JUMP_BOOST, 1800, 0, true, false, true)),
  TIER2_JUMP2(new PotionEffect(PotionEffectType.JUMP_BOOST, 1800, 1, true, false, true)),

  WATER(new PotionEffect(PotionEffectType.WATER_BREATHING, 2400, 0, true, false, true)),

  TIER1_HAST1(new PotionEffect(PotionEffectType.HASTE, 1800, 0, true, false, true)),
  TIER2_HAST2(new PotionEffect(PotionEffectType.HASTE, 2400, 1, true, false, true)),

  TIER2_INVISIBILITY1(new PotionEffect(PotionEffectType.INVISIBILITY, 1200, 0, true, false, true)),
  NAUSEA(new PotionEffect(PotionEffectType.NAUSEA, 300, 4, true, false, true));

  private ItemStack item;
  private PotionEffect potionEffect;

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
}
