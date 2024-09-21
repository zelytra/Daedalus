package fr.zelytra.daedalus.events.running.environnement.mobCutclean;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class MilkDrink implements Listener {

  @EventHandler
  public void onMilkDrink(PlayerItemConsumeEvent e) {
    if (Daedalus.getInstance().getGameManager().isRunning()) {
      if (e.getItem().getType() == Material.MILK_BUCKET) {
        e.setCancelled(true);
        e.getPlayer()
            .sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("event.drinkMilk"));
      }
    }
  }
}
