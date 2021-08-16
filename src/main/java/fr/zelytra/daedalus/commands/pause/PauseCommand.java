package fr.zelytra.daedalus.commands.pause;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PauseCommand implements CommandExecutor {

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
            Bukkit.broadcastMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.pauseResume"));
            Daedalus.getInstance().getGameManager().getTimeManager().setPause(false);
        } else {
            Bukkit.broadcastMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.pause"));
            Daedalus.getInstance().getGameManager().getTimeManager().setPause(true);
        }

        return true;

    }
}
