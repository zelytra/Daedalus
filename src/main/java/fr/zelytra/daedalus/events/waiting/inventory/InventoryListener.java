package fr.zelytra.daedalus.events.waiting.inventory;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.InventoryBuilder;
import fr.zelytra.daedalus.builders.ItemBuilder;
import fr.zelytra.daedalus.managers.game.settings.DayCycleEnum;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.game.settings.TemplesGenerationEnum;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.team.Team;
import fr.zelytra.daedalus.managers.team.TeamsEnum;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler
    public void onItemMove(InventoryClickEvent e) {

        final Player p = (Player) e.getWhoClicked();
        final InventoryView inventory = e.getView();
        if (Daedalus.getInstance().getGameManager().isWaiting()) {

            e.setCancelled(true);
            if (inventory.getTitle().equals("§3Team selection")) {

                if (e.getCurrentItem() != null) {

                    Team tp = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId());

                    switch (e.getCurrentItem().getType()) {

                        case RED_BANNER:
                            if(tp.getTeamEnum() == TeamsEnum.RED){
                                Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                                Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.WHITE).addPlayer(p.getUniqueId());
                                p.sendMessage("§8You joined the §fspectator team");
                                p.getInventory().setItem(0, new ItemBuilder(Material.WHITE_BANNER, "§7Team selection").getItemStack());
                            }else{
                                Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                                Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.RED).addPlayer(p.getUniqueId());
                                p.sendMessage("§8You joined the §cred team");
                                p.getInventory().setItem(0, new ItemBuilder(Material.RED_BANNER, "§7Team selection").getItemStack());
                            }

                            p.closeInventory();
                            break;

                        case BLUE_BANNER:
                            if(tp.getTeamEnum() == TeamsEnum.BLUE){
                                Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                                Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.WHITE).addPlayer(p.getUniqueId());
                                p.sendMessage("§8You joined the §fspectator team");
                                p.getInventory().setItem(0, new ItemBuilder(Material.WHITE_BANNER, "§7Team selection").getItemStack());
                            }else {
                                Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                                Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.BLUE).addPlayer(p.getUniqueId());
                                p.sendMessage("§8You joined the §9blue team");
                                p.getInventory().setItem(0, new ItemBuilder(Material.BLUE_BANNER, "§7Team selection").getItemStack());
                            }
                            p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.5f, 1.0f);
                            p.closeInventory();
                            break;

                        case GREEN_BANNER:
                            if(tp.getTeamEnum() == TeamsEnum.GREEN){
                                Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                                Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.WHITE).addPlayer(p.getUniqueId());
                                p.sendMessage("§8You joined the §fspectator team");
                                p.getInventory().setItem(0, new ItemBuilder(Material.WHITE_BANNER, "§7Team selection").getItemStack());
                            }else {
                                Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                                Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.GREEN).addPlayer(p.getUniqueId());
                                p.sendMessage("§8You joined the §agreen team");
                                p.getInventory().setItem(0, new ItemBuilder(Material.GREEN_BANNER, "§7Team selection").getItemStack());
                            }
                            p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.5f, 1.0f);
                            p.closeInventory();
                            break;

                        case YELLOW_BANNER:
                            if(tp.getTeamEnum() == TeamsEnum.YELLOW){
                                Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                                Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.WHITE).addPlayer(p.getUniqueId());
                                p.sendMessage("§8You joined the §fspectator team");
                                p.getInventory().setItem(0, new ItemBuilder(Material.WHITE_BANNER, "§7Team selection").getItemStack());
                            }else {
                                Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                                Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.YELLOW).addPlayer(p.getUniqueId());
                                p.sendMessage("§8You joined the §eyellow team");
                                p.getInventory().setItem(0, new ItemBuilder(Material.YELLOW_BANNER, "§7Team selection").getItemStack());
                            }
                            p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.5f, 1.0f);
                            p.closeInventory();
                            break;

                        case GRAY_BANNER:
                            if(tp.getTeamEnum() == TeamsEnum.MINOS){
                                Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                                Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.WHITE).addPlayer(p.getUniqueId());
                                p.sendMessage("§8You joined the §fspectator team");
                                p.getInventory().setItem(0, new ItemBuilder(Material.WHITE_BANNER, "§7Team selection").getItemStack());
                            }else {
                                Daedalus.getInstance().getGameManager().getTeamManager().removePlayerFromAnyTeam(p.getUniqueId());
                                Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfColor(DyeColor.GRAY).addPlayer(p.getUniqueId());
                                p.sendMessage("§8You joined the §7minos team");
                                p.getInventory().setItem(0, new ItemBuilder(Material.GRAY_BANNER, "§7Team selection").getItemStack());
                            }
                            p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.5f, 1.0f);
                            p.closeInventory();
                            break;

                    }

                    p.closeInventory();
                }

            } else if (inventory.getTitle().equals("§3Game settings")) {

                if (e.getCurrentItem() != null) {

                    switch (e.getCurrentItem().getType()) {

                        case PISTON:
                        case STICKY_PISTON: {

                            Daedalus.getInstance().getGameManager().reverseTempleGeneration();
                            inventory.setItem(e.getSlot(), new ItemBuilder(Material.PISTON, "§6Temples generation").getGenerationSelection());
                            if(GameSettings.GOD_SELECTION == TemplesGenerationEnum.RANDOM){
                                inventory.setItem(10, new ItemStack(Material.AIR));
                            }else{
                                inventory.setItem(10, new ItemBuilder(Material.TOTEM_OF_UNDYING, "§6Gods selection", "", "§8CLICK TO SELECT").getItemStack());
                            }

                            break;
                        }

                        case TOTEM_OF_UNDYING: {


                            p.closeInventory();
                            p.openInventory(new InventoryBuilder("§3Divinities selection", 54).getGodsSelectionInventory());


                            break;
                        }

                        case BOOK: {
                            p.closeInventory();
                            p.openInventory(new InventoryBuilder("§3Miscellaneous settings", 36).getGameSettingsInventory());
                            break;
                        }
                    }
                }
                for(Player player : Bukkit.getOnlinePlayers())
                    player.getInventory().setItem(8, new ItemBuilder(Material.COMPARATOR, "§7Game settings").getSettings());

            }else if (inventory.getTitle().equals("§3Miscellaneous settings")) {
                if (e.getCurrentItem() != null)
                    switch (e.getCurrentItem().getType()){

                        case APPLE: {

                            p.closeInventory();
                            p.openInventory(new InventoryBuilder("§3Apple drop", InventoryType.DROPPER).getAppleSettingsInventory());
                            break;
                        }

                        case GOLDEN_APPLE: {

                            GameSettings.ABSORPTION = !GameSettings.ABSORPTION;
                            p.openInventory(new InventoryBuilder("§3Miscellaneous settings", 36).getGameSettingsInventory());
                            break;
                        }

                        case BLAZE_POWDER: {

                            GameSettings.FRIENDLY_FIRE = !GameSettings.FRIENDLY_FIRE;
                            p.openInventory(new InventoryBuilder("§3Miscellaneous settings", 36).getGameSettingsInventory());
                            break;
                        }

                        case CLOCK: {

                            p.closeInventory();
                            p.openInventory(new InventoryBuilder("§3Day settings", InventoryType.DROPPER).getDaySettingsInventory());

                            break;
                        }

                        case BLAST_FURNACE: {

                            GameSettings.CUT_CLEAN = !GameSettings.CUT_CLEAN;
                            p.openInventory(new InventoryBuilder("§3Miscellaneous settings", 36).getGameSettingsInventory());
                            break;
                        }

                        case SPLASH_POTION: {

                            GameSettings.HARDCORE = !GameSettings.HARDCORE;
                            p.openInventory(new InventoryBuilder("§3Miscellaneous settings", 36).getGameSettingsInventory());
                            break;
                        }

                        case BARRIER: {
                            p.closeInventory();
                            p.performCommand("settings");
                            break;
                        }
                    }
            } else if (inventory.getTitle().equals("§3Apple drop")) {

                if (e.getCurrentItem() != null)
                    switch (e.getCurrentItem().getType()){
                        case RED_BANNER: {
                            if(GameSettings.APPLE_DROP - 1 > 1){
                                --GameSettings.APPLE_DROP;
                            }
                            inventory.setItem(4, new ItemBuilder(Material.APPLE, "§eDrop percent", "§aActual percent: §e"+GameSettings.APPLE_DROP+"%", "", "§7Min - Max: 1% - 40%").getItemStack());
                            break;
                        }

                        case GREEN_BANNER: {
                            if(GameSettings.APPLE_DROP + 1 <= 40) {
                                ++GameSettings.APPLE_DROP;
                            }
                            inventory.setItem(4, new ItemBuilder(Material.APPLE, "§eDrop percent", "§aActual percent: §e"+GameSettings.APPLE_DROP+"%", "", "§7Min - Max: 1% - 40%").getItemStack());
                            break;
                        }

                        case BARRIER: {
                            p.closeInventory();
                            p.openInventory(new InventoryBuilder("§3Miscellaneous settings", 36).getGameSettingsInventory());
                            break;
                        }
                    }

            } else if (inventory.getTitle().equals("§3Day settings")) {

                if (e.getCurrentItem() != null)
                    switch (e.getCurrentItem().getType()){

                        case LANTERN:{

                            GameSettings.DAY_CYCLE = DayCycleEnum.ETERNAL_DAY;
                            p.closeInventory();
                            p.openInventory(new InventoryBuilder("§3Miscellaneous settings", 36).getGameSettingsInventory());
                            break;
                        }

                        case SOUL_LANTERN: {
                            GameSettings.DAY_CYCLE = DayCycleEnum.ETERNAL_NIGHT;
                            p.closeInventory();
                            p.openInventory(new InventoryBuilder("§3Miscellaneous settings", 36).getGameSettingsInventory());
                            break;
                        }

                        case CLOCK: {
                            GameSettings.DAY_CYCLE = DayCycleEnum.DEFAULT;
                            p.closeInventory();
                            p.openInventory(new InventoryBuilder("§3Miscellaneous settings", 36).getGameSettingsInventory());
                            break;
                        }
                    }

            } else if (inventory.getTitle().equals("§3Divinities selection")) {

                if (e.getCurrentItem() != null)
                    switch (e.getCurrentItem().getType()) {
                        case TOTEM_OF_UNDYING: {

                            String name = e.getCurrentItem().getItemMeta().getDisplayName();
                            name = ChatColor.stripColor(name);
                            GodsEnum god;
                            try {
                                god = GodsEnum.valueOf(name.toUpperCase());
                            } catch (IllegalArgumentException error) {
                                break;
                            }

                            if (!god.isSelected() && GameSettings.GOD_LIST.size() == GameSettings.GOD_LIMIT) {
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.f, 1.f);
                                break;
                            }

                            god.setSelected(!god.isSelected());

                            if (god.isSelected())
                                GameSettings.GOD_LIST.add(god);
                            else
                                GameSettings.GOD_LIST.remove(god);

                            p.openInventory(new InventoryBuilder("§3Divinities selection", 54).getGodsSelectionInventory());

                            break;
                        }
                        case BARRIER: {

                            p.closeInventory();
                            p.performCommand("settings");

                            break;
                        }

                        case RED_BANNER: {

                            if (Daedalus.getInstance().getGameManager().decreaseGodLimit()) {
                                p.openInventory(new InventoryBuilder("§3Divinities selection", 54).getGodsSelectionInventory());
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.f, 1.f);
                            }
                            break;
                        }

                        case GREEN_BANNER: {

                            if (Daedalus.getInstance().getGameManager().increaseGodLimit()) {
                                p.openInventory(new InventoryBuilder("§3Divinities selection", 54).getGodsSelectionInventory());
                            } else {
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.f, 1.f);
                            }
                            break;
                        }
                    }

                for (Player player : Bukkit.getOnlinePlayers())
                    player.getInventory().setItem(8, new ItemBuilder(Material.COMPARATOR, "§7Game settings").getSettings());
            }

        }

    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){

        if(!Daedalus.getInstance().getGameManager().isWaiting())
            return;

        final Player p = (Player) e.getPlayer();
        final InventoryView inventory = e.getView();

        if (inventory.getTitle().contains("§3Game Settings") || inventory.getTitle().contains("§3Divinities selection")) {
            Bukkit.getScheduler().scheduleAsyncDelayedTask(Daedalus.getInstance(), ()-> checkSettings(p), 5);
        }

    }

    private void checkSettings(Player p){

        p = Bukkit.getPlayer(p.getUniqueId());

        assert p != null && p.isOp();
        if(p.getOpenInventory().getTitle().contains("§3"))
            return;

        if(GameSettings.GOD_LIMIT != GameSettings.GOD_LIST.size() && GameSettings.GOD_SELECTION == TemplesGenerationEnum.CHOSEN){

            p.sendMessage("§c[SETTINGS ALERT] Your gods selection doesn't match with the set limit ! Please adjust your selection correctly or your game will not be able to start.");
            p.playSound(p.getLocation(), Sound.ENTITY_WITCH_HURT, 1.f, 1.f);

        }
    }
}
