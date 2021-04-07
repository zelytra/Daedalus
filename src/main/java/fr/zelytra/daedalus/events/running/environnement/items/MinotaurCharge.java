package fr.zelytra.daedalus.events.running.environnement.items;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.team.Team;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class MinotaurCharge implements Listener {
    private BukkitTask taskID;

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        final int itemCooldown = 3;
        final int radius = 2;


        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if ((e.getHand() == EquipmentSlot.HAND && CustomItemStack.hasCustomItemInMainHand(CustomMaterial.MINOTAUR_CHARGE.getName(), e.getPlayer())) || (e.getHand() == EquipmentSlot.OFF_HAND && CustomItemStack.hasCustomItemInOffHand(CustomMaterial.MINOTAUR_CHARGE.getName(), e.getPlayer()))) {
                    Player player = e.getPlayer();

                    //Cooldown check
                    Cooldown toRemove = null;
                    for (Map.Entry<Cooldown, Player> entry : Cooldown.cooldownsList.entrySet()) {
                        if (entry.getKey().getTag().equalsIgnoreCase(CustomMaterial.MINOTAUR_CHARGE.getName()) && entry.getValue().getUniqueId() == player.getUniqueId()) {
                            toRemove = entry.getKey();
                            if (!toRemove.isFinished()) {
                                player.sendMessage(Message.getPlayerPrefixe() + "§6You need to wait " + toRemove.toString());
                                return;
                            }

                        }
                    }
                    Cooldown.cooldownsList.remove(toRemove);
                    Cooldown cd = new Cooldown(player, itemCooldown, CustomMaterial.MINOTAUR_CHARGE.getName());
                    Cooldown.cooldownsList.put(cd, player);
                    //Item action
                    final int chargeCoef = 12;
                    final double yCoef = 0.3;
                    final double thresholdVelocity = 0.05;
                    Vector dir = new Vector(player.getLocation().getDirection().getX() * chargeCoef, yCoef, player.getLocation().getDirection().getZ() * chargeCoef);
                    player.setVelocity(dir);

                    this.taskID = Bukkit.getScheduler().runTaskTimer(Daedalus.getInstance(), () -> {
                        if (Math.abs(e.getPlayer().getVelocity().getX()) <= thresholdVelocity || Math.abs(e.getPlayer().getVelocity().getZ()) <= thresholdVelocity) {
                            cancelTask();
                        }
                        try {
                            Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(player.getUniqueId());
                            Collection<Entity> nearbyEntities = player.getWorld().getNearbyEntities(player.getLocation(), radius, radius, radius);
                            Collection<Entity> toStrike = new ArrayList<>();
                            for (Entity entity : nearbyEntities) {
                                if (entity instanceof Player) {
                                    Player target = (Player) entity;
                                    Team targetPlayerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(target.getUniqueId());
                                    if (targetPlayerTeam.getTeamEnum() == playerTeam.getTeamEnum()) {
                                        continue;
                                    }
                                    toStrike.add(entity);
                                } else if (entity instanceof LivingEntity) {
                                    toStrike.add(entity);
                                }
                            }
                            for (Entity entity : toStrike) {
                                Vector delta = new Vector(entity.getLocation().getX() - player.getLocation().getX(), 0, entity.getLocation().getZ() - player.getLocation().getZ());
                                double norme = Math.sqrt(Math.pow(delta.getX(), 2) + Math.pow(delta.getY(), 2) + Math.pow(delta.getZ(), 2));
                                int coef = 2;
                                Vector direction = new Vector((delta.getX() / norme) * coef, (delta.getY() / norme) + 1.5, (delta.getZ() / norme) * coef);
                                entity.setVelocity(direction);
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                            System.out.println("ERROR team not found");
                        }


                    }, 0, 1);

                }
            }
        }
    }

    private void cancelTask() {
        Bukkit.getServer().getScheduler().cancelTask(this.taskID.getTaskId());
    }
}
