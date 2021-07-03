package fr.zelytra.daedalus.events.running.players.DeathHandler.events;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

public class PartielDeathEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;
    private Player player;
    private EntityDamageEvent event;

    public PartielDeathEvent(Player player, EntityDamageEvent e){
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

    public Player getPlayer() {
        return player;
    }

    public Faction getFaction(){
        return Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(this.player);
    }

    public EntityDamageEvent getEvent() {
        return event;
    }
}