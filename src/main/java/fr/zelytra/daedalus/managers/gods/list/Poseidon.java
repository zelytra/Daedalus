package fr.zelytra.daedalus.managers.gods.list;

import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.Gods;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Collection;

public class Poseidon implements Gods {

    public Poseidon(Faction faction) {
        init(faction);
        Player god = faction.getGod();
        ArrayList<Player> playerList = (ArrayList<Player>) faction.getPlayerList().clone();
        playerList.remove(god.getUniqueId());
    }

    public Poseidon(){}

    @Override
    public double teamHeart() {
        return 22.0;
    }

    @Override
    public ArrayList<ItemStack> godItems() {
        ArrayList<ItemStack> items = new ArrayList<>();

        ItemStack item = new ItemStack(Material.NETHERITE_BOOTS);
        item.addEnchantment(Enchantment.DEPTH_STRIDER, 3);
        item.addEnchantment(Enchantment.PROTECTION, 3);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);

        PersistentDataContainer itemData = meta.getPersistentDataContainer();
        itemData.set(CustomItemStack.getItemKey(), PersistentDataType.STRING, "teamsItem");

        item.setItemMeta(meta);
        items.add(item);

        item = new ItemStack(Material.TRIDENT);
        item.addEnchantment(Enchantment.LOYALTY, 3);
        item.addEnchantment(Enchantment.IMPALING, 3);
        item.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);

        meta = item.getItemMeta();
        itemData = meta.getPersistentDataContainer();
        itemData.set(CustomItemStack.getItemKey(), PersistentDataType.STRING, "teamsItem");
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        items.add(item);

        items.add(new CustomItemStack(CustomMaterial.POSEIDON_CONCH).getItem());
        return items;
    }

    @Override
    public ArrayList<ItemStack> teamItems() {
        ArrayList<ItemStack> items = new ArrayList<>();

        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS);
        item.addEnchantment(Enchantment.DEPTH_STRIDER, 1);
        item.addEnchantment(Enchantment.PROTECTION, 2);
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer itemData = meta.getPersistentDataContainer();
        itemData.set(CustomItemStack.getItemKey(), PersistentDataType.STRING, "teamsItem");
        item.setItemMeta(meta);
        items.add(item);

        item = new ItemStack(Material.TRIDENT);
        item.addEnchantment(Enchantment.LOYALTY, 3);
        meta = item.getItemMeta();
        itemData = meta.getPersistentDataContainer();
        itemData.set(CustomItemStack.getItemKey(), PersistentDataType.STRING, "teamsItem");
        item.setItemMeta(meta);
        items.add(item);
        return items;
    }

    @Override
    public Collection<PotionEffect> godEffects() {
        return null;
    }

    @Override
    public Collection<PotionEffect> teamEffects() {
        return null;
    }
}
