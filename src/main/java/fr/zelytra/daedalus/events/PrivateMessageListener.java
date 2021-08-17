package fr.zelytra.daedalus.events;

import fr.zelytra.daedalus.managers.channel.PrivateMessage;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PrivateMessageListener implements Listener {
    @EventHandler
    public void onPrivateMessage(PlayerCommandPreprocessEvent e) {

        if (e.getMessage().contains("/me") || e.getMessage().contains("/tell") || e.getMessage().contains("/whisper")) {
            e.getPlayer().sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.permissionDenied"));
            e.setCancelled(true);
        }
        if (e.getMessage().contains("/msg")) {
            e.setCancelled(true);
            new PrivateMessage(e.getPlayer(), e.getMessage()).send();
        }
    }
}
