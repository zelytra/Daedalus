package fr.zelytra.daedalus.commands.revive;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReviveTabs implements TabCompleter {
  @Override
  public @Nullable List<String> onTabComplete(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String alias,
      @NotNull String[] args) {
    List<String> commandsList = new ArrayList<>();
    if (args.length == 1) {

      for (Player p : Bukkit.getOnlinePlayers()) {
        Faction playerFaction =
            Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(p);
        if (!playerFaction.isAlive(p)) {
          commandsList.add(p.getName());
        }
      }

      commandsList = Utils.dynamicTab(commandsList, args[0]);
    }
    return commandsList;
  }
}
