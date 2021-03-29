package fr.zelytra.daedalus.managers.scenarios;

import fr.zelytra.daedalus.managers.game.settings.DayCycleEnum;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.ArrayList;

public enum ScenariosEnum {

    HARDCORE("Hardcore", Material.SPLASH_POTION),
    FRIENDLY_FIRE("Friendly fire", Material.BLAZE_POWDER),
    DAY_CYCLE("Day cycle", Material.CLOCK),
    ABSORPTION("Absorption", Material.GOLDEN_APPLE),
    APPLE("Apple drop chance", Material.APPLE),
    CUT_CLEAN("Auto smelt", Material.BLAST_FURNACE);


    private final String name;
    private final Material material;

    ScenariosEnum(String name, Material material){
        this.name = name;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemStack getItemStack(){

        switch (getMaterial()){

            case SPLASH_POTION: {

                ItemStack item = new ItemStack(Material.SPLASH_POTION);
                PotionMeta meta = (PotionMeta) item.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();
                assert meta != null;
                meta.setColor(Color.FUCHSIA);
                meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                meta.setDisplayName("§e"+HARDCORE.getName());

                meta.setDisplayName("§e"+HARDCORE.getName());

                lore.add("§7If enabled, saturation cannot regenerate health.");
                lore.add("");
                if(GameSettings.HARDCORE)
                    lore.add("§bStatus §7-> §aenabled");
                else
                    lore.add("§bStatus §7-> §cdisabled");

                meta.setLore(lore);

                item.setItemMeta(meta);

                return item;
            }
            case BLAZE_POWDER: {
                ItemStack item = new ItemStack(Material.BLAZE_POWDER);
                ItemMeta meta = item.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();

                assert meta != null;
                meta.setDisplayName("§e"+FRIENDLY_FIRE.getName());

                lore.add("§7If enabled, friendly fire will be allowed");
                lore.add("");
                if(GameSettings.FRIENDLY_FIRE)
                    lore.add("§bStatus §7-> §aenabled");
                else
                    lore.add("§bStatus §7-> §cdisabled");

                meta.setLore(lore);

                item.setItemMeta(meta);

                return item;
            }
            case CLOCK: {

                ItemStack item = new ItemStack(Material.CLOCK);
                ItemMeta meta = item.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();

                assert meta != null;
                meta.setDisplayName("§e"+DAY_CYCLE.getName());

                lore.add("§7Time cycle aesthetic option");
                lore.add("");
                if(GameSettings.DAY_CYCLE == DayCycleEnum.ETERNAL_DAY)
                    lore.add("§bStatus §7-> §aEternal day");
                else if(GameSettings.DAY_CYCLE == DayCycleEnum.ETERNAL_NIGHT)
                    lore.add("§bStatus §7-> §aEternal night");
                else
                    lore.add("§bStatus §7-> §aNormal cycle");
                lore.add("");
                lore.add("§cIT WILL NOT AFFECT YOUR GAMEPLAY");
                lore.add("§cThis option is purely decorative");

                meta.setLore(lore);

                item.setItemMeta(meta);

                return item;
            }
            case GOLDEN_APPLE: {

                ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
                ItemMeta meta = item.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();

                assert meta != null;
                meta.setDisplayName("§e"+ABSORPTION.getName());

                lore.add("§7If disabled, NO absorption effect on Golden Apples");
                lore.add("");
                if(GameSettings.ABSORPTION)
                    lore.add("§bStatus §7-> §aenabled");
                else
                    lore.add("§bStatus §7-> §cdisabled");

                meta.setLore(lore);

                item.setItemMeta(meta);

                return item;
            }
            case APPLE: {

                ItemStack item = new ItemStack(Material.APPLE);
                ItemMeta meta = item.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();

                assert meta != null;
                meta.setDisplayName("§e"+APPLE.getName());

                lore.add("§7Define the apple drop chance from any leaves");
                lore.add("");
                lore.add("§bDrop chance §7-> §a"+GameSettings.APPLE_DROP*100+"%");

                meta.setLore(lore);

                item.setItemMeta(meta);

                return item;
            }
            case BLAST_FURNACE: {

                ItemStack item = new ItemStack(Material.BLAST_FURNACE);
                ItemMeta meta = item.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();

                assert meta != null;
                meta.setDisplayName("§e"+CUT_CLEAN.getName());

                lore.add("§7If enabled, iron/gold ore will drop directly an ingot");
                lore.add("");
                if(GameSettings.CUT_CLEAN)
                    lore.add("§bStatus §7-> §aenabled");
                else
                    lore.add("§bStatus §7-> §cdisabled");

                meta.setLore(lore);

                item.setItemMeta(meta);

                return item;
            }

        }

        return null;
    }
}
