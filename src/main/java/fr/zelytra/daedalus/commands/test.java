package fr.zelytra.daedalus.commands;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.FactionsEnum;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class test implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }
        for (Player player : Bukkit.getOnlinePlayers())
            Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(FactionsEnum.SPECTATOR).add(player);

        return true;
    }
}
