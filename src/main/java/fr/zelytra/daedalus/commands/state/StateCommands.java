package fr.zelytra.daedalus.commands.state;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.GameStatesEnum;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StateCommands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player && sender.isOp()) {

            if (!sender.isOp()){
                sender.sendMessage(Message.getPlayerPrefixe()+ GameSettings.LANG.textOf("command.permissionDenied"));
                return false;
            }

            if (args.length == 1) {

                try {
                    Daedalus.getInstance().getGameManager().setState(GameStatesEnum.valueOf(args[0]));
                } catch (IllegalArgumentException ignored) {
                    sender.sendMessage(Message.getPlayerPrefixe() + "§cWrong state.");
                }

                Bukkit.broadcastMessage(Message.getPlayerPrefixe()+"§6Game state changed into -> §l" + args[0]);
                return true;
            }

        }

        return false;
    }
}
 