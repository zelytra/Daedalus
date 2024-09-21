package fr.zelytra.daedalus.managers.game.time.event;

import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class EpisodeChangeEvent extends Event implements Cancellable {

	private static final HandlerList HANDLERS_LIST = new HandlerList();
	private boolean isCancelled;

	@Getter
	private int episode;

	public EpisodeChangeEvent(int episode) {
		this.episode = episode;
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
