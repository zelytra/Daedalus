package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.InventoryBuilder;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.game.settings.TemplesGenerationEnum;
import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){

        final Player p = e.getPlayer();
        if(e.getItem() == null)
            return;

        if(Daedalus.getInstance().getGameManager().isWaiting()||Daedalus.getInstance().getGameManager().isStarted()){

            if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK){

                if(Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals("§7Team selection")){

                    Inventory inv = new InventoryBuilder("§3Team selection", InventoryType.DROPPER).getInventory();

                    inv.setItem(0, Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.RED).getBanner());
                    inv.setItem(2, Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.GREEN).getBanner());
                    inv.setItem(6, Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.BLUE).getBanner());
                    inv.setItem(8, Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.YELLOW).getBanner());
                    inv.setItem(4, Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.GRAY).getBanner());

                    if(!p.getOpenInventory().getTitle().contains("§3"))
                        p.openInventory(inv);

                }else if(Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals("§7Game settings")){
                    if(!p.getOpenInventory().getTitle().contains("§3"))
                        p.performCommand("settings");
                }else if(Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals("§6Start game")){

                    if(GameSettings.GOD_LIMIT != GameSettings.GOD_LIST.size() && GameSettings.GOD_SELECTION == TemplesGenerationEnum.CHOSEN){
                        p.sendMessage("§c[SETTINGS ALERT] Your gods selection doesn't match with the set limit ! Please adjust your selection correctly or your game will not be able to start.");
                        p.playSound(p.getLocation(), Sound.ENTITY_WITCH_HURT, 1.f, 1.f);
                    }else{
                        if(Daedalus.getInstance().getGameManager().isStarting()){
                            p.sendMessage("§7(You can cancel the start by opening game settings)");
                            return;
                        }
                        Daedalus.getInstance().getGameManager().preStart(p);
                    }

                }

            }

        }

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(Daedalus.getInstance().getGameManager().isWaiting())
            e.setCancelled(true);
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e){

        if(Daedalus.getInstance().getGameManager().isWaiting())
            e.setCancelled(true);

    }
}