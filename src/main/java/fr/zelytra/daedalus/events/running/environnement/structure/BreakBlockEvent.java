package fr.zelytra.daedalus.events.running.environnement.structure;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.GameStatesEnum;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlockEvent implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (Daedalus.getInstance().getGameManager().getState() != GameStatesEnum.RUNNING) {
            return;
        }
        if (Daedalus.getInstance().getGameManager().getStructureManager().getStructuresPosition().isEmpty()) {
            return;
        }


    }
}
