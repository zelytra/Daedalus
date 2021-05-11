package fr.zelytra.daedalus.events.running.environnement;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PortalListener implements Listener {
    @EventHandler
    public void onPortal(PlayerPortalEvent e){
        e.setCanCreatePortal(false);
        e.setCancelled(true);
    }
}
