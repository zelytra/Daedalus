package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.ItemBuilder;
import fr.zelytra.daedalus.managers.game.GameManager;
import fr.zelytra.daedalus.objects.Team;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Map;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent e){

        final Player p = e.getPlayer();

        e.setJoinMessage("§7[§a+§7] §f"+p.getName());

        if(Daedalus.getInstance().getGameManager().isWaiting()){

            preparePlayer(p);

        }

    }


    private void preparePlayer(Player p){

        p.setPlayerListHeader("\n§7[§6Daedalus§7]\n");
        p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16);
        p.getInventory().setItem(0, new ItemBuilder(Material.WHITE_BANNER, "§7Sélection des équipes").getItemStack());
        if(Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()) != null){

            p.setDisplayName(Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()).getPrefix()+p.getName()+Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()).getSuffix());
            p.setPlayerListName(Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()).getPrefix()+p.getName()+Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()).getSuffix());

        }

    }

}
