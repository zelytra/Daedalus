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

public class Demeter implements Gods {

	public Demeter(Faction team) {
		init(team);
	}

	public Demeter() {
	}

	@Override
	public double teamHeart() {
		return 20;
	}

	@Override
	public ArrayList<ItemStack> godItems() {
		ArrayList<ItemStack> items = new ArrayList<>();
		items.add(new CustomItemStack(CustomMaterial.DEMETER_SICKLE).getItem());
		ItemStack hoe = new ItemStack(Material.NETHERITE_HOE);
		ItemMeta meta = hoe.getItemMeta();
		meta.addEnchant(Enchantment.KNOCKBACK, 7, true);

		PersistentDataContainer itemData = meta.getPersistentDataContainer();
		itemData.set(CustomItemStack.getItemKey(), PersistentDataType.STRING, "teamsItem");

		hoe.setItemMeta(meta);
		items.add(hoe);
		return items;
	}

	@Override
	public ArrayList<ItemStack> teamItems() {

		ArrayList<ItemStack> items = new ArrayList<>();
		ItemStack hoe = new ItemStack(Material.NETHERITE_HOE);
		ItemMeta meta = hoe.getItemMeta();
		meta.addEnchant(Enchantment.KNOCKBACK, 3, true);

		PersistentDataContainer itemData = meta.getPersistentDataContainer();
		itemData.set(CustomItemStack.getItemKey(), PersistentDataType.STRING, "teamsItem");

		hoe.setItemMeta(meta);
		items.add(hoe);
		return items;
	}

	@Override
	public Collection<PotionEffect> godEffects() {
		Collection<PotionEffect> potions = new ArrayList<>();
		potions.add(new PotionEffect(PotionEffectType.REGENERATION, 99999999, 0, false, false, true));
		potions.add(new PotionEffect(PotionEffectType.SATURATION, 99999999, 0, false, false, true));
		return potions;
	}

	@Override
	public Collection<PotionEffect> teamEffects() {
		Collection<PotionEffect> potions = new ArrayList<>();
		potions.add(new PotionEffect(PotionEffectType.SATURATION, 99999999, 0, false, false, true));
		return potions;
	}
}
