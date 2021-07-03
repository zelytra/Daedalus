package fr.zelytra.daedalus.events.running.environnement.gods.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Aphrodite;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class AphroditeHandler implements Listener {

    @EventHandler
    public void playerInteract(GodSpawnEvent e) {

        if (e.getGod() == GodsEnum.APHRODITE) {

            e.getFaction().setGod(e.getPlayer(), GodsEnum.APHRODITE);
            new Aphrodite(e.getFaction());
            vfx(e.getPlayer());

        }

    }

    @EventHandler
    public void playerInteract(PlayerDeathEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getEntity().getKiller() != null) {
                try {
                    Player killer = e.getEntity().getKiller();
                    Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(killer);
                    if (playerFaction.getGod() == null) {
                        return;
                    }
                    if (playerFaction.getGodsEnum() == GodsEnum.APHRODITE) {
                        killer.setHealth(killer.getHealth() + 4.0 > killer.getMaxHealth() ? killer.getMaxHealth() : killer.getHealth() + 4.0);
                    }
                } catch (Exception exception) {
                    System.out.println("ERROR team not found");
                }


            }
        }
    }

    private void vfx(Player player) {
        Bukkit.broadcastMessage("§d§l❤ Aphrodite as appear in the maze ❤");
        Utils.runTotemDisplay(player);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 10, 0.1f);
        }
    }



}
