package fr.zelytra.daedalus.events.running.pvp;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerRegen implements Listener {
    private HashMap<UUID, Long> regenPlayer = new HashMap<>();
    private HashMap<UUID, Float> lastSaturation = new HashMap<>();

    @EventHandler
    public void foodEvent(EntityRegainHealthEvent e) {

        if (Daedalus.getInstance().getGameManager().isRunning()) {

            if (e.getEntity() instanceof Player && e.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED && !GameSettings.HARDCORE) {
                Player player = (Player) e.getEntity();

                if (!regenPlayer.containsKey(player.getUniqueId())) {

                    if(!lastSaturation.containsKey(player.getUniqueId()))
                        lastSaturation.put(player.getUniqueId(),player.getSaturation());

                    if (lastSaturation.get(player.getUniqueId()) >= 3.0) {
                        player.setSaturation(lastSaturation.get(player.getUniqueId()));
                        e.setAmount(e.getAmount()*0.1);
                        player.setSaturation(player.getSaturation() - 2);
                        lastSaturation.put(player.getUniqueId(),player.getSaturation());
                        regenPlayer.put(player.getUniqueId(), System.currentTimeMillis() / 1000);
                    } else {
                        lastSaturation.remove(player.getUniqueId());
                        e.setCancelled(true);
                    }
                } else {
                    if ((System.currentTimeMillis() / 1000) - regenPlayer.get(player.getUniqueId()) >= 2) {
                        regenPlayer.remove(player.getUniqueId());
                    } else {
                        e.setCancelled(true);
                        player.setExhaustion(0);

                    }
                }
            }
        }
    }

}
