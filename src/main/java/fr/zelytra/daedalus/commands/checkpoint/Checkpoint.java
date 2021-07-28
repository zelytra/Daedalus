package fr.zelytra.daedalus.commands.checkpoint;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.waiting.environment.JumpCheckPoint;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Checkpoint implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if(!Daedalus.getInstance().getGameManager().isWaiting())
            return false;

        if(JumpCheckPoint.jumpCP.containsKey(player.getName()))
            player.teleport(JumpCheckPoint.jumpCP.get(player.getName()));
        else
            player.sendMessage(GameSettings.LANG.textOf("event.jumpCPNotFound"));

        return true;
    }
}
