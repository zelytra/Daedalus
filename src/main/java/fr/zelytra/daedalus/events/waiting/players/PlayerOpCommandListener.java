package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.guiBuilder.VisualItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class PlayerOpCommandListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {

        if (e.getPlayer().isOp() && e.getMessage().startsWith("/op") && e.getMessage().split(" ").length == 2 && Daedalus.getInstance().getGameManager().isWaiting()) {

            String pn = e.getMessage().split(" ")[1];

            if (Bukkit.getPlayer(pn) != null) {
                Bukkit.getPlayer(pn).getInventory().clear();
                Bukkit.getPlayer(pn).getInventory().setItem(0, new VisualItemStack(Material.WHITE_BANNER, "§7Team selection", false).getItem());
                Bukkit.getPlayer(pn).getInventory().setItem(0, new VisualItemStack(Material.COMPARATOR, "§7Game settings", false).getItem());
                Bukkit.getPlayer(pn).getInventory().setItem(4, new VisualItemStack(Material.BELL, "§6Start game", false, "§7Click here to start your game with the actual configuration").getItem());
            }
        }

        if (e.getMessage().startsWith("/deop") && e.getMessage().split(" ").length == 2 && Daedalus.getInstance().getGameManager().isWaiting()) {

            String pn = e.getMessage().split(" ")[1];

            if (Bukkit.getPlayer(pn) != null) {
                Bukkit.getPlayer(pn).getInventory().clear();
                Bukkit.getPlayer(pn).getInventory().setItem(4, new VisualItemStack(Material.WHITE_BANNER, "§7Team selection", false).getItem());
            }

        }

    }

    @EventHandler
    public void onCommandServer(ServerCommandEvent e) {

        if (e.getCommand().startsWith("op") && e.getCommand().split(" ").length == 2 && Daedalus.getInstance().getGameManager().isWaiting()) {

            String pn = e.getCommand().split(" ")[1];

            if (Bukkit.getPlayer(pn) != null) {
                Bukkit.getPlayer(pn).getInventory().clear();
                Bukkit.getPlayer(pn).getInventory().setItem(8, new VisualItemStack(Material.WHITE_BANNER, "§7Team selection", false).getItem());
                Bukkit.getPlayer(pn).getInventory().setItem(0, new VisualItemStack(Material.COMPARATOR, "§7Game settings", false).getItem());
                Bukkit.getPlayer(pn).getInventory().setItem(4, new VisualItemStack(Material.BELL, "§6Start game", false, "§7Click here to start your game with the actual configuration").getItem());
            }

        }

        if (e.getCommand().startsWith("deop") && e.getCommand().split(" ").length == 2 && Daedalus.getInstance().getGameManager().isWaiting()) {

            String pn = e.getCommand().split(" ")[1];

            if (Bukkit.getPlayer(pn) != null) {
                Bukkit.getPlayer(pn).getInventory().clear();
                Bukkit.getPlayer(pn).getInventory().setItem(4, new VisualItemStack(Material.WHITE_BANNER, "§7Team selection", false).getItem());
            }

        }

    }

}
 