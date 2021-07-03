package fr.zelytra.daedalus.managers.skrink;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.FactionsEnum;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class ShrinkManager {
    private static Daedalus daedalus = Daedalus.getInstance();
    private WorkloadThread workloadThread;
    private int offsetArea = 10;
    private BukkitTask task;

    public ShrinkManager() {
        workloadThread = new WorkloadThread();
    }

    public void startShrinking() {

        if (!daedalus.getGameManager().isRunning()) {
            return;
        }

        if (daedalus.getStructureManager().getMaze().getMaze() == null) {
            return;
        }

        Bukkit.getScheduler().runTask(daedalus, () -> {
            workloadThread.run();
        });

        startBorderListener();

    }

    public int getBorderRadius() {
        return workloadThread.getRadius();
    }

    private void startBorderListener() {
        task = Bukkit.getScheduler().runTaskTimerAsynchronously(Daedalus.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player).getType() == FactionsEnum.SPECTATOR || player.getGameMode() == GameMode.SPECTATOR)
                    continue;

                if (isInWarningArea(player.getLocation())) {
                    logPlayer(player, "§eYou're in §lWARNING§r§e area go back to the maze !");
                    particleFX(player);
                    continue;
                }
                if (isInDangerArea(player.getLocation())) {
                    player.sendTitle("§c§lDANGER", "§cGET OUT YOU IDIOT !!!", 3, 10, 3);
                    player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_DEATH, 1, 1);

                    Bukkit.getScheduler().runTask(Daedalus.getInstance(), () -> {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 50, 2, true, true, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40, 2, true, true, true));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 40, 1, true, true, true));
                    });

                    particleFX(player);

                }
            }

        }, 0, 30);
    }

    private void particleFX(Player player) {
        int radius = 8;
        Location loc = player.getLocation();

        for (int x = loc.getBlockX() - radius; x < loc.getBlockX() + radius; x++) {
            for (int z = loc.getBlockZ() - radius; z < loc.getBlockZ() + radius; z++) {

                if (isInDangerArea(new Location(player.getWorld(), x, loc.getY(), z))) {
                    for (int count = 0; count < 3; count++)
                        loc.getWorld().spawnParticle(Particle.SPELL_MOB, new Location(loc.getWorld(), x + 0.5, loc.getY(), z + 0.5), 0, 0, 0, 0, 1);
                }

            }
        }
    }

    private void logPlayer(Player player, String msg) {
        BaseComponent txt = new TextComponent(msg);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, txt);
    }

    private void stopBorderTask() {
        if (task != null && !task.isCancelled())
            task.cancel();
    }

    public WorkloadThread getWorkloadThread() {
        return workloadThread;
    }

    private boolean isInWarningArea(Location location) {
        if ((Math.abs(location.getX()) >= getBorderRadius() && Math.abs(location.getX()) <= getBorderRadius() + offsetArea) || (Math.abs(location.getZ()) >= getBorderRadius() && Math.abs(location.getZ()) <= getBorderRadius() + offsetArea))
            return true;
        else
            return false;
    }

    private boolean isInDangerArea(Location location) {
        if (Math.abs(location.getX()) >= getBorderRadius() + offsetArea || Math.abs(location.getZ()) >= getBorderRadius() + offsetArea)
            return true;
        else
            return false;
    }
}

