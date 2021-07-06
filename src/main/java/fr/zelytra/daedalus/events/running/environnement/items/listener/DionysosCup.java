package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;

public class DionysosCup implements Listener {

    @EventHandler
    public void onRightClick(CustomItemUseEvent e) {
        int itemCooldown = 90;
        int effectRadius = 10;
        if (e.getMaterial() != CustomMaterial.DIONYSOS_CUP) return;
        Player player = e.getPlayer();

        //Item action
        try {
            Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
            Collection<Entity> nearbyEntity = player.getNearbyEntities(effectRadius, effectRadius, effectRadius);
            ArrayList<Entity> targetList = new ArrayList<>();
            for (Entity entity : nearbyEntity) {
                if (entity instanceof LivingEntity) {
                    if (entity instanceof Player && ((Player) entity).getGameMode() == GameMode.SURVIVAL) {
                        Player target = (Player) entity;
                        Faction targetPlayerTeam = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(target);
                        if (targetPlayerTeam.getType() == playerFaction.getType()) {
                            continue;
                        }
                        targetList.add(entity);
                    }
                }
            }

            if (targetList.isEmpty()) {
                player.sendMessage(Message.getPlayerPrefixe() + "§6There's no player to cursed around you");
                return;
            }
            //Cooldown check
            if (!Cooldown.cooldownCheck(player, CustomMaterial.DIONYSOS_CUP.getName())) {
                return;
            }
            Cooldown cd = new Cooldown(player, itemCooldown, CustomMaterial.DIONYSOS_CUP.getName());


            for (Entity entity : targetList) {
                ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 0));
            }

        } catch (Exception exception) {
            System.out.println("ERROR team not found");
        }


    }


}
