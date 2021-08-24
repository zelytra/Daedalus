package fr.zelytra.daedalus.commands.godSummon;

import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GodSummonTab implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        List<String> commandsList = new ArrayList<>();
        if (args.length == 1) {
            for (GodsEnum god : GodsEnum.values()) {
                commandsList.add(god.name().toLowerCase());
            }
            commandsList = Utils.dynamicTab(commandsList, args[0]);
        }

        if (args.length == 2) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                commandsList.add(p.getName());
            }
            commandsList = Utils.dynamicTab(commandsList, args[1]);
        }
        return commandsList;
    }
}
