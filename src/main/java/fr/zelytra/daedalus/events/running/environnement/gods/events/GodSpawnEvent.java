package fr.zelytra.daedalus.events.running.environnement.gods.events;

import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GodSpawnEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;
    private final GodsEnum god;
    private final Faction faction;
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
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public GodsEnum getGod() {
        return god;
    }

    public Faction getFaction() {
        return faction;
    }

    public Player getPlayer() {
        return player;
    }
}
