package fr.zelytra.daedalus.events.running.players.DeathHandler.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.events.running.players.DeathHandler.events.DefinitiveDeathEvent;
import fr.zelytra.daedalus.events.running.players.DeathHandler.events.PartielDeathEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.faction.FactionsEnum;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DeathListener implements Listener {
    private Daedalus daedalus = Daedalus.getInstance();
    private boolean hasMinoSpawn = false;
    private boolean isMinoDead = false;

    @EventHandler
    public void onCustomDeath(EntityDamageEvent e) {

        if (!(e.getEntity() instanceof Player))
            return;

        Player player = (Player) e.getEntity();
        boolean isDead = player.getHealth() - e.getFinalDamage() > 0;

        if (daedalus.getGameManager().isRunning() && isDead) {
            e.setCancelled(true);
            player.setHealth(player.getMaxHealth());
            boolean shrinkHasReachSpawn = daedalus.getStructureManager().getShrinkManager().getBorderRadius() <= 495;
            boolean killByAMino = false;

            if (e instanceof EntityDamageByEntityEvent && ((EntityDamageByEntityEvent) e).getDamager() instanceof Player) {
                Player killer = (Player) ((EntityDamageByEntityEvent) e).getDamager();
                killByAMino = daedalus.getGameManager().getFactionManager().getFactionOf(killer).getGodsEnum() == GodsEnum.MINOTAURE; //Tuer par un mino
            }

            boolean isMemberOfMino = daedalus.getGameManager().getFactionManager().getFactionOf(player).getGodsEnum() == GodsEnum.MINOTAURE;//Quand on est un mino

            if (hasMinoSpawn && !isMinoDead && daedalus.getGameManager().getFactionManager().getFactionOf(FactionsEnum.MINOTAUR).getGod() != null) {
                isMinoDead = player.getUniqueId() == daedalus.getGameManager().getFactionManager().getFactionOf(FactionsEnum.MINOTAUR).getGod().getUniqueId();
                minotaursDeathFX();
            }

            if ((hasMinoSpawn && isMinoDead) || killByAMino || shrinkHasReachSpawn || isMemberOfMino) {

                DefinitiveDeathEvent event = new DefinitiveDeathEvent(player,e);
                Bukkit.getPluginManager().callEvent(event);
                //winListener();

            } else {

                PartielDeathEvent event = new PartielDeathEvent(player,e);
                Bukkit.getPluginManager().callEvent(event);

            }


        }


    }

    @EventHandler
    public void onMinoSpawn(GodSpawnEvent e) {
        if (e.getGod() == GodsEnum.MINOTAURE)
            hasMinoSpawn = true;
    }

    private void minotaursDeathFX() {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§6The Minotaur has been killed. Now death is the only option");
        Bukkit.broadcastMessage("");
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
            p.sendTitle(team.getType().getChatColor() + team.getType().getName() + "§6§l win !", "oskour", 5, 100, 5);
        }


    }


}
