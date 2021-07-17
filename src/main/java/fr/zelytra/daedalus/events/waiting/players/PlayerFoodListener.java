package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerFoodListener implements Listener {

    @EventHandler
    public void playerHungry(FoodLevelChangeEvent e){
        if(Daedalus.getInstance().getGameManager().isWaiting())
            e.setCancelled(true);
    }

}
