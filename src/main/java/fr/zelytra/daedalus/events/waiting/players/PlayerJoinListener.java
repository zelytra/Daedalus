package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.ItemBuilder;
import fr.zelytra.daedalus.managers.game.GameManager;
import fr.zelytra.daedalus.managers.game.GameStatesEnum;
import fr.zelytra.daedalus.objects.Team;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Map;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent e){

        final Player p = e.getPlayer();

        e.setJoinMessage(null);
        preparePlayer(p, Daedalus.getInstance().getGameManager().getState());

    }


    private void preparePlayer(Player p, GameStatesEnum state){

        Bukkit.broadcastMessage("§7[§a+§7] §f"+p.getName());

        if(state.equals(GameStatesEnum.WAIT)){
            p.setGameMode(GameMode.ADVENTURE);
            p.setFoodLevel(20);
            p.setHealth(p.getMaxHealth());
            p.getInventory().setItem(0, new ItemBuilder(Material.WHITE_BANNER, "§73Team selection").getItemStack());
            // TP TO SPAWN
        }else if(state.equals(GameStatesEnum.RUNNING)){

        }else{
            p.setGameMode(GameMode.SPECTATOR);
        }

        p.setPlayerListHeader("\n§7[§6Daedalus§7]\n");
        p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16);
        if(Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()) != null){

            p.setDisplayName(Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()).getPrefix()+p.getName()+Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()).getSuffix());
            p.setPlayerListName(Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()).getPrefix()+p.getName()+Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()).getSuffix());

        }

    }



}
