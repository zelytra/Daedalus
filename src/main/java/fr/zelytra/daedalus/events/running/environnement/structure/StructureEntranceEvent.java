package fr.zelytra.daedalus.events.running.environnement.structure;

import fr.zelytra.daedalus.managers.structure.Structure;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.util.BoundingBox;

import java.util.List;

public class StructureEntranceEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;

    private final Structure structure;
    private final List<Player> players;
    private final BoundingBox area;

    public StructureEntranceEvent(Structure structure, List<Player> player, BoundingBox area) {
        this.structure = structure;
        this.players = player;
        this.area = area;
    }

    public BoundingBox getArea() {
        return area;
    }

    public Structure getStructure() {
        return structure;
    }

    public List<Player> getPlayers() {
        return players;
    }


    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
