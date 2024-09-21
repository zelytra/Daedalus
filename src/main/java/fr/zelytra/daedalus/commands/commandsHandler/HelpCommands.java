package fr.zelytra.daedalus.commands.commandsHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.entity.Player;

public class HelpCommands {
  private final String commandName;
  private final List<String> commands;

  public HelpCommands(String commandName) {
    this.commandName = commandName;
    this.commands = new ArrayList<>();
  }

  public void addCommand(String prefix, String... args) {
    this.commands.add("§6- /" + commandName + " " + prefix + " §b" + Arrays.toString(args));
  }

  public void printPlayer(Player player) {
    player.sendMessage("§9§m---------------[--§r§l§bDaedalus§9§m--]---------------");
    player.sendMessage("");
    for (String s : commands) {
      player.sendMessage(s);
    }
    player.sendMessage("");
    player.sendMessage("§9§m---------------[------------]---------------");
  }
}
