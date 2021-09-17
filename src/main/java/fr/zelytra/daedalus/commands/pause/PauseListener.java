package fr.zelytra.daedalus.commands.pause;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PauseListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (Daedalus.getInstance().getGameManager().getTimeManager().isPause()) {
            if (e.getPlayer().getGameMode() == GameMode.SPECTATOR)
                return;

            if (!PauseCommand.isResuming)
                e.getPlayer().sendTitle("", GameSettings.LANG.textOf("command.pauseWarn"), 0, 20, 5);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (Daedalus.getInstance().getGameManager().getTimeManager().isPause())
            e.setCancelled(true);
    }

    @EventHandler
    public void onTarget(EntityTargetEvent e) {
        if (Daedalus.getInstance().getGameManager().getTimeManager().isPause())
            e.setCancelled(true);
    }

    @EventHandler
    public void placeBlock(BlockPlaceEvent e) {
        if (Daedalus.getInstance().getGameManager().getTimeManager().isPause())
            e.setCancelled(true);

    }

    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        if (Daedalus.getInstance().getGameManager().getTimeManager().isPause())
            e.setCancelled(true);
    }

    @EventHandler
    public void interact(PlayerInteractEvent e) {
        if (Daedalus.getInstance().getGameManager().getTimeManager().isPause())
            e.setCancelled(true);
    }

    @EventHandler
    public void dropItem(PlayerDropItemEvent e) {
        if (Daedalus.getInstance().getGameManager().getTimeManager().isPause())
            e.setCancelled(true);
    }

    @EventHandler
    public void inventory(InventoryClickEvent e) {
        if (Daedalus.getInstance().getGameManager().getTimeManager().isPause())
            e.setCancelled(true);
    }

    @EventHandler
    public void inventory(ProjectileLaunchEvent e) {
        if (Daedalus.getInstance().getGameManager().getTimeManager().isPause())
            e.setCancelled(true);
    }
}
