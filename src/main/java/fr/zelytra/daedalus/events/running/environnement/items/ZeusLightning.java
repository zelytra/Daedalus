package fr.zelytra.daedalus.events.running.environnement.items;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.game.GameStatesEnum;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.team.Team;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Collection;
import java.util.Map;

public class ZeusLightning implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        int itemCooldown = 30;
        if (Daedalus.getInstance().getGameManager().getState() == GameStatesEnum.RUNNING) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if ((e.getHand() == EquipmentSlot.HAND && CustomItemStack.hasCustomItemInMainHand(CustomMaterial.ZEUS_LIGHTNING.getName(), e.getPlayer())) || (e.getHand() == EquipmentSlot.OFF_HAND && CustomItemStack.hasCustomItemInOffHand(CustomMaterial.ZEUS_LIGHTNING.getName(), e.getPlayer()))) {
                    Player player = e.getPlayer();

                    //Cooldown check
                    for (Map.Entry<Cooldown, Player> entry : Cooldown.cooldownsList.entrySet()) {
                        if (entry.getKey().getTag().equalsIgnoreCase(CustomMaterial.ZEUS_LIGHTNING.getName()) && entry.getValue().getUniqueId() == player.getUniqueId()) {
                            Cooldown cd = entry.getKey();
                            if (!cd.isFinished()) {
                                player.sendMessage("§6You need to wait " + cd.toString());
                                return;
                            }
                            Cooldown.cooldownsList.remove(player);
                        }
                    }

                    Cooldown cd = new Cooldown(player, itemCooldown, CustomMaterial.ZEUS_LIGHTNING.getName());
                    Cooldown.cooldownsList.put(cd, player);

                    //Item action
                    try {
                        Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(player.getUniqueId());

                        Collection<Entity> nearbyEntities = player.getWorld().getNearbyEntities(player.getLocation(), 3, 3, 3);

                        for (Entity entity : nearbyEntities) {
                            if (entity instanceof Player) {
                                Player target = (Player) entity;
                                Team targetPlayerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(target.getUniqueId());
                                if (targetPlayerTeam.getTeamEnum() == playerTeam.getTeamEnum()) {
                                    continue;
                                }
                            }
                            if (entity instanceof LivingEntity) {
                                player.getWorld().strikeLightningEffect(entity.getLocation());
                                ((LivingEntity) entity).damage(4.0);
                                entity.setFireTicks(80);
                            }

                        }
                    } catch (Exception exception) {
                        System.out.println("ERROR team not found");
                        return;
                    }
                }
            }
        }
    }
}
