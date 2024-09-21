package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatWListener implements Listener {

  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {

    if (!Daedalus.getInstance().getGameManager().isWaiting()) return;

    final Player p = e.getPlayer();
    String message = e.getMessage().replace("%", "%%");
    if (Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(e.getPlayer())
        != null) {
      Faction playerFaction =
          Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(e.getPlayer());
      e.setFormat(playerFaction.getType().getChatColor() + p.getName() + " §7> " + message);

    } else {
      e.setFormat("§f" + p.getName() + " §7> " + message);
    }
  }
}
