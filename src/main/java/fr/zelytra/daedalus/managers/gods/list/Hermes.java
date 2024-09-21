package fr.zelytra.daedalus.managers.gods.list;

import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.Gods;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Hermes implements Gods {

  public Hermes(Faction faction) {
    init(faction);
    Player god = faction.getGod();
    ArrayList<Player> playerList = (ArrayList<Player>) faction.getPlayerList().clone();
    god.setAllowFlight(true);
    playerList.remove(god.getUniqueId());
  }

  public Hermes() {}

  @Override
  public double teamHeart() {
    return 24;
  }

  @Override
  public ArrayList<ItemStack> godItems() {
    ArrayList<ItemStack> items = new ArrayList<>();
    items.add(new CustomItemStack(CustomMaterial.HERMES_CADUCEUS).getItem());
    return items;
  }

  @Override
  public ArrayList<ItemStack> teamItems() {
    return null;
  }

  @Override
  public Collection<PotionEffect> godEffects() {
    Collection<PotionEffect> potions = new ArrayList<>();
    potions.add(new PotionEffect(PotionEffectType.SPEED, 99999999, 1, false, false, true));
    potions.add(new PotionEffect(PotionEffectType.SATURATION, 99999999, 9, false, false, true));
    return potions;
  }

  @Override
  public Collection<PotionEffect> teamEffects() {
    Collection<PotionEffect> potions = new ArrayList<>();
    potions.add(new PotionEffect(PotionEffectType.SPEED, 99999999, 0, false, false, true));
    return potions;
  }
}
