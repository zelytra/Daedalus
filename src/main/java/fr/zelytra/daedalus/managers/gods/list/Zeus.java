package fr.zelytra.daedalus.managers.gods.list;

import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.Gods;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Collection;

public class Zeus implements Gods {

    public Zeus(Faction faction) {
        init(faction);
        Player god = faction.getGod();
        ArrayList<Player> playerList = (ArrayList<Player>) faction.getPlayerList().clone();
        playerList.remove(god.getUniqueId());
        for (Player player : playerList) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(24.0);
        }
    }

    @Override
    public ArrayList<ItemStack>godItems() {
        ArrayList<ItemStack> items = new ArrayList();
        items.add(new CustomItemStack(CustomMaterial.ZEUS_LIGHTNING,1).getItem());
        return items;
    }

    @Override
    public ArrayList<ItemStack> teamItems() {
        return null;
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
