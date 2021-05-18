package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatWListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        if (!Daedalus.getInstance().getGameManager().isWaiting())
            return;

        final Player p = e.getPlayer();
        if (Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(e.getPlayer().getUniqueId()) != null) {
            Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(e.getPlayer().getUniqueId());
            e.setFormat(playerTeam.getChatColor() + p.getName() + " §7> " + e.getMessage());

        } else {
            e.setFormat("§f" + p.getName() + " §7> " + e.getMessage());
        }

    }
}
 