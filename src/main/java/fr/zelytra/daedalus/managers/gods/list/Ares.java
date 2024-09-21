package fr.zelytra.daedalus.managers.gods.list;

import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.Gods;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Ares implements Gods {

  public Ares(Faction team) {
    init(team);
  }

  public Ares() {}

  @Override
  public double teamHeart() {
    return 20;
  }

  @Override
  public ArrayList<ItemStack> godItems() {
    ArrayList<ItemStack> items = new ArrayList<>();
    items.add(new CustomItemStack(CustomMaterial.ARES_WAR_BANNER).getItem());

    ItemStack item = new ItemStack(Material.NETHERITE_CHESTPLATE);
    item.addEnchantment(Enchantment.THORNS, 3);
    item.addEnchantment(Enchantment.PROTECTION, 3);
    ItemMeta meta = item.getItemMeta();
    meta.setUnbreakable(true);
    item.setItemMeta(meta);
    items.add(item);

    return items;
  }

  @Override
  public ArrayList<ItemStack> teamItems() {
    ArrayList<ItemStack> items = new ArrayList<>();

    ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
    item.addEnchantment(Enchantment.UNBREAKING, 2);
    item.addEnchantment(Enchantment.PROTECTION, 2);
    ItemMeta meta = item.getItemMeta();
    PersistentDataContainer itemData = meta.getPersistentDataContainer();
    itemData.set(CustomItemStack.getItemKey(), PersistentDataType.STRING, "teamsItem");
    item.setItemMeta(meta);
    items.add(item);

    return items;
  }

  @Override
  public Collection<PotionEffect> godEffects() {
    Collection<PotionEffect> potions = new ArrayList<>();
    potions.add(new PotionEffect(PotionEffectType.STRENGTH, 99999999, 0, false, false, true));
    return potions;
  }

  @Override
  public Collection<PotionEffect> teamEffects() {
    return null;
  }
}
