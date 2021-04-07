package fr.zelytra.daedalus.managers.items;

import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.List;

public class CraftManager {

    public CraftManager(){
        initCraftrecipe();
        initBlacklistedCraft();
    }

    private void initBlacklistedCraft() {
        List<Recipe> recipes = Bukkit.getRecipesFor(new ItemStack(Material.PISTON));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.STICKY_PISTON)));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.TNT)));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.TNT_MINECART)));
        recipes.stream().filter(r -> r instanceof Keyed).map(r -> ((Keyed) r).getKey()).forEach(Bukkit::removeRecipe);
    }

    private void initCraftrecipe() {
    }
}
