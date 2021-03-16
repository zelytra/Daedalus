package fr.zelytra.daedalus.events.waiting.inventory;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;

public class InventoryListener implements Listener {

    @EventHandler
    public void onItemMove(InventoryClickEvent e){

        final Player p = (Player) e.getWhoClicked();
        final InventoryView inventory = e.getView();
        if(Daedalus.getInstance().getGameManager().isWaiting()){

            e.setCancelled(true);
            if(inventory.getTitle().equals("§3Sélection des équipes")){

                if(e.getCurrentItem() != null){

                    switch (e.getCurrentItem().getType()){

                        case RED_BANNER:
                            Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                            Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.RED).addPlayer(p.getUniqueId());
                            p.sendMessage("§8Vous avez rejoint l'équipe §cROUGE");
                            p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.5f, 1.0f);
                            break;

                        case BLUE_BANNER:
                            Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                            Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.BLUE).addPlayer(p.getUniqueId());
                            p.sendMessage("§8Vous avez rejoint l'équipe §9BLEUE");
                            p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.5f, 1.0f);
                            break;

                        case GREEN_BANNER:
                            Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                            Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.GREEN).addPlayer(p.getUniqueId());
                            p.sendMessage("§8Vous avez rejoint l'équipe §aVERTE");
                            p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.5f, 1.0f);
                            break;

                        case YELLOW_BANNER:
                            Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                            Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.YELLOW).addPlayer(p.getUniqueId());
                            p.sendMessage("§8Vous avez rejoint l'équipe §eJAUNE");
                            p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.5f, 1.0f);
                            break;

                        case GRAY_BANNER:
                            Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                            Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.GRAY).addPlayer(p.getUniqueId());
                            p.sendMessage("§8Vous avez rejoint l'équipe §7MINOTAURE");
                            p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.5f, 1.0f);
                            break;

                    }

                    p.closeInventory();
                }

            }

        }

    }
}
