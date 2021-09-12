package fr.zelytra.daedalus.managers.gods.list;

import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.Gods;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Collection;

public class Athena implements Gods {

    public Athena(Faction team) {
        init(team);
    }

    public Athena(){}

    @Override
    public double teamHeart() {
        return 20;
    }

    @Override
    public ArrayList<ItemStack> godItems() {
        ArrayList<ItemStack> items = new ArrayList<>();

        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        item.addEnchantment(Enchantment.DAMAGE_ALL, 4);
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer itemData = meta.getPersistentDataContainer();
        itemData.set(CustomItemStack.getItemKey(), PersistentDataType.STRING, "teamsItem");
        item.setItemMeta(meta);
        items.add(item);

        item = new ItemStack(Material.SHIELD);
        meta = item.getItemMeta();
        meta.setUnbreakable(true);
        item.setItemMeta(meta);

        itemData = meta.getPersistentDataContainer();
        itemData.set(CustomItemStack.getItemKey(), PersistentDataType.STRING, "teamsItem");
        item.setItemMeta(meta);
        items.add(item);
        items.add(new CustomItemStack(CustomMaterial.ATHENA_MAP).getItem());
        return items;
    }

    @Override
    public ArrayList<ItemStack> teamItems() {
        ArrayList<ItemStack> items = new ArrayList<>();
        items.add(new ItemStack(Material.NETHERITE_SCRAP,3));
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
