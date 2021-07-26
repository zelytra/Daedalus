package fr.zelytra.daedalus.events.running.environnement.items.events;

import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CustomItemUseEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;
    private CustomMaterial material;
    private final Player player;
    private final ItemStack item;
    private final PlayerInteractEvent event;

    public CustomItemUseEvent(Player player, CustomMaterial material, ItemStack item, PlayerInteractEvent event) {

        this.material = material;
        this.player = player;
        this.item = item;
        this.event = event;

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

    public CustomMaterial getMaterial() {
        return material;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public PlayerInteractEvent getEvent() {
        return event;
    }
}
