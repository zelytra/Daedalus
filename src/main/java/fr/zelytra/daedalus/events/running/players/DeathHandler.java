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
    private boolean hasMinotaureSpawn = true;

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
        if (Daedalus.getInstance().getGameManager().isRunning()) {

            Player player = (Player) e.getEntity();
            if (((player.getHealth() - e.getFinalDamage()) > 0) || (e.getCause() == EntityDamageEvent.DamageCause.FALL && Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player).getGod() != null && Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player).getGodsEnum() == GodsEnum.ZEUS)) {
                return;
            }

            boolean isMinotaure = false;

            for (Faction team : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
                if (team.getGodsEnum() == GodsEnum.MINOTAURE && team.getGod() != null) {
                    hasMinotaureSpawn = true;
                    isMinotaure = true;
                    break;
                }
            }
            if (Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player).getGod() != null && Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player).getGod().getUniqueId() == player.getUniqueId()) {
                isMinotaure = false;
                Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player).removeGod();
                minotaursDeathFX();
            }

            e.setCancelled(true);
            player.setHealth(player.getMaxHealth());

            //Definitive death
            if (hasMinotaureSpawn && (!isMinotaure || (e instanceof EntityDamageByEntityEvent && ((EntityDamageByEntityEvent) e).getDamager() instanceof Player && Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf((Player) ((EntityDamageByEntityEvent) e).getDamager()).getGodsEnum() == GodsEnum.MINOTAURE))) {
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
                Faction playerTeam = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
                playerTeam.setPlayerStatus(player, PlayerStatus.DEAD);

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
                Faction playerTeam = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
                player.teleport(playerTeam.getType().getSpawn());

            }
            winListener();

        }
    }

    private void minotaursDeathFX() {
    }

    private void respawnFX(EntityDamageEvent e) {
        Player player = (Player) e.getEntity();
        player.getWorld().strikeLightningEffect(player.getLocation());
        switch (e.getCause()) {
            case ENTITY_ATTACK:
                Bukkit.broadcastMessage(e.getEntity().getName() + " was killed.");
                break;
            default:
                Bukkit.broadcastMessage(e.getEntity().getName() + " is dead.");
                break;
        }
    }

    private void deathFX(EntityDamageEvent e) {
        Player player = (Player) e.getEntity();
        player.getWorld().strikeLightningEffect(player.getLocation());
        switch (e.getCause()) {
            case ENTITY_ATTACK:
                Bukkit.broadcastMessage(e.getEntity().getName() + " was killed.");
                break;
            default:
                Bukkit.broadcastMessage(e.getEntity().getName() + " is dead.");
                break;
        }
    }

    private void winListener() {
        Faction winner = null;
        int teamAliveCount = 0;
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            for (Faction team : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
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
                Daedalus.getInstance().getGameManager().stop();
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
