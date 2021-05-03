package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatWListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){

        if(!Daedalus.getInstance().getGameManager().isWaiting())
            return;

        final Player p = e.getPlayer();

        e.setFormat("§f["+p.getName()+"]§7 "+e.getMessage());

    }
}
 