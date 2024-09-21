package fr.zelytra.daedalus.events.running.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.channel.ChannelEnum;
import fr.zelytra.daedalus.managers.channel.MessageManager;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.faction.FactionsEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatRListener implements Listener {

  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {

    if (Daedalus.getInstance().getGameManager().isRunning()
        || Daedalus.getInstance().getGameManager().isFinished()) {

      final Player p = e.getPlayer();
      final Faction t = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(p);

      if (t.getType() != FactionsEnum.SPECTATOR && t.isAlive(p)) {

        if (e.getMessage().startsWith("!")) {

          MessageManager message = new MessageManager(p, e.getMessage(), ChannelEnum.GLOBAL, t);
          message.playerSendMessage();

        } else {

          MessageManager message = new MessageManager(p, e.getMessage(), ChannelEnum.TEAM, t);
          message.playerSendMessage();
        }
      } else if (!t.isAlive(p)) {

        MessageManager message = new MessageManager(p, e.getMessage(), ChannelEnum.SPECTATOR, t);
        message.playerSendMessage();

      } else {

        Faction spec =
            Daedalus.getInstance()
                .getGameManager()
                .getFactionManager()
                .getFactionOf(FactionsEnum.SPECTATOR);
        if (!p.isOp()) {

          MessageManager message =
              new MessageManager(p, e.getMessage(), ChannelEnum.SPECTATOR, spec);
          message.playerSendMessage();

        } else {

          if (e.getMessage().startsWith("!")) {

            MessageManager message = new MessageManager(p, e.getMessage(), ChannelEnum.GLOBAL, t);
            message.playerSendMessage();

          } else {

            MessageManager message =
                new MessageManager(p, e.getMessage(), ChannelEnum.SPECTATOR, t);
            message.playerSendMessage();
          }
        }
      }

      e.setCancelled(true);
    }
  }
}
