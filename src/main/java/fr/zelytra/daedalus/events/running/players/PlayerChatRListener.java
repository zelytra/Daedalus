package fr.zelytra.daedalus.events.running.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.channel.ChannelEnum;
import fr.zelytra.daedalus.managers.channel.MessageManager;
import fr.zelytra.daedalus.managers.team.Team;
import fr.zelytra.daedalus.managers.team.TeamsEnum;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatRListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){

        if(!Daedalus.getInstance().getGameManager().isRunning())
            return;

        final Player p = e.getPlayer();
        final Team t = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId());

        if(t.isAlive(p) && t.getTeamEnum() != TeamsEnum.SPECTATOR){

            if(e.getMessage().startsWith("!")){

                MessageManager message = new MessageManager(p,e.getMessage(),ChannelEnum.GLOBAL,t);
                message.playerSendMessage();


            }else{

                MessageManager message = new MessageManager(p,e.getMessage(),ChannelEnum.TEAM,t);
                message.playerSendMessage();


            }
        }else{

            Team spec = Daedalus.getInstance().getGameManager().getTeamManager().getSpectatorTeam();
            MessageManager message = new MessageManager(p,e.getMessage(),ChannelEnum.TEAM,spec);
            message.playerSendMessage();

        }


        e.setCancelled(true);

    }

}
 