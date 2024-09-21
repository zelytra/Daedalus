package fr.zelytra.daedalus.events.running.pvp;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;

public class EntityResurect implements Listener {

  @EventHandler
  public void onResurect(EntityResurrectEvent e) {
    e.setCancelled(true);
  }
}
