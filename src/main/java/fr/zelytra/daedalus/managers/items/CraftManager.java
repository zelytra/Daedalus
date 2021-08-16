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
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.LODESTONE)));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.TNT_MINECART)));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.STONE)));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.MAP)));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.BEACON)));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.NETHER_STAR)));
        recipes.addAll(Bukkit.getRecipesFor(new ItemStack(Material.CHORUS_FLOWER)));
        recipes.stream().filter(r -> r instanceof Keyed).map(r -> ((Keyed) r).getKey()).forEach(Bukkit::removeRecipe);
    }

    private void initCraftRecipe() {
        ShapedRecipeBuilder shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.ZEUS_TOTEM);
        shapedRecipeBuilder.setShape("/a/", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a', Material.NETHER_STAR);
        shapedRecipeBuilder.assigneSymbol('c', Material.DIAMOND);
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(Material.NETHER_STAR);
        shapedRecipeBuilder.setShape("*a*", "aba", "*a*");
        shapedRecipeBuilder.assigneSymbol('a', Material.DIAMOND);
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.POSEIDON_TOTEM);
        shapedRecipeBuilder.setShape("a/a", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a', Material.HEART_OF_THE_SEA);
        shapedRecipeBuilder.assigneSymbol('c', Material.LAPIS_BLOCK);
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
        shapedRecipeBuilder.setShape("a/a", "/b/", "czc");
        shapedRecipeBuilder.assigneSymbol('a',Material.BONE);
        shapedRecipeBuilder.assigneSymbol('c', Material.OBSIDIAN);
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.assigneSymbol('z', Material.NETHERITE_SCRAP);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.ARES_TOTEM);
        shapedRecipeBuilder.setShape("a/c", "/b/", "d/e");
        shapedRecipeBuilder.assigneSymbol('a',Material.GOLDEN_SWORD);
        shapedRecipeBuilder.assigneSymbol('c', Material.GOLDEN_CHESTPLATE);
        shapedRecipeBuilder.assigneSymbol('d', Material.GOLDEN_AXE);
        shapedRecipeBuilder.assigneSymbol('e', Material.GOLDEN_BOOTS);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.APHRODITE_TOTEM);
        shapedRecipeBuilder.setShape("a/a", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a',Material.GOLDEN_APPLE);
        shapedRecipeBuilder.assigneSymbol('c', Material.GOLD_BLOCK);
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.DEMETER_TOTEM);
        shapedRecipeBuilder.setShape("a/a", "/b/", "czc");
        shapedRecipeBuilder.assigneSymbol('a',Material.GOLDEN_HOE);
        shapedRecipeBuilder.assigneSymbol('c', Material.WHEAT);
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.assigneSymbol('z', Material.CAKE);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.HERMES_TOTEM);
        shapedRecipeBuilder.setShape("aza", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a',Material.FEATHER);
        shapedRecipeBuilder.assigneSymbol('z',Material.GOLDEN_BOOTS);
        shapedRecipeBuilder.assigneSymbol('c', Material.EMERALD_BLOCK);
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.ARTEMIS_TOTEM);
        shapedRecipeBuilder.setShape("a/a", "/b/", "cde");
        shapedRecipeBuilder.assigneSymbol('a',Material.NETHER_STAR);
        shapedRecipeBuilder.assigneSymbol('e',Material.CROSSBOW);
        shapedRecipeBuilder.assigneSymbol('d',Material.ARROW);
        shapedRecipeBuilder.assigneSymbol('c', Material.BOW);
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.ATHENA_TOTEM);
        shapedRecipeBuilder.setShape("a/c", "/b/", "ded");
        shapedRecipeBuilder.assigneSymbol('a',Material.DIAMOND_HELMET);
        shapedRecipeBuilder.assigneSymbol('c',Material.ENCHANTING_TABLE);
        shapedRecipeBuilder.assigneSymbol('d', Material.BOOK);
        shapedRecipeBuilder.assigneSymbol('e', Material.SHIELD);
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.DIONYSUS_TOTEM);
        shapedRecipeBuilder.setShape("a/a", "/b/", "c/c");
        shapedRecipeBuilder.assigneSymbol('a',Material.SWEET_BERRIES);
        shapedRecipeBuilder.assigneSymbol('c', Material.CHORUS_FLOWER);
        shapedRecipeBuilder.assigneSymbol('b', CustomMaterial.DIVINE_HEART);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(Material.CHORUS_FLOWER);
        shapedRecipeBuilder.setShape("aba", "bcb", "aba");
        shapedRecipeBuilder.assigneSymbol('a',Material.LAPIS_LAZULI);
        shapedRecipeBuilder.assigneSymbol('b',Material.REDSTONE_BLOCK);
        shapedRecipeBuilder.assigneSymbol('c',Material.DIAMOND);
        shapedRecipeBuilder.register();

        shapedRecipeBuilder = new ShapedRecipeBuilder(CustomMaterial.DIVINE_TRACKER);
        shapedRecipeBuilder.setShape("aga", "/k/", "aga");
        shapedRecipeBuilder.assigneSymbol('a',Material.EMERALD);
        shapedRecipeBuilder.assigneSymbol('g',Material.GOLD_INGOT);
        shapedRecipeBuilder.assigneSymbol('k', Material.COMPASS);
        shapedRecipeBuilder.assigneSymbol('/', CustomMaterial.DIVINE_FRAGMENT);
        shapedRecipeBuilder.register();
    }
}
