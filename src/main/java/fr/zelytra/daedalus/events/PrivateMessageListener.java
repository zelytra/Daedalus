package fr.zelytra.daedalus.events;

import fr.zelytra.daedalus.managers.channel.PrivateMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PrivateMessageListener implements Listener {
    @EventHandler
    public void onPrivateMessage(PlayerCommandPreprocessEvent e) {

        if (e.getMessage().contains("/me")) {
            e.setCancelled(true);
        }
        if (e.getMessage().contains("/msg")) {
            e.setCancelled(true);
            new PrivateMessage(e.getPlayer(),e.getMessage()).send();
        }
    }
}
