package fr.zelytra.daedalus.builders;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.scenarios.ScenariosEnum;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class InventoryBuilder {

    private Inventory inventory;

    public InventoryBuilder(String name, int size){
        this.inventory = Bukkit.createInventory(null, size, name);
    }

    public InventoryBuilder(String name, InventoryType type){
        this.inventory = Bukkit.createInventory(null, type, name);
    }

    public Inventory getInventory(){
        return inventory;
    }

    public Inventory getGodsSelectionInventory(){


        int green = 0;
        int red = 5;
        int bedrock = 4;
        int selected = 1;
        int unselected = 6;
        boolean selectedBool = true;
        boolean unselectedBool = true;

        for(int i = 0; i < 5; i++){

            inventory.setItem(green, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
            inventory.setItem(red, new ItemStack(Material.RED_STAINED_GLASS_PANE));

            green += 3;
            red += 3;

            inventory.setItem(green, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
            inventory.setItem(red, new ItemStack(Material.RED_STAINED_GLASS_PANE));

            green += 6;
            red += 6;

            inventory.setItem(bedrock, new ItemStack(Material.BEDROCK));

            bedrock +=9 ;

        }

        for(GodsEnum god : GodsEnum.values()){

            ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
            ItemMeta meta = totem.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            assert meta != null;
            meta.setDisplayName("§7"+god.getName());
            if(god.isSelected()){


                lore.add("§a[SELECTED]");
                meta.setLore(lore);
                totem.setItemMeta(meta);

                inventory.setItem(selected, totem);

                if(selectedBool)
                    selected += 1;
                else
                    selected += 8;

                selectedBool = !selectedBool;
            }else{

                lore.add("§c[UNSELECTED]");
                meta.setLore(lore);
                totem.setItemMeta(meta);

                inventory.setItem(unselected, totem);


                if(unselectedBool)
                    unselected += 1;
                else
                    unselected += 8;

                unselectedBool = !unselectedBool;
            }

        }

        inventory.setItem(22, new ItemBuilder(Material.BARRIER, "§cGo back").getItemStack());

        inventory.setItem(45, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        inventory.setItem(46, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        inventory.setItem(47, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        inventory.setItem(51, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inventory.setItem(52, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        inventory.setItem(53, new ItemStack(Material.RED_STAINED_GLASS_PANE));


        inventory.setItem(48, new BannerBuilder("§cRemove one", Material.RED_BANNER, new Pattern(DyeColor.RED, PatternType.BASE), new Pattern(DyeColor.WHITE, PatternType.STRIPE_MIDDLE), new Pattern(DyeColor.RED, PatternType.BORDER)).getBanner());
        inventory.setItem(50, new BannerBuilder("§aAdd one", Material.GREEN_BANNER, new Pattern(DyeColor.GREEN, PatternType.BASE), new Pattern(DyeColor.WHITE, PatternType.STRIPE_CENTER), new Pattern(DyeColor.WHITE, PatternType.STRIPE_MIDDLE), new Pattern(DyeColor.GREEN, PatternType.BORDER), new Pattern(DyeColor.GREEN, PatternType.STRIPE_BOTTOM), new Pattern(DyeColor.GREEN, PatternType.STRIPE_TOP)).getBanner());
        inventory.setItem(49, new ItemBuilder(Material.TOTEM_OF_UNDYING, "§6Gods limit", GameSettings.GOD_LIMIT, "", "§7Maximum: 10", "§7Minimum: 4").getItemStack());


        return inventory;
    }

    public Inventory getGameSettingsInventory(){


        inventory.setItem(10, ScenariosEnum.FRIENDLY_FIRE.getItemStack());
        inventory.setItem(12, ScenariosEnum.HARDCORE.getItemStack());
        inventory.setItem(14, ScenariosEnum.APPLE.getItemStack());
        inventory.setItem(16, ScenariosEnum.ABSORPTION.getItemStack());
        inventory.setItem(20, ScenariosEnum.DAY_CYCLE.getItemStack());
        inventory.setItem(24, ScenariosEnum.CUT_CLEAN.getItemStack());
        inventory.setItem(22, new ItemBuilder(Material.BARRIER, "§cGo back").getItemStack());

        return inventory;
    }

}
