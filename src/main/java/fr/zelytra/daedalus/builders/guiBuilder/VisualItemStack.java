package fr.zelytra.daedalus.builders.guiBuilder;

import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VisualItemStack {
	private ItemStack item;

	public VisualItemStack(Material material, String name, boolean isEnchanted, String... subMessage) {
		this.item = new ItemStack(material);
		ItemMeta meta = this.item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(subMessage));
		if (isEnchanted) {
			item.addEnchantment(Enchantment.POWER, 1);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		item.setItemMeta(meta);
	}

	public VisualItemStack(CustomMaterial material, String name, boolean isEnchanted, String... subMessage) {
		this.item = new CustomItemStack(material, 1).getItem();
		ItemMeta meta = this.item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(subMessage));
		if (isEnchanted) {
			item.addEnchantment(Enchantment.POWER, 1);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		item.setItemMeta(meta);
	}

	public VisualItemStack(Material material, String name, boolean isEnchanted) {
		this.item = new ItemStack(material);
		ItemMeta meta = this.item.getItemMeta();
		meta.setDisplayName(name);
		if (isEnchanted) {
			item.addEnchantment(Enchantment.POWER, 1);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		item.setItemMeta(meta);
	}

	public VisualItemStack(CustomMaterial material, String name, boolean isEnchanted) {
		this.item = new CustomItemStack(material, 1).getItem();
		ItemMeta meta = this.item.getItemMeta();
		meta.setDisplayName(name);
		if (isEnchanted) {
			item.addEnchantment(Enchantment.POWER, 1);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		item.setItemMeta(meta);
	}

	public ItemStack getItem() {
		return item;
	}

	public void addAmount() {
		this.item.setAmount(this.item.getAmount() + 1);
	}

	public void removeAmount() {
		if (this.item.getAmount() - 1 > 1) {
			this.item.setAmount(this.item.getAmount() - 1);
		}
	}
}
