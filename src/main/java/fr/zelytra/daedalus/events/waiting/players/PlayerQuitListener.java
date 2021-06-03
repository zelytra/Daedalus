package fr.zelytra.daedalus.events.waiting.players;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void playerQuit(PlayerQuitEvent e){

        final Player p = e.getPlayer();

        e.setQuitMessage("§8[§c-§8] §f"+p.getName());

    }
}
