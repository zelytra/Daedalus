package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Random;

public class Particle implements Listener {

    @EventHandler
    public void test(CustomItemUseEvent e) {
        if (e.getMaterial() != CustomMaterial.PARTICLE) return;
        Player player = e.getPlayer();

        Location location = player.getLocation();
        location.setY(location.getY()+1.8);


        Location spawnLoc = player.getLocation();
        spawnLoc.setY(spawnLoc.getY() + 1);

        spawnLoc.setX(new Random().nextInt((int) ((spawnLoc.getX() + 2) - (spawnLoc.getX() - 2))) + (spawnLoc.getX() - 2));
        spawnLoc.setZ(new Random().nextInt((int) ((spawnLoc.getZ() + 2) - (spawnLoc.getZ() - 2))) + (spawnLoc.getZ() - 2));
        System.out.println(spawnLoc);
        location.getWorld().spawnParticle(org.bukkit.Particle.VILLAGER_ANGRY, spawnLoc, 15);

    }
}
