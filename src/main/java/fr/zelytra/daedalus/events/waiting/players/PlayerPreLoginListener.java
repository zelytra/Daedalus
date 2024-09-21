package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PlayerPreLoginListener implements Listener {
  @EventHandler
  public void playerPreLogin(AsyncPlayerPreLoginEvent e) {

    if (Daedalus.getInstance().getGameManager().isStarted()) {
      e.disallow(
          AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
          "§cMaze is currently loading... please come back in few seconds");
      return;
    }
  }
}
