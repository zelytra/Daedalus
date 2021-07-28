package fr.zelytra.daedalus.events.waiting.environment;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class JumpCheckPoint implements Listener {
    public static HashMap<String, Location> jumpCP = new HashMap<>();

    @EventHandler
    public void onPressurePlate(PlayerInteractEvent e) {
        if (!Daedalus.getInstance().getGameManager().isWaiting())
            return;

        if (e.getAction() == Action.PHYSICAL) {
            Location cp = e.getPlayer().getLocation().getBlock().getLocation();
            cp.setX(cp.getX() + 0.5);
            cp.setZ(cp.getZ() + 0.5);
            cp.setPitch(e.getPlayer().getLocation().getPitch());
            cp.setYaw(e.getPlayer().getLocation().getYaw());

            if (jumpCP.containsKey(e.getPlayer().getName()) && jumpCP.get(e.getPlayer().getName()).getY() == cp.getY())
                return;

            if (cp.getBlock().getType() == Material.AIR) {
                for (double x = (cp.getX() - 1); x <= cp.getX() + 1; x++)
                    for (double z = (cp.getZ() - 1); z <= cp.getZ() + 1; z++) {
                        Location temp = cp.clone();
                        temp.setX(x);
                        temp.setZ(z);

                        if (temp.getBlock().getType() == Material.JUNGLE_PRESSURE_PLATE) {
                            jumpCP.put(e.getPlayer().getName(), temp);
                            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 2, 2);
                            e.getPlayer().sendMessage(GameSettings.LANG.textOf("event.jumpCP"));
                            return;
                        }
                    }
            } else {
                jumpCP.put(e.getPlayer().getName(), cp);
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 2, 2);
                e.getPlayer().sendMessage(GameSettings.LANG.textOf("event.jumpCP"));
            }


        }
    }
}
