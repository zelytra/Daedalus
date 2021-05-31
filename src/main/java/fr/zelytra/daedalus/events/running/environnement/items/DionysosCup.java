package fr.zelytra.daedalus.events.running.environnement.items;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;

public class DionysosCup implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        int itemCooldown = 90;
        int effectRadius = 10;

        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if ((e.getHand() == EquipmentSlot.HAND && CustomItemStack.hasCustomItemInMainHand(CustomMaterial.DIONYSOS_CUP.getName(), e.getPlayer())) || (e.getHand() == EquipmentSlot.OFF_HAND && CustomItemStack.hasCustomItemInOffHand(CustomMaterial.DIONYSOS_CUP.getName(), e.getPlayer()))) {
                    Player player = e.getPlayer();

                    //Item action
                    try {
                        Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
                        Collection<Entity> nearbyEntity = player.getNearbyEntities(effectRadius, effectRadius, effectRadius);
                        ArrayList<Entity> targetList = new ArrayList<>();
                        for (Entity entity : nearbyEntity) {
                            if (entity instanceof LivingEntity) {
                                if (entity instanceof Player) {
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
                        if(!Cooldown.cooldownCheck(player,CustomMaterial.DIONYSOS_CUP.getName())){
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
        }
    }


}
