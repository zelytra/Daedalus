package fr.zelytra.daedalus.events.running.environnement.mobCutclean;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public enum FoodEnum {
  MUTTON(EntityType.SHEEP, Material.MUTTON, Material.COOKED_MUTTON),
  PORK(EntityType.PIG, Material.PORKCHOP, Material.COOKED_PORKCHOP),
  RABBIT(EntityType.RABBIT, Material.RABBIT, Material.COOKED_RABBIT),
  COW(EntityType.COW, Material.BEEF, Material.COOKED_BEEF),
  CHICKEN(EntityType.CHICKEN, Material.CHICKEN, Material.COOKED_CHICKEN);

  private final Material raw;
  private final Material cooked;
  private final EntityType mob;

  FoodEnum(EntityType type, Material raw, Material cooked) {
    this.raw = raw;
    this.cooked = cooked;
    this.mob = type;
  }

  public Material getCooked() {
    return cooked;
  }

  public Material getRaw() {
    return raw;
  }

  public EntityType getMob() {
    return mob;
  }

  public static FoodEnum getByRaw(Material raw) {
    for (FoodEnum food : FoodEnum.values()) {
      if (food.getRaw() == raw) {
        return food;
      }
    }
    return null;
  }

  public static boolean containMob(EntityType entity) {
    for (FoodEnum food : FoodEnum.values()) {
      if (food.getMob() == entity) {
        return true;
      }
    }
    return false;
  }

  public static FoodEnum getByMob(EntityType entity) {
    for (FoodEnum food : FoodEnum.values()) {
      if (food.getMob() == entity) {
        return food;
      }
    }
    return null;
  }
}
