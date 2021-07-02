package fr.zelytra.daedalus.managers.gods;

import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Collection;

public interface Gods {
    default void init(Faction faction) {
        Player god = faction.getGod();
        ArrayList<Player> playerList = (ArrayList<Player>) faction.getPlayerList().clone();
        playerList.remove(god);
        god.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        //God init
        if (this.godEffects() != null) {
            god.addPotionEffects(this.godEffects());
        }
        if (this.godItems() != null) {
            for (ItemStack item : this.godItems()) {
                if (Utils.getEmptySlots(god) <= this.godItems().size()) {
                    god.getWorld().dropItem(god.getLocation(), item);
                }
                god.getInventory().addItem(item);
            }
        }

        //Player init
        for (Player player : playerList) {

            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(teamHeart());


            if (this.teamEffects() != null) {
                player.addPotionEffects(this.teamEffects());
            }
            if (this.teamItems() != null) {
                for (ItemStack item : this.teamItems()) {
                    if (Utils.getEmptySlots(player) <= this.teamItems().size()) {
                        player.getWorld().dropItem(player.getLocation(), item);
                    }
                    player.getInventory().addItem(item);
                }
            }
        }


    }

    double teamHeart();

    ArrayList<ItemStack> godItems();

    ArrayList<ItemStack> teamItems();

    Collection<PotionEffect> godEffects();

    Collection<PotionEffect> teamEffects();

}
