package fr.zelytra.daedalus.commands;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class test implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){
            return false;
        }
        Daedalus.getInstance().getGameManager().getTimeManager().start();
        //ShrinkManager.startShrinking();
        return true;
    }
}
