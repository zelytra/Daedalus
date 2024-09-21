package fr.zelytra.daedalus.events.running.environnement.structure;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class PistonListener implements Listener {
  @EventHandler
  public void onPistonRetract(BlockPistonRetractEvent e) {
    if (Daedalus.getInstance().getGameManager().isRunning()) {
      e.setCancelled(true);
    }
  }

  @EventHandler
  public void onPistonExtend(BlockPistonExtendEvent e) {
    if (Daedalus.getInstance().getGameManager().isRunning()) {
      e.setCancelled(true);
    }
  }
}
