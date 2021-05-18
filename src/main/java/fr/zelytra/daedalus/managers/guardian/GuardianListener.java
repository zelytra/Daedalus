package fr.zelytra.daedalus.managers.guardian;

import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.List;

public class GuardianListener implements Listener {

    @EventHandler
    public void onGuardianDamage(EntityDamageEvent e) {
        if (!Guardian.isGuardian((LivingEntity) e.getEntity())) {
            return;
        }
        Guardian guardian = Guardian.getGuardianFromList((LivingEntity) e.getEntity());
        guardian.getBossBar().setProgress((guardian.getEntity().getHealth()) / guardian.getHealth());
    }

    @EventHandler
    public void onGuardianTarget(EntityTargetEvent e) {
        if (e.getEntityType() != EntityType.VINDICATOR) {
            return;
        }
        if (!Guardian.isGuardian((LivingEntity) e.getEntity())) {
            return;
        }
        LivingEntity entity = (LivingEntity) e.getEntity();
        Guardian guardian = Guardian.getGuardianFromList(entity);

        List<Entity> targets = entity.getNearbyEntities(50, 50, 50);
        for (Entity target : targets) {
            if (target instanceof Player) {
                if (getDistance(target.getLocation(), guardian.getEntity().getLocation()) <= 30)
                    guardian.getBossBar().addPlayer((Player) target);
                else {
                    if (guardian.getBossBar().getPlayers().contains((Player) target)) {
                        guardian.getBossBar().removePlayer((Player) target);
                    }
                }

            }
        }
    }

    @EventHandler
    public void onGuardianDeath(EntityDeathEvent e) {
        if (!Guardian.isGuardian(e.getEntity())) {
            return;
        }
        Guardian guardian = Guardian.getGuardianFromList(e.getEntity());
        guardian.death();
        e.setDroppedExp(150);
        e.getDrops().removeAll(e.getDrops());
        e.getDrops().add(new CustomItemStack(CustomMaterial.DIVINE_HEART, 1).getItem());
    }

    private double getDistance(Location loc1, Location loc2) {
        double deltaX = loc1.getX() - loc2.getX();
        double deltaZ = (loc1.getZ() - loc2.getZ());
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
    }
}
