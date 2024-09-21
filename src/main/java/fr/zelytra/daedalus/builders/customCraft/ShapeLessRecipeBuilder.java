package fr.zelytra.daedalus.builders.customCraft;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;

public class ShapeLessRecipeBuilder {

  private final ShapelessRecipe shapelessRecipe;
  private final NamespacedKey itemKey;
  private final CustomMaterial material;
  private static int count = 0;

  public ShapeLessRecipeBuilder(CustomMaterial material) {
    this.material = material;
    this.itemKey = new NamespacedKey(Daedalus.getInstance(), "daedalus" + count);
    count++;
    this.shapelessRecipe =
        new ShapelessRecipe(this.itemKey, new CustomItemStack(material, 1).getItem());
  }

  public ShapeLessRecipeBuilder(CustomMaterial material, int amount) {
    this.material = material;
    this.itemKey = new NamespacedKey(Daedalus.getInstance(), "daedalus" + count);
    count++;
    this.shapelessRecipe =
        new ShapelessRecipe(this.itemKey, new CustomItemStack(material, amount).getItem());
  }

  public void setIngredient(Material material) {
    this.shapelessRecipe.addIngredient(material);
  }

  public void setIngredient(CustomMaterial material) {
    RecipeChoice.ExactChoice item =
        new RecipeChoice.ExactChoice(new CustomItemStack(material, 1).getItem());
    this.shapelessRecipe.addIngredient(item);
  }

  public void register() {
    Daedalus.getInstance().getServer().addRecipe(this.shapelessRecipe);
  }
}
