package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.InventoryBuilder;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){

        final Player p = e.getPlayer();
        if(e.getItem() == null)
            return;

        if(Daedalus.getInstance().getGameManager().isWaiting()){

            if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK){

                if(Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals("§7Sélection des équipes")){

                    Inventory inv = new InventoryBuilder("§3Sélection des équipes", InventoryType.DROPPER).getInventory();

                    inv.setItem(0, Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.RED).getBanner());
                    inv.setItem(2, Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.GREEN).getBanner());
                    inv.setItem(6, Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.BLUE).getBanner());
                    inv.setItem(8, Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.YELLOW).getBanner());
                    inv.setItem(4, Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.GRAY).getBanner());

                    p.openInventory(inv);

                }

            }

        }


    }
}