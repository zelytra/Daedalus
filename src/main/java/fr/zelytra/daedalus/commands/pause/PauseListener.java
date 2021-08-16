package fr.zelytra.daedalus.commands.pause;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.FactionsEnum;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PauseListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (Daedalus.getInstance().getGameManager().getTimeManager().isPause()) {
            if (Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(e.getPlayer()).getType() == FactionsEnum.SPECTATOR)
                return;

            e.getPlayer().sendTitle(GameSettings.LANG.textOf("command.pauseWarn"), "", 5, 20, 5);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (Daedalus.getInstance().getGameManager().getTimeManager().isPause())
            e.setCancelled(true);
    }
}
