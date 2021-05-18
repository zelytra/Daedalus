package fr.zelytra.daedalus.managers.items;

import fr.zelytra.daedalus.builders.customCraft.ShapedRecipeBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.List;

public class CraftManager {

    public CraftManager(){
        initBlacklistedCraft();
        initCraftRecipe();
    }

    private void initBlacklistedCraft() {
        List<Recipe> recipes = Bukkit.getRecipesFor(new ItemStack(Material.PISTON));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.STICKY_PISTON)));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.TNT)));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.TNT_MINECART)));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.STONE)));
        recipes.stream().filter(r -> r instanceof Keyed).map(r -> ((Keyed) r).getKey()).forEach(Bukkit::removeRecipe);
    }

    private void initCraftRecipe() {
        ShapedRecipeBuilder shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.ZEUS_TOTEM);
        shapedRecipeBuilder.setShape("a/a", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a', new ItemStack(Material.FEATHER,4));
        shapedRecipeBuilder.assigneSymbol('c', new ItemStack(Material.DIAMOND,5));
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.POSEIDON_TOTEM);
        shapedRecipeBuilder.setShape("a/a", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a', Material.HEART_OF_THE_SEA);
        shapedRecipeBuilder.assigneSymbol('c', new ItemStack(Material.LAPIS_BLOCK,3));
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(Material.HEART_OF_THE_SEA);
        shapedRecipeBuilder.setShape("a/a", "/b/", "a/a");
        shapedRecipeBuilder.assigneSymbol('a', Material.LAPIS_LAZULI);
        shapedRecipeBuilder.assigneSymbol('b', Material.WATER_BUCKET);
        shapedRecipeBuilder.assigneSymbol('/', Material.DIAMOND);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.HADES_TOTEM);
        shapedRecipeBuilder.setShape("a/a", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a',new ItemStack(Material.BONE,12));
        shapedRecipeBuilder.assigneSymbol('c', new ItemStack(Material.OBSIDIAN,2));
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.ARES_TOTEM);
        shapedRecipeBuilder.setShape("a/c", "/b/", "d/e");
        shapedRecipeBuilder.assigneSymbol('a',Material.IRON_HELMET);
        shapedRecipeBuilder.assigneSymbol('c', Material.IRON_LEGGINGS);
        shapedRecipeBuilder.assigneSymbol('d', Material.DIAMOND_CHESTPLATE);
        shapedRecipeBuilder.assigneSymbol('e', Material.DIAMOND_BOOTS);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.APHRODITE_TOTEM);
        shapedRecipeBuilder.setShape("a/a", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a',new ItemStack(Material.GOLDEN_APPLE,2));
        shapedRecipeBuilder.assigneSymbol('c', new ItemStack(Material.REDSTONE_BLOCK,4));
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.DEMETER_TOTEM);
        shapedRecipeBuilder.setShape("a/a", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a',Material.CAKE);
        shapedRecipeBuilder.assigneSymbol('c', new ItemStack(Material.WHEAT,2));
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.HERMES_TOTEM);
        shapedRecipeBuilder.setShape("a/a", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a',new ItemStack(Material.FEATHER,8));
        shapedRecipeBuilder.assigneSymbol('c', new ItemStack(Material.EMERALD,5));
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.ARTEMIS_TOTEM);
        shapedRecipeBuilder.setShape("a/d", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a',Material.BOW);
        shapedRecipeBuilder.assigneSymbol('d',Material.CROSSBOW);
        shapedRecipeBuilder.assigneSymbol('c', new ItemStack(Material.ARROW,16));
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.ATHENA_TOTEM);
        shapedRecipeBuilder.setShape("ada", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a',Material.BOOK);
        shapedRecipeBuilder.assigneSymbol('d',Material.ENCHANTING_TABLE);
        shapedRecipeBuilder.assigneSymbol('c', Material.BOOKSHELF);
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.DIONYSOS_TOTEM);
        shapedRecipeBuilder.setShape("a/a", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a',new ItemStack(Material.GLASS_BOTTLE,32));
        shapedRecipeBuilder.assigneSymbol('c', new ItemStack(Material.EMERALD,5));
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.DIVINE_TRACKER);
        shapedRecipeBuilder.setShape("a/a", "aka", "crc");
        shapedRecipeBuilder.assigneSymbol('a',new ItemStack(Material.EMERALD,2));
        shapedRecipeBuilder.assigneSymbol('r',new ItemStack(Material.REDSTONE,4));
        shapedRecipeBuilder.assigneSymbol('c', Material.FLINT);
        shapedRecipeBuilder.assigneSymbol('k', new ItemStack(Material.IRON_BLOCK,2));
        shapedRecipeBuilder.assigneSymbol('/', Material.OBSIDIAN);
        shapedRecipeBuilder.register();
    }
}
