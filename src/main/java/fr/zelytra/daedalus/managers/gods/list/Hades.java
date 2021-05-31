package fr.zelytra.daedalus.managers.gods.list;

import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.Gods;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;

public class Hades implements Gods {

    public Hades(Faction faction) {
        init(faction);
        Player god = faction.getGod();
        ArrayList<Player> playerList = (ArrayList<Player>) faction.getPlayerList().clone();
        playerList.remove(god.getUniqueId());
        for (Player player : playerList) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(22.0);
        }
    }

    @Override
    public ArrayList<ItemStack> godItems() {
        ArrayList<ItemStack> items = new ArrayList<>();
        items.add(new CustomItemStack(CustomMaterial.HADES_SCEPTER).getItem());
        return items;
    }

    @Override
    public ArrayList<ItemStack> teamItems() {
        return null;
    }

    @Override
    public Collection<PotionEffect> godEffects() {
        Collection<PotionEffect> potions = new ArrayList<>();
        potions.add(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,99999999,0,false,false,true));
        return potions;
    }

    @Override
    public Collection<PotionEffect> teamEffects() {
        Collection<PotionEffect> potions = new ArrayList<>();
        potions.add(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,99999999,0,false,false,true));
        return potions;
    }
}
