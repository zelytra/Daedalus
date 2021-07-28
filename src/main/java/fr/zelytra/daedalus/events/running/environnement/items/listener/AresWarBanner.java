package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AresWarBanner implements Listener {
    private int itemCooldown = 60;


    @EventHandler
    public void onItemUse(CustomItemUseEvent e) {

        if (e.getMaterial() != CustomMaterial.ARES_WAR_BANNER) return;

        if (!Cooldown.cooldownCheck(e.getPlayer(), CustomMaterial.ARES_WAR_BANNER.getName())) return;

        try {
            Faction faction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(e.getPlayer());

            if (faction.getGodsEnum() == GodsEnum.ARES) {
                for (Player player : faction.getPlayerList()) {
                    if (player.getName() != faction.getGod().getName()) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 140, 0));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 0));
                        player.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 2, 1);
                        player.sendMessage(Message.getPlayerPrefixe() + "§6You feel a litle bit stronger");
                    }

                }
            }
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 2, 1);
            e.getPlayer().sendMessage(Message.getPlayerPrefixe() + "§6You use your war banner");
            new Cooldown(e.getPlayer(), itemCooldown, CustomMaterial.ARES_WAR_BANNER.getName());

        } catch (Exception exception) {
            System.out.println("ERROR team not found");
        }

    }
}
