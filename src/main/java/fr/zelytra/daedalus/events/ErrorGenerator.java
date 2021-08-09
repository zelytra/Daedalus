package fr.zelytra.daedalus.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ErrorGenerator implements Listener {
    @EventHandler
    public void error(PlayerInteractEvent e){
        System.out.println(e.getClickedBlock().getType());
    }
}
