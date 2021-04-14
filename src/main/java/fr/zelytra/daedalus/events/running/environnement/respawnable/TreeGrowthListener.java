package fr.zelytra.daedalus.events.running.environnement.respawnable;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;

public class TreeGrowthListener implements Listener {

    @EventHandler
    public void onGrowth(StructureGrowEvent e){
        e.setCancelled(true);
    }
}
 