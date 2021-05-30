package fr.zelytra.daedalus.events.running.environnement.items;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
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

public class HadesScepter implements Listener {
    private static final NamespacedKey hadesKey = new NamespacedKey(Daedalus.getInstance(), "hades");

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        int itemCooldown = 120;
        int skeletonNumber = 2;
        int spawnRadius = 3;
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if ((e.getHand() == EquipmentSlot.HAND && CustomItemStack.hasCustomItemInMainHand(CustomMaterial.HADES_SCEPTER.getName(), e.getPlayer())) || (e.getHand() == EquipmentSlot.OFF_HAND && CustomItemStack.hasCustomItemInOffHand(CustomMaterial.HADES_SCEPTER.getName(), e.getPlayer()))) {
                    Player player = e.getPlayer();

                    //Cooldown check
                    if(!Cooldown.cooldownCheck(player,CustomMaterial.HADES_SCEPTER.getName())){
                        return;
                    }
                    Cooldown cd = new Cooldown(player, itemCooldown, CustomMaterial.HADES_SCEPTER.getName());

                    try {
                        Faction playerTeam = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
                        //Item action
                        for (int x = 1; x <= skeletonNumber; x++) {
                            Location spawnLoc = player.getLocation();
                            spawnLoc.setY(spawnLoc.getY() + 1);
                            spawnLoc.setX((int) (Math.random() * (spawnLoc.getX() + spawnRadius - spawnLoc.getX() - spawnRadius)) + (spawnLoc.getX() - spawnRadius));
                            spawnLoc.setZ((int) (Math.random() * (spawnLoc.getZ() + spawnRadius - spawnLoc.getZ() - spawnRadius)) + (spawnLoc.getZ() - spawnRadius));
                            Entity entity = player.getWorld().spawnEntity(player.getLocation(), EntityType.WITHER_SKELETON);
                            PersistentDataContainer pdc = entity.getPersistentDataContainer();
                            pdc.set(hadesKey, PersistentDataType.STRING, playerTeam.getType().getName());

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
        int radiusTarget = 10;
        Entity entity = e.getEntity();
        if (entity.getType() == EntityType.WITHER_SKELETON) {
            PersistentDataContainer pdc = entity.getPersistentDataContainer();
            if (pdc.has(hadesKey, PersistentDataType.STRING)) {

                Collection<Entity> nearbyEntities = entity.getWorld().getNearbyEntities(entity.getLocation(), radiusTarget, radiusTarget, radiusTarget);
                ArrayList<Entity> toTargetPlayer = new ArrayList<>();
                ArrayList<Entity> toTarget = new ArrayList<>();

                for (Entity target : nearbyEntities) {
                    if (target instanceof LivingEntity) {
                        if (target instanceof Player) {
                            Player targetedPlayer = (Player) target;
                            Faction targetPlayerTeam = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(targetedPlayer);
                            if (!targetPlayerTeam.getType().getName().equals(pdc.get(hadesKey, PersistentDataType.STRING))) {
                                toTargetPlayer.add(target);
                            }
                        } else if (!(target instanceof WitherSkeleton)) {
                            toTarget.add(target);
                        }

                    }
                }

                if (toTargetPlayer.isEmpty()) {
                    if (toTarget.isEmpty()) {
                        e.setCancelled(true);
                        return;
                    }
                    e.setTarget(toTarget.get((int) (Math.random() * toTarget.size())));
                } else {
                    e.setTarget(toTargetPlayer.get((int) (Math.random() * toTargetPlayer.size())));
                }
            }
        }
    }

}
