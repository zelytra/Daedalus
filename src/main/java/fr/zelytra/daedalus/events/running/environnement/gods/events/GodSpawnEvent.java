package fr.zelytra.daedalus.events.running.environnement.gods.events;

import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class GodSpawnEvent extends Event implements Cancellable {

	private static final HandlerList HANDLERS_LIST = new HandlerList();
	private boolean isCancelled;

	@Getter
	private final GodsEnum god;

	@Getter
	private final Faction faction;

	@Getter
	private final Player player;

	public GodSpawnEvent(GodsEnum god, Faction faction, Player player) {
		this.god = god;
		this.isCancelled = false;
		this.faction = faction;
		this.player = player;
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
	public @NotNull HandlerList getHandlers() {
		return HANDLERS_LIST;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS_LIST;
	}
}
