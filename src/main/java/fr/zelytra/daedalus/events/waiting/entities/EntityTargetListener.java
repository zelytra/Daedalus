package fr.zelytra.daedalus.events.waiting.entities;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class EntityTargetListener implements Listener {

    @EventHandler
    public void onTarget(EntityTargetEvent e){

        if(Daedalus.getInstance().getGameManager().isWaiting())
            e.setCancelled(true);

    }
}
