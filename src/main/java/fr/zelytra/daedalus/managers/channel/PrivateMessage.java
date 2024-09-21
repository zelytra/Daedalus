package fr.zelytra.daedalus.managers.channel;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PrivateMessage {

  private Player sender;
  private final String commandLine;

  public PrivateMessage(Player player, String commandLine) {
    this.commandLine = commandLine;
    this.sender = player;
  }

  public void send() {
    final String[] arg = commandLine.split(" ");

    if (arg.length < 2) {
      sender.sendMessage(
          Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.noArguments"));
      return;
    }
    final String targetName = arg[1];

    if (Bukkit.getPlayer(targetName) == null) {
      sender.sendMessage(
          Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.playerOffline"));
      return;
    }
    Player receiver = Bukkit.getPlayer(targetName);
    StringBuilder message = new StringBuilder();

    for (int x = 2; x < arg.length; x++) {
      if (x == arg.length - 1) {
        message.append(arg[x]);
        continue;
      }
      message.append(arg[x]).append(" ");
    }
    assert receiver != null;

    receiver.sendMessage(
        GameSettings.LANG.textOf("command.messageFrom") + sender.getName() + "§f > §o" + message);
    sender.sendMessage(
        GameSettings.LANG.textOf("command.messageTo") + receiver.getName() + "§f > §o" + message);
  }
}
