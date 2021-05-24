package fr.zelytra.daedalus.commands.revive;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.team.PlayerStatus;
import fr.zelytra.daedalus.managers.team.Team;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Revive implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;
        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage(Message.getPlayerPrefixe() + "§cYou don't have permission to perform this command");
            return false;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                player.sendMessage(Message.getPlayerPrefixe() + "§cThis player is not online");
                return false;
            }

            Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(target.getUniqueId());
            if (playerTeam.isAlive(target)) {
                player.sendMessage(Message.getPlayerPrefixe() + "§cThis player is still alive");
                return false;
            } else {
                playerTeam.setPlayerStatus(target, PlayerStatus.ALIVE);
                target.teleport(playerTeam.getTeamEnum().getSpawn());
                target.setGameMode(GameMode.SURVIVAL);

                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.playSound(p.getLocation(), Sound.ENTITY_WITCH_HURT, 2, 0.1f);
                }

                Bukkit.broadcastMessage(Message.getPlayerPrefixe() + playerTeam.getChatColor() + target.getName() + "§6has been revived");
                return true;
            }


        } else {
            player.sendMessage(Message.getHelp(cmd.getName()));
            return false;
        }

    }
}
