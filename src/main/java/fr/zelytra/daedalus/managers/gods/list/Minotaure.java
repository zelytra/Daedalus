package fr.zelytra.daedalus.managers.gods.list;

import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.Gods;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;

public class Minotaure implements Gods {

    public Minotaure(Faction faction) {
        init(faction);
        Player god = faction.getGod();
        ArrayList<Player> playerList = (ArrayList<Player>) faction.getPlayerList().clone();
        playerList.remove(god.getUniqueId());

        ItemStack item = new CustomItemStack(CustomMaterial.MINOTAUR_HEAD).getItem();
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.BINDING_CURSE,1,false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        faction.getGod().getInventory().setHelmet(item);

    }

    public Minotaure(){}

    @Override
    public double teamHeart() {
        return 30;
    }

    @Override
    public ArrayList<ItemStack> godItems() {
        ArrayList<ItemStack> items = new ArrayList<>();
        items.add(new CustomItemStack(CustomMaterial.MINOTAUR_CHARGE).getItem());
        return items;
    }

    @Override
    public ArrayList<ItemStack> teamItems() {
        return null;
    }

    @Override
    public Collection<PotionEffect> godEffects() {
        Collection<PotionEffect> potions = new ArrayList<>();
        potions.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999999, 0, false, false, true));
        return potions;
    }

    @Override
    public Collection<PotionEffect> teamEffects() {
        return null;
    }
}
