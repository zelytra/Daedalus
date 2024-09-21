package fr.zelytra.daedalus.events.running.players.DeathHandler.events;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DefinitiveDeathEvent extends Event implements Cancellable {

	private static final HandlerList HANDLERS_LIST = new HandlerList();
	private boolean isCancelled;

	@Getter
	private Player player;

	@Getter
	private EntityDamageEvent event;

	public DefinitiveDeathEvent(Player player, EntityDamageEvent e) {
		this.player = player;
		this.isCancelled = false;
		this.event = e;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.isCancelled = cancelled;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS_LIST;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS_LIST;
	}

	public Faction getFaction() {
		return Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(this.player);
	}

	public Player getKiller() {
		if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
			if (((EntityDamageByEntityEvent) event).getDamager() instanceof Player)
				return (Player) ((EntityDamageByEntityEvent) event).getDamager();
		return null;
	}
}
