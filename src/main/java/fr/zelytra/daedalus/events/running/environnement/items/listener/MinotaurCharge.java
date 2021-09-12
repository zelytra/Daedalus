package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;

public class MinotaurCharge implements Listener {
    private BukkitTask taskID;
    private long timeOut;

    @EventHandler
    public void onRightClick(CustomItemUseEvent e) {
        final int itemCooldown = 45;
        final int radius = 2;

        if (e.getMaterial() != CustomMaterial.MINOTAUR_CHARGE) return;

        Player player = e.getPlayer();

        if (player.getLocation().getY() >= 93) {
            return;
        }


        //Cooldown check
        if (!Cooldown.cooldownCheck(player, CustomMaterial.MINOTAUR_CHARGE.getName())) {
            return;
        }
        new Cooldown(player, itemCooldown, CustomMaterial.MINOTAUR_CHARGE.getName());

        Location location = player.getLocation();
        Location loc2 = e.getPlayer().getLocation().clone();
        loc2.setY(loc2.getY() - 0.5);
        location.setY(location.getY() + 0.5);
        location.getWorld().spawnParticle(org.bukkit.Particle.BLOCK_DUST, location, 250, loc2.getBlock().getBlockData());

        //Item action
        final int chargeCoef = 4;
        final double yCoef = 0.3;
        final double thresholdVelocity = 0.1;
        //Charge direction vector
        double radianYaw = player.getEyeLocation().getYaw();

        if (radianYaw > 180) {
            radianYaw -= 360;
        } else if (radianYaw < -180) {
            radianYaw += 360;
        }
        radianYaw *= Math.PI / 180.0;
        Vector dir = new Vector(-Math.sin(radianYaw) * chargeCoef, yCoef, Math.cos(radianYaw) * chargeCoef);
        player.setVelocity(dir);

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_DAMAGE, 1, 0.5f);
            p.playSound(player.getLocation(), Sound.BLOCK_SHROOMLIGHT_BREAK, 2, 0.5f);
        }


        this.timeOut = System.currentTimeMillis();
        this.taskID = Bukkit.getScheduler().runTaskTimer(Daedalus.getInstance(), () -> {
            if (Math.abs(e.getPlayer().getVelocity().getX()) <= thresholdVelocity || Math.abs(e.getPlayer().getVelocity().getZ()) <= thresholdVelocity) {
                cancelTask();
            }
            try {
                Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
                Collection<Entity> nearbyEntities = player.getWorld().getNearbyEntities(player.getLocation(), radius, radius, radius);
                Collection<Entity> toStrike = new ArrayList<>();

                for (Entity entity : nearbyEntities) {
                    if (entity instanceof Player && ((Player) entity).getGameMode() == GameMode.SURVIVAL) {

                        Player target = (Player) entity;
                        Faction targetPlayerTeam = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(target);

                        if (targetPlayerTeam.getType() == playerFaction.getType())
                            continue;

                        toStrike.add(entity);
                    }else if (!(entity instanceof Player)){
                        toStrike.add(entity);
                    }
                }
                for (Entity entity : toStrike) {
                    //Entity air ejection
                    Vector delta = new Vector(entity.getLocation().getX() - player.getLocation().getX(), 0, entity.getLocation().getZ() - player.getLocation().getZ());
                    double norme = Math.sqrt(Math.pow(delta.getX(), 2) + Math.pow(delta.getY(), 2) + Math.pow(delta.getZ(), 2));
                    int coef = 2;

                    Vector direction = new Vector((delta.getX() / norme) * coef, (delta.getY() / norme) + 1.5, (delta.getZ() / norme) * coef);
                    entity.setVelocity(direction);
                    ((LivingEntity) entity).damage(4.0);
                }

                if (System.currentTimeMillis() - this.timeOut >= 5000) {
                    cancelTask();
                }
            } catch (Exception exception) {
                System.out.println("ERROR team not found");
            }

        }, 0, 1);


    }

    private void cancelTask() {
        Bukkit.getServer().getScheduler().cancelTask(this.taskID.getTaskId());
    }
}
