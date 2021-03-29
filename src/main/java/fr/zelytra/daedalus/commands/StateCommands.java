package fr.zelytra.daedalus.commands;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.GameStatesEnum;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StateCommands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player && sender.isOp()){

            if(args.length == 1){

                try{
                    Daedalus.getInstance().getGameManager().setState(GameStatesEnum.valueOf(args[0]));
                }catch (IllegalArgumentException ignored){}

                sender.sendMessage("§dGame state changed into -> "+args[0]);
                return true;
            }

        }

        return false;
    }
}
 