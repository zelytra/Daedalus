package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class MedusaHead implements Listener {
    private static final ArrayList<Player> petrifiedPlayer = new ArrayList<>();
    final int freezeTeammate = 15;
    final int freezeEnemy = 5;

    final int cooldownTeammate = 20;
    final int cooldownEnemy = 60;

    @EventHandler
    public void playerMove(PlayerMoveEvent e) {
        //TODO Evenement test

        if (!Daedalus.getInstance().getGameManager().isRunning()) {
            return;
        }
        Player player = e.getPlayer();
        final String petrifiedTag = "petrified";
        if (petrifiedPlayer.contains(player)) {
            if (!Cooldown.cooldownCheck(player, petrifiedTag)) {
                e.setCancelled(true);
                return;
            }
        }
        Player target = getLookingEntity(player);
        if (target != null && target.getInventory().getHelmet() != null && CustomItemStack.hasTag(target.getInventory().getHelmet(), CustomMaterial.MEDUSA_HEAD)) {
            if (!Cooldown.cooldownCheck(target, CustomMaterial.MEDUSA_HEAD.getName())) {
                return;
            }
            petrifiedPlayer.add(player);
            player.getWorld().spawnParticle(Particle.MOB_APPEARANCE, player.getLocation(), 10);
            try {
                Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
                Faction targetTeam = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(target);
                if (playerFaction == targetTeam) {
                    new Cooldown(player, freezeTeammate, petrifiedTag);
                    new Cooldown(target, cooldownTeammate, CustomMaterial.MEDUSA_HEAD.getName());
                } else {
                    new Cooldown(player, freezeEnemy, petrifiedTag);
                    new Cooldown(target, cooldownEnemy, CustomMaterial.MEDUSA_HEAD.getName());
                }
            } catch (Exception exception) {
                System.out.println("ERROR team not found");
            }
        }
    }

    private Player getLookingEntity(Player player) {
        final int range = 50;
        ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(range, range, range);
        ArrayList<Block> sightBlock = (ArrayList<Block>) player.getLineOfSight(null, range);
        ArrayList<Location> sight = new ArrayList<>();
        for (Block block : sightBlock) {
            sight.add(block.getLocation());
        }
        for (Location location : sight) {
            for (Entity entity : entities) {
                if (Math.abs(entity.getLocation().getX() - location.getX()) < 1.3) {
                    if (Math.abs(entity.getLocation().getY() - location.getY()) < 1.5) {
                        if (Math.abs(entity.getLocation().getZ() - location.getZ()) < 1.3) {
                            if (entity instanceof Player) {
                                return (Player) entity;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
