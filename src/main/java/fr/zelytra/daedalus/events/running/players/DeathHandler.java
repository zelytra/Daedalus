package fr.zelytra.daedalus.events.running.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.faction.FactionsEnum;
import fr.zelytra.daedalus.managers.faction.PlayerStatus;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DeathHandler implements Listener {
    private final List<CustomMaterial> whitelist = new ArrayList<>();
    private boolean hasMinotaureSpawn = false;
    private Daedalus daedalus = Daedalus.getInstance();

    {
        whitelist.add(CustomMaterial.DIVINE_FRAGMENT);
        whitelist.add(CustomMaterial.DIVINE_HEART);
        whitelist.add(CustomMaterial.DIVINE_TRACKER);
    }

    @EventHandler
    public void onPlayerDeath(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (daedalus.getGameManager().isRunning() || daedalus.getGameManager().isFinished()) {

            Player player = (Player) e.getEntity();
            if (((player.getHealth() - e.getFinalDamage()) > 0) || (e.getCause() == EntityDamageEvent.DamageCause.FALL && daedalus.getGameManager().getFactionManager().getFactionOf(player).getGod() != null && daedalus.getGameManager().getFactionManager().getFactionOf(player).getGodsEnum() == GodsEnum.ZEUS)) {
                return;
            }

            boolean isMinotaure = false;

            for (Faction team : daedalus.getGameManager().getFactionManager().getFactionList()) {
                if (team.getGodsEnum() == GodsEnum.MINOTAURE && team.getGod() != null) {
                    hasMinotaureSpawn = true;
                    isMinotaure = true;
                    break;
                }
            }
            if (daedalus.getGameManager().getFactionManager().getFactionOf(player).getGod() != null && daedalus.getGameManager().getFactionManager().getFactionOf(player).getGod().getUniqueId() == player.getUniqueId()) {
                isMinotaure = false;
                daedalus.getGameManager().getFactionManager().getFactionOf(player).removeGod();
                minotaursDeathFX();
            }

            e.setCancelled(true);
            player.setHealth(player.getMaxHealth());

            //Definitive death
            if ((hasMinotaureSpawn && (!isMinotaure || (e instanceof EntityDamageByEntityEvent && ((EntityDamageByEntityEvent) e).getDamager() instanceof Player && daedalus.getGameManager().getFactionManager().getFactionOf((Player) ((EntityDamageByEntityEvent) e).getDamager()).getGodsEnum() == GodsEnum.MINOTAURE))) || daedalus.getStructureManager().getShrinkManager().getBorderRadius() >= 495) {
                player.setGameMode(GameMode.SPECTATOR);
                for (ItemStack content : player.getInventory().getContents()) {
                    if ((!CustomItemStack.hasTag(content) || whitelist.contains(CustomItemStack.getCustomMaterial(content))) && content != null) {
                        player.getWorld().dropItem(player.getLocation(), content);
                    }
                }

                player.getInventory().clear();
                player.getActivePotionEffects().clear();
                player.setMaxHealth(20.0);
                deathFX(e);
                Faction playerFaction = daedalus.getGameManager().getFactionManager().getFactionOf(player);
                playerFaction.setPlayerStatus(player, PlayerStatus.DEAD);

            } else { // Respawn
                player.setSaturation(20.0f);
                for (int x = 0; x < player.getInventory().getContents().length; x++) {
                    ItemStack item = player.getInventory().getContents()[x];
                    if ((!CustomItemStack.hasTag(item) || whitelist.contains(CustomItemStack.getCustomMaterial(item))) && item != null) {
                        player.getWorld().dropItem(player.getLocation(), item);
                        player.getInventory().getContents()[x].setType(Material.AIR);
                    }
                }
                player.getInventory().clear();
                respawnFX(e);
                Faction playerFaction = daedalus.getGameManager().getFactionManager().getFactionOf(player);
                player.teleport(playerFaction.getType().getSpawn());

            }
            winListener();

        }
    }

    private void minotaursDeathFX() {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
    }

    private void respawnFX(EntityDamageEvent e) {
        Player player = (Player) e.getEntity();
        player.getWorld().strikeLightningEffect(player.getLocation());
        Faction faction = daedalus.getGameManager().getFactionManager().getFactionOf(player);
        switch (e.getCause()) {
            case ENTITY_ATTACK:
                Bukkit.broadcastMessage(faction.getType().getPrefix() + e.getEntity().getName() + " §8sent " + daedalus.getGameManager().getFactionManager().getFactionOf((Player) ((EntityDamageByEntityEvent) e).getDamager()).getType().getPrefix() + ((EntityDamageByEntityEvent) e).getDamager().getName() + " §8from whence they came");
                break;
            default:
                Bukkit.broadcastMessage("§8The Labyrinth sent " + faction.getType().getPrefix() + e.getEntity().getName() + " §8from whence they came");
                break;
        }
    }

    private void deathFX(EntityDamageEvent e) {
        Player player = (Player) e.getEntity();
        player.getWorld().strikeLightningEffect(player.getLocation());
        Faction faction = daedalus.getGameManager().getFactionManager().getFactionOf(player);
        switch (e.getCause()) {
            case ENTITY_ATTACK:
                Bukkit.broadcastMessage(faction.getType().getPrefix() + e.getEntity().getName() + " §8sent " + daedalus.getGameManager().getFactionManager().getFactionOf((Player) ((EntityDamageByEntityEvent) e).getDamager()).getType().getPrefix() + ((EntityDamageByEntityEvent) e).getDamager().getName() + " §8from whence theire own §ldemise");
                break;
            default:
                Bukkit.broadcastMessage(faction.getType().getPrefix() + e.getEntity().getName() + " §8met their own §ldemise");
                break;
        }
    }

    private void winListener() {
        Faction winner = null;
        int teamAliveCount = 0;
        if (daedalus.getGameManager().isRunning()) {
            for (Faction team : daedalus.getGameManager().getFactionManager().getFactionList()) {
                if (team.getType() == FactionsEnum.SPECTATOR) {
                    continue;
                }
                int playerCount = 0;
                for (Player player : team.getPlayerList()) {
                    if (team.isAlive(player)) {
                        playerCount++;
                    }
                }
                if (playerCount > 0) {
                    winner = team;
                    teamAliveCount++;
                }
            }
            if (teamAliveCount == 1) {
                daedalus.getGameManager().stop();
                winFX(winner);
            } else {
                return;
            }
        }
    }

    private void winFX(Faction team) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(team.getType().getChatColor() + team.getType().getName() + "§6§l win !", "help", 5, 100, 5);
        }


    }
}
