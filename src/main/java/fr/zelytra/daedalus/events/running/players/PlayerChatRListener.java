package fr.zelytra.daedalus.events.running.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.team.Team;
import fr.zelytra.daedalus.managers.team.TeamsEnum;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class PlayerChatRListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){

        if(!Daedalus.getInstance().getGameManager().isRunning())
            return;


        final Player p = e.getPlayer();
        final Team t = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId());

        if(t.isAlive(p) && t.getTeamEnum() != TeamsEnum.SPECTATOR){

            if(e.getMessage().startsWith("!")){

                for(Player pl : Bukkit.getOnlinePlayers()){
                    pl.sendMessage(getFormattedMessage(p, t, e.getMessage(), ChannelEnum.GLOBAL));
                }

            }else{

                for(UUID uuid : t.getPlayerList()){
                    Player pl = Bukkit.getPlayer(uuid);
                    assert pl != null;
                    pl.sendMessage(getFormattedMessage(p, t, e.getMessage(), ChannelEnum.TEAM));
                }


            }
        }else{

            Team spec = Daedalus.getInstance().getGameManager().getTeamManager().getSpectatorTeam();

            for(UUID uuid : spec.getPlayerList()){
                Player pl = Bukkit.getPlayer(uuid);
                assert pl != null;
                pl.sendMessage(getFormattedMessage(p, t, e.getMessage(), ChannelEnum.SPECTATOR));
            }
        }


        e.setCancelled(true);

    }

    private String getFormattedMessage(Player p, Team t, String message, ChannelEnum channel){

        switch (channel){

            case GLOBAL: {

                return t.getPrefix()+p.getName()+"§7: §f"+message.substring(1);
            }
            case TEAM: {

                return t.getPrefix()+p.getName()+"§7: §3"+message;
            }
            case SPECTATOR: {

                return t.getPrefix()+"§7"+p.getName()+": §3"+message;
            }
        }
        return "";
    }
}
 