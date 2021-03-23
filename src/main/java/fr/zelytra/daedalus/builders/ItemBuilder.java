package fr.zelytra.daedalus.builders;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.TemplesGenerationEnum;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder(ItemStack itemStack){
        this.itemStack = itemStack;
    }

    public ItemBuilder(Material material){

        this.itemStack = new ItemStack(material);

    }

    public ItemBuilder(Material material, String name, int stack, String... loreArgs){

        this.itemStack = new ItemStack(material, stack);
        ItemMeta meta = this.itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        ArrayList<String> lore = new ArrayList<>();
        Collections.addAll(lore, loreArgs);
        meta.setLore(lore);
        this.itemStack.setItemMeta(meta);

    }

    public ItemBuilder(Material material, String name){

        this.itemStack = new ItemStack(material);
        ItemMeta meta = this.itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        this.itemStack.setItemMeta(meta);

    }

    public ItemBuilder(Material material, String name, String... loreArgs){

        this.itemStack = new ItemStack(material);
        ItemMeta meta = this.itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        ArrayList<String> lore = new ArrayList<>();
        Collections.addAll(lore, loreArgs);
        meta.setLore(lore);

        this.itemStack.setItemMeta(meta);

    }

    public ItemStack getGenerationSelection(){

        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        ArrayList<String> lore = new ArrayList<>();

        if(Daedalus.getInstance().getGameManager().getTemplesGeneration() == TemplesGenerationEnum.RANDOM){

            meta.setDisplayName(meta.getDisplayName()+" §a[DEFAULT]");
            itemStack.setType(Material.PISTON);
            lore.add("§7Temples and divinities are picked randomly during generation.");

        }else{
            meta.setDisplayName(meta.getDisplayName()+" §d[CUSTOM]");
            itemStack.setType(Material.STICKY_PISTON);
            lore.add("§7Temples and divinities are generated according your selection.");
            lore.add("");
            if(Daedalus.getInstance().getGameManager().getSelectedGods().isEmpty()) {
                lore.add("§cYour selection is empty !");
                lore.add("§cYou have to select §b§c"+Daedalus.getInstance().getGameManager().getGodLimit()+"§c gods");
            }else{
                for(GodsEnum god : Daedalus.getInstance().getGameManager().getSelectedGods()){

                    lore.add("§a- "+god.getName());

                }
                lore.add("");
                if(Daedalus.getInstance().getGameManager().getGodLimit() > Daedalus.getInstance().getGameManager().getSelectedGods().size())
                    lore.add("§cYou still have §b§c"+(Daedalus.getInstance().getGameManager().getGodLimit()-Daedalus.getInstance().getGameManager().getSelectedGods().size())+"§c gods to select");
                else
                    lore.add("§cYou can't' select more gods");
            }
        }
        lore.add("");
        lore.add("§8CLICK TO CHANGE");

        meta.setLore(lore);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

}
