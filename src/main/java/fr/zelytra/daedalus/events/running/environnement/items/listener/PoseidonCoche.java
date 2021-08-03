package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

import java.util.List;

public class PoseidonCoche implements Listener {

    @EventHandler
    public void onRightClick(CustomItemUseEvent e) {
        int itemCooldown = 30;

        if (e.getMaterial() != CustomMaterial.POSEIDON_COCHE) return;

        Player player = e.getPlayer();

        //Item action
        try {
            //Cooldown check
            if (!Cooldown.cooldownCheck(player, CustomMaterial.POSEIDON_COCHE.getName())) return;

            new Cooldown(player, itemCooldown, CustomMaterial.POSEIDON_COCHE.getName());

            Location center = e.getPlayer().getLocation().clone();

            List<Entity> tridents = e.getPlayer().getNearbyEntities(15, 15, 15);
            for (Entity entity : tridents)
                if (entity instanceof Trident)
                    if (((Trident) entity).isInBlock())
                        entity.remove();

            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.getInstance(), () -> {
                for (int size = 2; size <= 10; size++) {
                    double rand = Math.random() * 10;
                    for (double angle = rand; angle <= rand + 30; angle++) {
                        Location ballLoc = new Location(center.getWorld(), center.getX(), center.getY(), center.getZ());
                        ballLoc.setX(center.getX() + Math.cos(angle) * size);
                        ballLoc.setZ(center.getZ() + Math.sin(angle) * size);
                        ballLoc.setY(ballLoc.getY() + 10);
                        Bukkit.getScheduler().runTask(Daedalus.getInstance(), () -> {
                            Trident trident = (Trident) center.getWorld().spawnEntity(ballLoc, EntityType.TRIDENT);
                            trident.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                            trident.setVelocity(new Vector(0, -2, 0));
                        });

                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            });


        } catch (Exception exception) {
            System.out.println("ERROR team not found");
        }

    }

    @EventHandler
    public void onTridentDamage(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Trident && e.getHitEntity() instanceof Player) {
            Faction faction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf((Player) e.getHitEntity());
            if (faction.getGodsEnum() != null && faction.getGodsEnum() == GodsEnum.POSEIDON)
                e.setCancelled(true);
        }

    }


}
