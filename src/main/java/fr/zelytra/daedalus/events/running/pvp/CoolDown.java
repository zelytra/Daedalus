package fr.zelytra.daedalus.events.running.pvp;

import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CoolDown implements Listener {
	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16);
	}
}
