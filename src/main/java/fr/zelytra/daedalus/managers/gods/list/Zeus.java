package fr.zelytra.daedalus.managers.gods.list;

import fr.zelytra.daedalus.managers.gods.Gods;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Zeus implements Gods {

    public Zeus(Team team) {
        init(team);
        Player god = team.getGod();
        ArrayList<UUID> playerList = (ArrayList<UUID>) team.getPlayerList().clone();
        playerList.remove(god.getUniqueId());
        for (UUID uuid : playerList) {
            Player player = Bukkit.getPlayer(uuid);
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
