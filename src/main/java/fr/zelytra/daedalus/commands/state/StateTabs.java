package fr.zelytra.daedalus.commands.state;

import fr.zelytra.daedalus.managers.game.GameStatesEnum;
import fr.zelytra.daedalus.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StateTabs implements TabCompleter {
  @Override
  public @Nullable List<String> onTabComplete(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String alias,
      @NotNull String[] args) {
    List<String> commandsList = new ArrayList<>();
    if (args.length == 1) {
      for (GameStatesEnum state : GameStatesEnum.values()) {
        commandsList.add(state.toString());
      }
      commandsList = Utils.dynamicTab(commandsList, args[0]);
    }
    return commandsList;
  }
}
