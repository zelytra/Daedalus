package fr.zelytra.daedalus.events.waiting.environment;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e){

        if(Daedalus.getInstance().getGameManager().isWaiting())
            e.setCancelled(true);

    }
}
