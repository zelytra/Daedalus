package fr.zelytra.daedalus.events.running.environnement.items;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.team.Team;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class HadesScepter implements Listener {
    private static final NamespacedKey hadesKey = new NamespacedKey(Daedalus.getInstance(), "hades");

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        int itemCooldown = 3;
        int skeletonNumber = 2;
        int spawnRadius = 2;
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if ((e.getHand() == EquipmentSlot.HAND && CustomItemStack.hasCustomItemInMainHand(CustomMaterial.HADES_SCEPTER.getName(), e.getPlayer())) || (e.getHand() == EquipmentSlot.OFF_HAND && CustomItemStack.hasCustomItemInOffHand(CustomMaterial.ZEUS_LIGHTNING.getName(), e.getPlayer()))) {
                    Player player = e.getPlayer();

                    //Cooldown check
                    for (Map.Entry<Cooldown, Player> entry : Cooldown.cooldownsList.entrySet()) {
                        if (entry.getKey().getTag().equalsIgnoreCase(CustomMaterial.HADES_SCEPTER.getName()) && entry.getValue().getUniqueId() == player.getUniqueId()) {
                            Cooldown cd = entry.getKey();
                            if (!cd.isFinished()) {
                                player.sendMessage(Message.getPlayerPrefixe() + "§6You need to wait " + cd.toString());
                                return;
                            }
                            Cooldown.cooldownsList.remove(cd);
                        }
                    }

                    Cooldown cd = new Cooldown(player, itemCooldown, CustomMaterial.HADES_SCEPTER.getName());
                    Cooldown.cooldownsList.put(cd, player);
                    try {
                        Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(player.getUniqueId());
                        //Item action
                        for (int x = 1; x <= skeletonNumber; x++) {
                            Location spawnLoc = player.getLocation();
                            spawnLoc.setY(spawnLoc.getY() + 1);
                            spawnLoc.setX((int) (Math.random() * (spawnLoc.getX() + spawnRadius - spawnLoc.getX() - spawnRadius)) + (spawnLoc.getX() - spawnRadius));
                            spawnLoc.setZ((int) (Math.random() * (spawnLoc.getZ() + spawnRadius - spawnLoc.getZ() - spawnRadius)) + (spawnLoc.getZ() - spawnRadius));
                            Entity entity = player.getWorld().spawnEntity(player.getLocation(), EntityType.WITHER_SKELETON);
                            PersistentDataContainer pdc = entity.getPersistentDataContainer();
                            pdc.set(hadesKey, PersistentDataType.STRING, playerTeam.getTeamEnum().getName());

                        }
                    } catch (Exception exception) {
                        System.out.println("ERROR team not found");
                    }


                }
            }
        }
    }

    @EventHandler
    public void mobTarget(EntityTargetEvent e) {
        int radiusTarget = 8;
        Entity entity = e.getEntity();
        if (entity.getType() == EntityType.WITHER_SKELETON) {
            PersistentDataContainer pdc = entity.getPersistentDataContainer();
            if (pdc.has(hadesKey, PersistentDataType.STRING)) {

                Collection<Entity> nearbyEntities = entity.getWorld().getNearbyEntities(entity.getLocation(), radiusTarget, radiusTarget, radiusTarget);
                ArrayList<Entity> toTargetPlayer = new ArrayList<>();
                ArrayList<Entity> toTarget = new ArrayList<>();

                for (Entity target : nearbyEntities) {
                    if (entity instanceof Player) {
                        Player targetedPlayer = (Player) entity;
                        Team targetPlayerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(targetedPlayer.getUniqueId());
                        if (targetPlayerTeam.getTeamEnum().getName().equals(pdc.get(hadesKey, PersistentDataType.STRING))) {
                            continue;
                        }
                        toTargetPlayer.add(target);
                    } else if (target instanceof LivingEntity) {
                        if (target.getType() != EntityType.WITHER_SKELETON) {
                            toTarget.add(entity);
                        }
                    }
                }
                if (toTargetPlayer.isEmpty()) {
                    e.setTarget(toTarget.get((int) (Math.random() * toTarget.size())));
                } else {
                    e.setTarget(toTargetPlayer.get((int) (Math.random() * toTargetPlayer.size())));
                }
            }
        }
    }

}
