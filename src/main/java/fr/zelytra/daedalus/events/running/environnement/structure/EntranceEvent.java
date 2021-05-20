package fr.zelytra.daedalus.events.running.environnement.structure;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntranceEvent implements Listener {

    @EventHandler
    public void onPlayerEntrance(StructureEntranceEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(Daedalus.getInstance(), () -> {

        });

    }
}
