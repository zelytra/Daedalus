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
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Hermes implements Gods {

    public Hermes(Team team) {
        init(team);
        Player god = team.getGod();
        ArrayList<UUID> playerList = (ArrayList<UUID>) team.getPlayerList().clone();
        god.setAllowFlight(true);
        playerList.remove(god.getUniqueId());
        for (UUID uuid : playerList) {
            Player player = Bukkit.getPlayer(uuid);
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(24.0);
        }
    }

    @Override
    public ArrayList<ItemStack> godItems() {
        ArrayList<ItemStack> items = new ArrayList<>();
        items.add(new CustomItemStack(CustomMaterial.HERMES_TRUC).getItem());
        return items;
    }

    @Override
    public ArrayList<ItemStack> teamItems() {
        return null;
    }

    @Override
    public Collection<PotionEffect> godEffects() {
        Collection<PotionEffect> potions = new ArrayList<>();
        potions.add(new PotionEffect(PotionEffectType.SPEED,99999999,1,false,false));
        return potions;
    }

    @Override
    public Collection<PotionEffect> teamEffects() {
        Collection<PotionEffect> potions = new ArrayList<>();
        potions.add(new PotionEffect(PotionEffectType.SPEED,99999999,0,false,false));
        potions.add(new PotionEffect(PotionEffectType.SATURATION,99999999,9,false,false));
        return potions;
    }
}
