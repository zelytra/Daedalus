package fr.zelytra.daedalus.commands.revive;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.team.Team;
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

public class ReviveTabs implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> commandsList = new ArrayList<>();
        if (args.length == 1) {

            for (Player p : Bukkit.getOnlinePlayers()) {
                Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId());
                if (!playerTeam.isAlive(p)) {
                    commandsList.add(p.getName());
                }
            }

            commandsList = Utils.dynamicTab(commandsList, args[0]);
        }
        return commandsList;
    }
}
 