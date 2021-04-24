package fr.zelytra.daedalus.events.running;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.team.PlayerStatus;
import fr.zelytra.daedalus.managers.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class DeathHandler implements Listener {

    @EventHandler
    public void onPlayerDeath(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) e.getEntity();
        if (((player.getHealth() - e.getFinalDamage()) > 0)) {
            return;
        }
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            boolean isMinotaure = false;

            for (Team team : Daedalus.getInstance().getGameManager().getTeamManager().getTeamList()) {
                if (team.getGodEnum() == GodsEnum.MINOTAURE && team.getGod() != null) {
                    isMinotaure = true;
                    break;
                }
            }
            if (Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(player.getUniqueId()).getGod().getUniqueId() == player.getUniqueId()) {
                isMinotaure = false;
                Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(player.getUniqueId()).removeGod();
                minotaursDeathFX();
            }

            e.setCancelled(true);
            player.setHealth(player.getMaxHealth());
            //Definitive death
            if (!isMinotaure) {
                player.setGameMode(GameMode.SPECTATOR);
                for (ItemStack content : player.getInventory().getContents()) {
                    if (!CustomItemStack.hasTag(content) && content != null) {
                        player.getWorld().dropItem(player.getLocation(), content);
                    }
                }
                player.getInventory().clear();
                player.getActivePotionEffects().clear();
                player.setMaxHealth(20.0);
                deathFX(e);
                Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(player.getUniqueId());
                playerTeam.setPlayerStatus(player, PlayerStatus.DEAD);

            } else { // Respawn
                player.setSaturation(20.0f);
                for (int x = 0; x < player.getInventory().getContents().length; x++) {
                    ItemStack item = player.getInventory().getContents()[x];
                    if (!CustomItemStack.hasTag(item) && item != null) {
                        player.getWorld().dropItem(player.getLocation(), item);
                        player.getInventory().getContents()[x].setType(Material.AIR);
                    }
                }
                player.getInventory().clear();
                respawnFX(e);
                Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(player.getUniqueId());
                player.teleport(playerTeam.getTeamEnum().getSpawn());
            }


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
}
