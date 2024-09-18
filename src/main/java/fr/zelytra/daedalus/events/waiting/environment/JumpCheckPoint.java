package fr.zelytra.daedalus.events.waiting.environment;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JumpCheckPoint implements Listener {
    public static HashMap<String, Location> jumpCP = new HashMap<>();
    private final static List<String> playerWin = new ArrayList<>();

    @EventHandler
    public void onPressurePlate(PlayerInteractEvent e) {
        if (!Daedalus.getInstance().getGameManager().isWaiting())
            return;

        if (e.getAction() == Action.PHYSICAL) {
            e.setCancelled(true);
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
                            checkPointEvent(e, temp);
                            return;
                        } else if (temp.getBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
                            saucisse(e.getPlayer());
                            return;
                        }
                    }
            } else if (cp.getBlock().getType() == Material.JUNGLE_PRESSURE_PLATE)
                checkPointEvent(e, cp);

            else if (cp.getBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE)
                saucisse(e.getPlayer());

        }

    }

    private void checkPointEvent(PlayerInteractEvent e, Location loc) {
        jumpCP.put(e.getPlayer().getName(), loc);
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 2, 2);
        e.getPlayer().sendMessage(GameSettings.LANG.textOf("event.jumpCP"));
    }

    private void saucisse(Player player) {
        if(playerWin.contains(player.getName()))
            return;
        playerWin.add(player.getName());
        for (int x = 0; x <= 150; x++) {
            Bukkit.getScheduler().runTaskLater(Daedalus.getInstance(), () -> {

                double randomx = (Math.random() * (10.0 + 10.0 + 1)) - 10.0;
                double randomy = (Math.random() * (10.0));
                double randomz = (Math.random() * (10.0 + 10.0 + 1)) - 10.0;

                Location Location1 = new Location(player.getWorld(),
                        player.getLocation().getX() + randomx,
                        player.getLocation().getY() + randomy,
                        player.getLocation().getZ() + randomz);

                Firework fw = (Firework) player.getWorld().spawnEntity(Location1, EntityType.FIREWORK_ROCKET);
                FireworkMeta fwm = fw.getFireworkMeta();

                fwm.setPower(20);
                fwm.addEffect(FireworkEffect.builder().withColor(Color.fromRGB((int) (Math.random() * (16000000) + 1))).flicker(true).build());
                fw.setFireworkMeta(fwm);
                fw.detonate();

            }, x + 10);

        }
    }
}
