package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.builders.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class PlayerOpCommandListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){

        if(e.getPlayer().isOp() && e.getMessage().startsWith("/op") && e.getMessage().split(" ").length == 2){

            String pn = e.getMessage().split(" ")[1];

            if(Bukkit.getPlayer(pn) != null){
                Bukkit.getPlayer(pn).getInventory().clear();
                Bukkit.getPlayer(pn).getInventory().setItem(8, new ItemBuilder(Material.WHITE_BANNER, "§7Team selection").getItemStack());
                Bukkit.getPlayer(pn).getInventory().setItem(0, new ItemBuilder(Material.COMPARATOR, "§7Game settings").getSettings());
                Bukkit.getPlayer(pn).getInventory().setItem(4, new ItemBuilder(Material.BELL, "§6Start game", "§7Click here to start your game with the actual configuration").getItemStack());
            }
        }

    }

    @EventHandler
    public void onCommandServer(ServerCommandEvent e){

        if(e.getCommand().startsWith("/op") && e.getCommand().split(" ").length == 2){

            String pn = e.getCommand().split(" ")[1];

            if(Bukkit.getPlayer(pn) != null){
                Bukkit.getPlayer(pn).getInventory().clear();
                Bukkit.getPlayer(pn).getInventory().setItem(8, new ItemBuilder(Material.WHITE_BANNER, "§7Team selection").getItemStack());
                Bukkit.getPlayer(pn).getInventory().setItem(0, new ItemBuilder(Material.COMPARATOR, "§7Game settings").getSettings());
                Bukkit.getPlayer(pn).getInventory().setItem(4, new ItemBuilder(Material.BELL, "§6Start game", "§7Click here to start your game with the actual configuration").getItemStack());
            }

        }

    }

}
 