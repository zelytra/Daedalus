package fr.zelytra.daedalus.events.running.environnement.items;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.team.Team;
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
import java.util.Map;

public class DemeterSickle implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        int itemCooldown = 60;
        int effectRadius = 10;

        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if ((e.getHand() == EquipmentSlot.HAND && CustomItemStack.hasCustomItemInMainHand(CustomMaterial.DEMETER_SICKLE.getName(), e.getPlayer())) || (e.getHand() == EquipmentSlot.OFF_HAND && CustomItemStack.hasCustomItemInOffHand(CustomMaterial.DEMETER_SICKLE.getName(), e.getPlayer()))) {
                    Player player = e.getPlayer();

                    //Item action
                    try {
                        Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(player.getUniqueId());
                        Collection<Entity> nearbyEntity = player.getNearbyEntities(effectRadius, effectRadius, effectRadius);
                        ArrayList<Entity> targetList = new ArrayList<>();
                        for (Entity entity : nearbyEntity) {
                            if (entity instanceof LivingEntity) {
                                if (entity instanceof Player) {
                                    Player target = (Player) entity;
                                    Team targetPlayerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(target.getUniqueId());
                                    if (targetPlayerTeam.getTeamEnum() == playerTeam.getTeamEnum()) {
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
                        Cooldown toRemove = null;
                        for (Map.Entry<Cooldown, Player> entry : Cooldown.cooldownsList.entrySet()) {
                            if (entry.getKey().getTag().equalsIgnoreCase(CustomMaterial.DEMETER_SICKLE.getName()) && entry.getValue().getUniqueId() == player.getUniqueId()) {
                                toRemove = entry.getKey();
                                if (!toRemove.isFinished()) {
                                    player.sendMessage(Message.getPlayerPrefixe() + "§6You need to wait " + toRemove.toString());
                                    return;
                                }

                            }
                        }
                        Cooldown.cooldownsList.remove(toRemove);
                        Cooldown cd = new Cooldown(player, itemCooldown, CustomMaterial.DEMETER_SICKLE.getName());
                        Cooldown.cooldownsList.put(cd, player);

                        for (Entity entity : targetList) {
                            ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 200, 1));
                        }

                    } catch (Exception exception) {
                        System.out.println("ERROR team not found");
                    }


                }
            }
        }
    }


}
