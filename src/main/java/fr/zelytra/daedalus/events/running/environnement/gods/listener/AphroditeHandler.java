package fr.zelytra.daedalus.events.running.environnement.gods.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.events.running.players.DeathHandler.events.DefinitiveDeathEvent;
import fr.zelytra.daedalus.events.running.players.DeathHandler.events.PartielDeathEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Aphrodite;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AphroditeHandler implements Listener {

    @EventHandler
    public void godSpawn(GodSpawnEvent e) {

        if (e.getGod() == GodsEnum.APHRODITE) {

            e.getFaction().setGod(e.getPlayer(), GodsEnum.APHRODITE);
            new Aphrodite(e.getFaction());
            vfx(e.getPlayer());

        }

    }

    @EventHandler
    public void playerDeath(PartielDeathEvent e) {
        aphroditeKill(e.getKiller());
    }

    @EventHandler
    public void playerDeath(DefinitiveDeathEvent e) {
        aphroditeKill(e.getKiller());
    }

    private void aphroditeKill(Player killer) {
        if (killer == null) return;

        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (killer != null) {
                try {
                    Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(killer);
                    if (playerFaction.getGod() == null)
                        return;

                    if (playerFaction.getGodsEnum() == GodsEnum.APHRODITE) {
                        if (playerFaction.getGod() != null && playerFaction.getGod().getName() == killer.getName()) {
                            if (playerFaction.getGod().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + 2 <= 25)
                                playerFaction.getGod().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerFaction.getGod().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + 2);
                        } else
                            killer.setHealth(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                    }
                } catch (Exception exception) {
                    System.out.println("ERROR team not found");
                }


            }
        }
    }



    private void vfx(Player player) {
        Bukkit.broadcastMessage(GameSettings.LANG.textOf("godSpawn.aphrodite"));
        Utils.runTotemDisplay(player);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 10, 0.1f);
        }
    }


}
