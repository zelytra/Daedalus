package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class HadesScepter implements Listener {
    private static final NamespacedKey hadesKey = new NamespacedKey(Daedalus.getInstance(), "hades");

    @EventHandler
    public void onRightClick(CustomItemUseEvent e) {
        int itemCooldown = 30;
        int skeletonNumber = 2;

        if (e.getMaterial() != CustomMaterial.HADES_SCEPTER) return;

        Player player = e.getPlayer();

        //Cooldown check
        if (!Cooldown.cooldownCheck(player, CustomMaterial.HADES_SCEPTER.getName())) return;

        new Cooldown(player, itemCooldown, CustomMaterial.HADES_SCEPTER.getName());

        List<Entity> skeleton = e.getPlayer().getNearbyEntities(15, 15, 15);
        int count = 0;
        for (Entity entity : skeleton) {
            if (entity instanceof WitherSkeleton)
                count++;

            if (entity instanceof WitherSkeleton && count > 2)
                entity.remove();
        }

        try {
            Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
            //Item action
            for (int x = 1; x <= skeletonNumber; x++) {

                Location spawnLoc = player.getLocation();
                spawnLoc.setY(spawnLoc.getY() + 1);
                spawnLoc.setX(new Random().nextInt((int) ((spawnLoc.getX() + 2) - (spawnLoc.getX() - 2))) + (spawnLoc.getX() - 2));
                spawnLoc.setZ(new Random().nextInt((int) ((spawnLoc.getZ() + 2) - (spawnLoc.getZ() - 2))) + (spawnLoc.getZ() - 2));
                Entity entity = player.getWorld().spawnEntity(spawnLoc, EntityType.WITHER_SKELETON);
                PersistentDataContainer pdc = entity.getPersistentDataContainer();
                pdc.set(hadesKey, PersistentDataType.STRING, playerFaction.getType().getName());
                entity.getWorld().spawnParticle(Particle.SOUL, entity.getLocation(), 300, 0.1, 0.1, 0.1, 0.1);
                ((WitherSkeleton) entity).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
                ((WitherSkeleton) entity).setHealth(((WitherSkeleton) entity).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());


            }
        } catch (Exception exception) {
            System.out.println("ERROR team not found");
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
                    if (target instanceof WitherSkeleton) continue;

                    if (target instanceof Player) {
                        Player targetedPlayer = (Player) target;

                        if (targetedPlayer.getGameMode() != GameMode.SURVIVAL) continue;

                        Faction targetPlayerTeam = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(targetedPlayer);

                        if (targetPlayerTeam.getType().getName().equals(pdc.get(hadesKey, PersistentDataType.STRING))) {
                            System.out.println("isHadesPlayer");
                            continue;
                        }
                        toTargetPlayer.add(target);

                    } else if (!(target instanceof Player))
                        toTarget.add(target);

                }

                if (!toTargetPlayer.isEmpty()) {
                    e.setTarget(toTargetPlayer.get((int) (Math.random() * toTargetPlayer.size())));

                } else if (!toTarget.isEmpty()) {
                    e.setTarget(toTarget.get((int) (Math.random() * toTarget.size())));

                } else {
                    e.setTarget(null);
                    e.setCancelled(true);
                }
                for (Entity entity1 : toTarget)
                    System.out.println(entity1);

                for (Entity entity1 : toTargetPlayer)
                    System.out.println(entity1);

                System.out.println("Target:"+ e.getTarget());
                System.out.println("");
                System.out.println("");

            }
        }
    }

}
