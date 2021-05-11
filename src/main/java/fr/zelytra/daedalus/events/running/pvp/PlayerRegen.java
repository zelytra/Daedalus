package fr.zelytra.daedalus.events.running.pvp;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerRegen implements Listener {
    private HashMap<UUID, Long> reganPlayer = new HashMap<>();

    @EventHandler
    public void foodEvent(EntityRegainHealthEvent e) {

        if (Daedalus.getInstance().getGameManager().isRunning()) {

            if (e.getEntity() instanceof Player && (e.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED || e.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN)) {
                Player player =(Player) e.getEntity();

                if (!reganPlayer.containsKey(player.getUniqueId())) {
                    e.setAmount(e.getAmount() * 0.1);//10% regen speed
                    player.setExhaustion(3);
                    reganPlayer.put(player.getUniqueId(), System.currentTimeMillis() / 1000);
                } else {
                    if ((System.currentTimeMillis() / 1000) - reganPlayer.get(player.getUniqueId()) >= 4) {
                        reganPlayer.remove(player.getUniqueId());
                    } else {
                        e.setCancelled(true);
                        player.setExhaustion(0);
                    }
                }
            }
        }
    }

    @EventHandler
    public void exhaustion(FoodLevelChangeEvent e){
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (reganPlayer.containsKey(e.getEntity().getUniqueId())){
                e.getEntity().setExhaustion(0);
            }
        }
    }

}
