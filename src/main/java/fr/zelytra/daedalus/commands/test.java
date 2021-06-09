package fr.zelytra.daedalus.commands;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
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

        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(Message.getPlayerPrefixe() + "§6The wall maze begin to fall... Advise : §cRUN.");
        Bukkit.broadcastMessage("");
        Daedalus.getInstance().getStructureManager().getShrinkManager().startShrinking();
        return true;
    }
}
