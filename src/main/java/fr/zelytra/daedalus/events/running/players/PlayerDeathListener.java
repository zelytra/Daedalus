package fr.zelytra.daedalus.events.running.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    public void onPlayerDeath(PlayerDeathEvent e){

        final Player p = e.getEntity();
        final Player killer = p.getKiller();
        final Location location = p.getLocation();
        final Team team = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId());

        e.setDeathMessage(null);

        if(Daedalus.getInstance().getGameManager().getMinos().isSpawned()){
            if(Daedalus.getInstance().getGameManager().getMinos().isDead() || isKilledByMinos(killer)){
                p.spigot().respawn();
                p.setGameMode(GameMode.SPECTATOR);
                p.teleport(location);
                team.removePlayer(p.getUniqueId());
                p.setDisplayName(ChatColor.WHITE + "" + ChatColor.ITALIC + p.getName());
                p.setPlayerListName(ChatColor.WHITE + "" + ChatColor.ITALIC + p.getName());

            }else{
                p.spigot().respawn();
                // TP SPAWN
            }
        }else{
            p.spigot().respawn();
            // TP SPAWN
        }

    }

    private boolean isKilledByMinos(Player killer){

        if(killer != null){

            Team t = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(killer.getUniqueId());
            return t == Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.GRAY);
        }

        return false;
    }

}
 