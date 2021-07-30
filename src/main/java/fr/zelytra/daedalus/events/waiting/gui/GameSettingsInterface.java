package fr.zelytra.daedalus.events.waiting.gui;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.guiBuilder.Interface;
import fr.zelytra.daedalus.builders.guiBuilder.InterfaceBuilder;
import fr.zelytra.daedalus.builders.guiBuilder.VisualItemStack;
import fr.zelytra.daedalus.builders.guiBuilder.VisualType;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.DayCycleEnum;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.languages.Lang;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Team;

public class GameSettingsInterface implements Listener, Interface {
    private String interfaceName = "§6Game settings";
    private final Material material = Material.COMPARATOR;
    private static int dayCount = 0;


    @EventHandler
    public void onItemClick(PlayerInteractEvent e) {

        if (Daedalus.getInstance().getGameManager().isWaiting() && (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) {

            if (e.getItem() != null && e.getItem().getType() == material) {

                if (Daedalus.getInstance().getGameManager().isStarted()) {
                    Daedalus.getInstance().getGameManager().setStarted(false);
                    Bukkit.broadcastMessage(Message.getPlayerPrefixe()+GameSettings.LANG.textOf("maze.startCancel"));
                    return;
                }

                InterfaceBuilder interfaceBuilder = new InterfaceBuilder(54, interfaceName);
                interfaceBuilder.setContent(contentBuilder());
                interfaceBuilder.open(e.getPlayer());
            }
        }
    }

    @Override
    public ItemStack[] contentBuilder() {
        ItemStack[] content = new ItemStack[54];

        content[11] = new VisualItemStack(Material.APPLE, "§6Apple drop", false, "", "§8[§f" + GameSettings.APPLE_DROP + "%§8]").getItem();

        content[13] = new VisualItemStack(Material.CLOCK, "§6Light cycle", false, ""
                , "§8- " + (GameSettings.DAY_CYCLE == DayCycleEnum.DEFAULT ? "§a" : "§c") + "Default"
                , "§8- " + (GameSettings.DAY_CYCLE == DayCycleEnum.ETERNAL_DAY ? "§a" : "§c") + "Eternal Day"
                , "§8- " + (GameSettings.DAY_CYCLE == DayCycleEnum.ETERNAL_NIGHT ? "§a" : "§c") + "Eternal Night"
        ).getItem();

        content[15] = new VisualItemStack(Material.IRON_SWORD, "§6Friendly fire", false, "", "§8[" + (GameSettings.FRIENDLY_FIRE ? "§aTRUE" : "§cFALSE") + "§8]").getItem();
        ItemMeta meta = content[15].getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        content[15].setItemMeta(meta);

        content[28] = new VisualItemStack(Material.GOLDEN_APPLE, "§6Golden apple saturation", false, "", "§8[" + (GameSettings.ABSORPTION ? "§aTRUE" : "§cFALSE") + "§8]").getItem();
        content[30] = new VisualItemStack((GameSettings.CUT_CLEAN ? Material.IRON_INGOT : Material.IRON_ORE), "§6CutClean", false, "", "§8[" + (GameSettings.CUT_CLEAN ? "§aTRUE" : "§cFALSE") + "§8]").getItem();
        content[32] = new VisualItemStack(Material.TOTEM_OF_UNDYING, "§6HardCore", false, "", "§8[" + (GameSettings.HARDCORE ? "§aTRUE" : "§cFALSE") + "§8]").getItem();

        content[34] = new VisualItemStack(Material.PLAYER_HEAD, "§6Language", false, ""
                , (GameSettings.LANG == Lang.EN ? "§a" : "§c")+"English"
                , (GameSettings.LANG == Lang.FR ? "§a" : "§c")+"Français"
                , (GameSettings.LANG == Lang.ES ? "§a" : "§c")+"Español"
                , (GameSettings.LANG == Lang.DE ? "§a" : "§c")+"Deutsch"
        ).getItem();

        for (int x = 45; x < 54; x++)
            content[x] = VisualType.BLANK_GREEN_GLASSE.getItem();

        content[49] = new VisualItemStack(Material.BARRIER, "§cReset settings", false).getItem();

        return content;
    }

    @EventHandler
    public void onInterfaceInteract(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(interfaceName)) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                switch (e.getCurrentItem().getType()) {
                    case APPLE:
                        if (e.getClick() == ClickType.LEFT) {
                            if ((GameSettings.APPLE_DROP -= 2) <= 2)
                                GameSettings.APPLE_DROP = 2;

                        } else if (e.getClick() == ClickType.RIGHT) {
                            if ((GameSettings.APPLE_DROP += 2) >= 40)
                                GameSettings.APPLE_DROP = 40;
                        }
                        break;

                    case CLOCK:
                        GameSettings.DAY_CYCLE = DayCycleEnum.values()[(dayCount++ >= 2 ? dayCount = 0 : dayCount)];
                        switch (GameSettings.DAY_CYCLE) {
                            case DEFAULT:
                                e.getWhoClicked().getWorld().setFullTime(23250);
                                break;
                            case ETERNAL_NIGHT:
                                e.getWhoClicked().getWorld().setFullTime(18000);
                                break;
                            case ETERNAL_DAY:
                                e.getWhoClicked().getWorld().setFullTime(6000);
                                break;
                        }
                        break;

                    case IRON_SWORD:
                        GameSettings.FRIENDLY_FIRE = (!GameSettings.FRIENDLY_FIRE);
                        for (Faction faction : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
                            for (Team team : faction.getFactionScoreBoard().getScoreboard().getTeams()) {
                                team.setAllowFriendlyFire(GameSettings.FRIENDLY_FIRE);
                            }
                        }
                        break;
                    case GOLDEN_APPLE:
                        GameSettings.ABSORPTION = (!GameSettings.ABSORPTION);
                        break;

                    case IRON_INGOT:
                    case IRON_ORE:
                        GameSettings.CUT_CLEAN = (!GameSettings.CUT_CLEAN);
                        break;

                    case TOTEM_OF_UNDYING:
                        GameSettings.HARDCORE = (!GameSettings.HARDCORE);
                        e.getWhoClicked().getWorld().setGameRule(GameRule.NATURAL_REGENERATION, GameSettings.HARDCORE);
                        break;

                    case BARRIER:
                        GameSettings.reset();
                        update(e.getWhoClicked().getWorld());
                        break;
                    case PLAYER_HEAD:
                        GameSettings.LANG = Lang.values()[(dayCount++ >= 3 ? dayCount = 0 : dayCount)];
                        break;
                }
                InterfaceBuilder interfaceBuilder = new InterfaceBuilder(54, interfaceName);
                interfaceBuilder.setContent(contentBuilder());
                interfaceBuilder.open((Player) e.getWhoClicked());
            }
        }
    }

    private void update(World world) {
        for (Faction faction : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
            for (Team team : faction.getFactionScoreBoard().getScoreboard().getTeams()) {
                team.setAllowFriendlyFire(GameSettings.FRIENDLY_FIRE);
            }
        }
        world.setGameRule(GameRule.NATURAL_REGENERATION, GameSettings.HARDCORE);

        switch (GameSettings.DAY_CYCLE) {
            case DEFAULT:
                world.setFullTime(23250);
                break;
            case ETERNAL_NIGHT:
                world.setFullTime(18000);
                break;
            case ETERNAL_DAY:
                world.setFullTime(6000);
                break;
        }
    }
}
