package fr.zelytra.daedalus.events.waiting.gui;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.guiBuilder.Interface;
import fr.zelytra.daedalus.builders.guiBuilder.InterfaceBuilder;
import fr.zelytra.daedalus.builders.guiBuilder.VisualItemStack;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.faction.FactionsEnum;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TeamSelector implements Listener, Interface {
    List<Material> banners = new ArrayList<>();

    {
        banners.add(Material.BLUE_BANNER);
        banners.add(Material.WHITE_BANNER);
        banners.add(Material.YELLOW_BANNER);
        banners.add(Material.RED_BANNER);
        banners.add(Material.GREEN_BANNER);
        banners.add(Material.BROWN_BANNER);
    }

    @EventHandler
    public void onItemClick(PlayerInteractEvent e) {

        if (Daedalus.getInstance().getGameManager().isWaiting() && (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) {

            if (e.getItem() != null && banners.contains(e.getItem().getType())) {
                InterfaceBuilder interfaceBuilder = new InterfaceBuilder(InventoryType.DROPPER, GameSettings.LANG.textOf("menu.teamChooser"));
                interfaceBuilder.setContent(contentBuilder());
                interfaceBuilder.open(e.getPlayer());
            }
        }
    }

    @Override
    public ItemStack[] contentBuilder() {
        ItemStack[] content = new ItemStack[9];

        int enumCount = 0;
        for (int slot = 0; slot < content.length; slot += 2) {
            content[slot] = new VisualItemStack(FactionsEnum.values()[enumCount].getBanner(), FactionsEnum.values()[enumCount].getPrefix() + FactionsEnum.values()[enumCount].getName(), false).getItem();

            Faction faction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(FactionsEnum.values()[enumCount]);
            ItemMeta meta = content[slot].getItemMeta();
            List<String> lore = new ArrayList<>();

            for (Player member : faction.getPlayerList()) {
                lore.add(faction.getType().getPrefix() + "- " + member.getName());
            }
            meta.setLore(lore);
            content[slot].setItemMeta(meta);
            enumCount++;
        }

        return content;
    }

    @EventHandler
    public void onInterfaceInteract(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(GameSettings.LANG.textOf("menu.teamChooser"))) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                for (FactionsEnum factionsEnum : FactionsEnum.values()) {
                    if (e.getCurrentItem().getType() == factionsEnum.getBanner()) {
                        Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(factionsEnum).add((Player) e.getWhoClicked());
                        e.getWhoClicked().closeInventory();
                    }
                }
            }
        }
    }
}
