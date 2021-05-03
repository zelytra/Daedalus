package fr.zelytra.daedalus.managers.gods.list;

import fr.zelytra.daedalus.managers.gods.Gods;
import fr.zelytra.daedalus.managers.team.Team;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;

public class Ares implements Gods {

    public Ares(Team team) {
        init(team);
    }

    @Override
    public ArrayList<ItemStack> godItems() {
        ArrayList<ItemStack> items = new ArrayList<>();

        ItemStack item = new ItemStack(Material.NETHERITE_CHESTPLATE);
        item.addEnchantment(Enchantment.THORNS, 3);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        items.add(item);

        return items;
    }

    @Override
    public ArrayList<ItemStack> teamItems() {
        return null;
    }

    @Override
    public Collection<PotionEffect> godEffects() {
        Collection<PotionEffect> potions = new ArrayList<>();
        potions.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,99999999,0,false,false,true));
        return potions;
    }

    @Override
    public Collection<PotionEffect> teamEffects() {
        return null;
    }
}