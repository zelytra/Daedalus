package fr.zelytra.daedalus.commands.pause;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicInteger;

public class PauseCommand implements CommandExecutor {
    private static BukkitTask preStartRunnable;
    public static boolean isResuming = false;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player && !sender.isOp()) {
            sender.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.permissionDenied"));
            return false;
        }

        if (!Daedalus.getInstance().getGameManager().isRunning()) {
            System.out.println(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.pauseState"));
            if (sender instanceof Player)
                sender.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.pauseState"));
            return false;
        }

        if (Daedalus.getInstance().getGameManager().getTimeManager().isPause()) {
            isResuming = true;
            AtomicInteger time = new AtomicInteger(5);
            preStartRunnable = Bukkit.getScheduler().runTaskTimerAsynchronously(Daedalus.getInstance(), () -> {
                switch (time.get()) {
                    case 5:
                        Bukkit.broadcastMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.pauseRestartSoon"));
                        break;
                    case 0:
                        Bukkit.broadcastMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.pauseResume"));
                        Daedalus.getInstance().getGameManager().getTimeManager().setPause(false);
                        isResuming = false;
                        preStartRunnable.cancel();
                        return;
                }
                time.getAndDecrement();
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendTitle("§6" + time.get(), "", 10, 20, 10);
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 0.5f, 1f);
                }

            }, 0, 20l);

        } else {
            Bukkit.broadcastMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.pause"));
            Daedalus.getInstance().getGameManager().getTimeManager().setPause(true);
        }

        return true;

    }
}
