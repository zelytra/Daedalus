package fr.zelytra.daedalus.events.running.environnement.gods.listener;

import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Dionysos;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DionysosHandler implements Listener {

    @EventHandler
    public void playerInteract(GodSpawnEvent e) {

        if (e.getGod() == GodsEnum.DIONYSUS) {

            e.getFaction().setGod(e.getPlayer(), GodsEnum.DIONYSUS);
            new Dionysos(e.getFaction());
            vfx(e.getPlayer());
        }

    }


    private void vfx(Player player) {
        Bukkit.broadcastMessage("§5§l✌ Dionysos has appeared in the maze ✌");
        Utils.runTotemDisplay(player);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 10, 0.1f);
        }
    }

}
