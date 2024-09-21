package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

  @EventHandler
  public void playerQuit(PlayerQuitEvent e) {

    final Player p = e.getPlayer();
    Utils.setTabFX(Daedalus.getInstance().getServer().getOnlinePlayers().size() - 1);
    e.setQuitMessage("§8[§c-§8] §f" + p.getName());
  }
}
