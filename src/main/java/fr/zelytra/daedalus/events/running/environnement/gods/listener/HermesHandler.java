package fr.zelytra.daedalus.events.running.environnement.gods.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Hermes;
import fr.zelytra.daedalus.utils.Message;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.Objects;

public class HermesHandler implements Listener {
    private final int maxY = 10;

    @EventHandler
    public void playerInteract(GodSpawnEvent e) {

        if (e.getGod() == GodsEnum.HERMES) {

            e.getFaction().setGod(e.getPlayer(), GodsEnum.HERMES);
            new Hermes(e.getFaction());
            vfx(e.getPlayer());
            doubleJump();

        }

    }

    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getEntity() instanceof Player) {
                Player player = ((Player) e.getEntity());
                if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    try {
                        Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
                        if (playerFaction.getGodsEnum() != null && playerFaction.getGodsEnum() == GodsEnum.HERMES && Objects.requireNonNull(playerFaction.getGod()).getUniqueId() == e.getEntity().getUniqueId()) {
                            e.setCancelled(true);
                        }
                    } catch (Exception exception) {
                        System.out.println("ERROR team not found");
                    }
                }
            }
        }
    }

    @EventHandler
    public void setFlyOnJump(PlayerToggleFlightEvent e) {
        Player jumper = e.getPlayer();

        if (jumper.getGameMode() != GameMode.SURVIVAL) return;

        if (jumper.getLocation().getY() >= 93) {
            e.setCancelled(true);
            jumper.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("event.jumpTooHigh"));
            return;
        }

        if (e.isFlying()) {
            jumper.setFlying(false);
            Vector jump = jumper.getLocation().getDirection().multiply(0.2).setY(0.8);
            jumper.setVelocity(jumper.getVelocity().add(jump));
            e.setCancelled(true);
            jumper.setAllowFlight(false);

        }

    }

    private void vfx(Player player) {
        Bukkit.broadcastMessage(GameSettings.LANG.textOf("godSpawn.hermes"));
        Utils.runTotemDisplay(player);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 10, 0.1f);
        }
    }

    public void doubleJump() {
        Bukkit.getScheduler().runTaskTimer(Daedalus.getInstance(), () -> {
            for (Faction team : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
                if (team.getGodsEnum() != GodsEnum.HERMES) {
                    continue;
                }

                if (team.getGod() == null)
                    return;

                if (team.getGod().isOnGround()) {
                    team.getGod().setAllowFlight(true);
                }
            }

        }, 0, 13);


    }
}
