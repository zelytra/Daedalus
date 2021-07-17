package fr.zelytra.daedalus.commands.wiki;


import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.guiBuilder.CustomGUI;
import fr.zelytra.daedalus.builders.guiBuilder.InterfaceBuilder;
import fr.zelytra.daedalus.builders.guiBuilder.VisualItemStack;
import fr.zelytra.daedalus.builders.guiBuilder.VisualType;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.items.ItemType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Wiki implements CommandExecutor, Listener {
    private final String wikiName = "§9[§bWIKI§9]§b";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        InterfaceBuilder interfaceBuilder = new InterfaceBuilder(54, wikiName);
        interfaceBuilder.setContent(contentBuilder(0));
        interfaceBuilder.open(player);
        return false;
    }


    private ItemStack[] contentBuilder(int page) {
        if (page < 0) {
            page = 0;
        }
        ItemStack[] content = new ItemStack[54];
        for (int x = 0; x < 54; x++) {
            content[x] = VisualType.BLANK_CYAN_GLASSE.getItem();
        }
        content[0] = VisualType.BLANK_GRAY_GLASSE.getItem();
        content[9] = VisualType.BLANK_BLACK_GLASSE.getItem();
        content[18] = VisualType.BLANK_BLACK_GLASSE.getItem();
        content[27] = VisualType.BLANK_BLACK_GLASSE.getItem();
        content[36] = VisualType.BLANK_BLACK_GLASSE.getItem();
        content[45] = VisualType.BLANK_GRAY_GLASSE.getItem();
        content[8] = VisualType.BLANK_GRAY_GLASSE.getItem();
        content[17] = VisualType.BLANK_BLACK_GLASSE.getItem();
        content[26] = VisualType.BLANK_BLACK_GLASSE.getItem();
        content[35] = VisualType.BLANK_BLACK_GLASSE.getItem();
        content[44] = VisualType.BLANK_BLACK_GLASSE.getItem();
        content[53] = VisualType.BLANK_GRAY_GLASSE.getItem();

        content[48] = VisualType.BLANK_BLACK_GLASSE.getItem();
        content[50] = VisualType.BLANK_BLACK_GLASSE.getItem();

        int count = page * 28;
        content[49] = new VisualItemStack(Material.SPRUCE_SIGN, "§dPage " + page, false).getItem();
        for (int a = 0; a < 4; a++) {
            for (int id = 10; id <= 16; id++) {
                content[(id + a * 9)] = new ItemStack(Material.AIR);
                if (count >= CustomMaterial.values().length) {
                    continue;
                }
                if (CustomMaterial.values()[count].getItemType() == ItemType.SPECIAL) {
                    continue;
                }
                content[(id + a * 9)] = new CustomItemStack(CustomMaterial.values()[count], 1).getItem();
                count++;
            }
        }

        return content;
    }

    private ItemStack[] contentCraftBuilder(ItemStack item) {
        ItemStack[] content = new ItemStack[36];
        ShapedRecipe shapedRecipe;
        Recipe recipes = null;
        if (CustomItemStack.getCustomMaterial(item) == null) {
            if (Bukkit.getServer().getRecipesFor(item) != null && !Bukkit.getServer().getRecipesFor(item).isEmpty()) {
                recipes = Bukkit.getServer().getRecipesFor(item).get(0);
            }
        } else {
            recipes = Bukkit.getServer().getRecipe(new NamespacedKey(Daedalus.getInstance(), CustomItemStack.getCustomMaterial(item).getName() + "0"));
        }
        for (int x = 0; x < 36; x++) {
            content[x] = VisualType.BLANK_BLACK_GLASSE.getItem();
        }
        for (int x = 27; x < 36; x++) {
            content[x] = VisualType.BLANK_CYAN_GLASSE.getItem();
        }
        content[31] = VisualType.RETURN.getItem();
        if (recipes != null && recipes instanceof ShapedRecipe) {
            shapedRecipe = (ShapedRecipe) recipes;
            content[2] = new ItemStack(Material.AIR);
            content[3] = new ItemStack(Material.AIR);
            content[4] = new ItemStack(Material.AIR);
            content[11] = new ItemStack(Material.AIR);
            content[12] = new ItemStack(Material.AIR);
            content[13] = new ItemStack(Material.AIR);
            content[20] = new ItemStack(Material.AIR);
            content[21] = new ItemStack(Material.AIR);
            content[22] = new ItemStack(Material.AIR);
            content[15] = item;

            int count = 2;
            for (Map.Entry<Character, ItemStack> entry : shapedRecipe.getIngredientMap().entrySet()) {
                if (count == 5)
                    count = 11;
                if (count == 14)
                    count = 20;
                content[count] = entry.getValue();
                count++;
            }

        } else {
            content[4] = new ItemStack(Material.AIR);
            content[12] = new ItemStack(Material.AIR);
            content[14] = new ItemStack(Material.AIR);
            content[22] = new ItemStack(Material.AIR);

            content[5] = VisualType.BLANK_GRAY_GLASSE.getItem();
            content[6] = VisualType.BLANK_GRAY_GLASSE.getItem();
            content[15] = VisualType.BLANK_GRAY_GLASSE.getItem();
            content[16] = VisualType.BLANK_GRAY_GLASSE.getItem();
            content[23] = VisualType.BLANK_GRAY_GLASSE.getItem();
            content[24] = VisualType.BLANK_GRAY_GLASSE.getItem();
            content[2] = VisualType.BLANK_GRAY_GLASSE.getItem();
            content[3] = VisualType.BLANK_GRAY_GLASSE.getItem();
            content[10] = VisualType.BLANK_GRAY_GLASSE.getItem();
            content[11] = VisualType.BLANK_GRAY_GLASSE.getItem();
            content[20] = VisualType.BLANK_GRAY_GLASSE.getItem();
            content[21] = VisualType.BLANK_GRAY_GLASSE.getItem();
            content[13] = item;

        }


        return content;
    }

    @EventHandler
    public void InterfaceInteract(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof CustomGUI && e.getView().getTitle().equals(wikiName)) {
            if (e.getCurrentItem() != null) {
                if (CustomItemStack.getCustomMaterial(e.getCurrentItem()) != null) {
                    switch (CustomItemStack.getCustomMaterial(e.getCurrentItem())) {
                        case NEXT_ARROW:
                            InterfaceBuilder interfaceBuilder = new InterfaceBuilder(54, wikiName);
                            ItemStack[] content = contentBuilder(getPage(e.getInventory().getContents()) + 1);
                            boolean isEmpty = true;
                            for (int a = 0; a < 4; a++) {
                                for (int id = 10; id <= 16; id++) {
                                    if (content[(id + a * 9)].getType() != Material.AIR) {
                                        isEmpty = false;
                                        break;
                                    }
                                }

                            }
                            if (!isEmpty) {
                                interfaceBuilder.setContent(content);
                            } else {
                                break;
                            }

                            interfaceBuilder.open((Player) e.getWhoClicked());
                            break;
                        case PREVIOUS_ARROW:
                            interfaceBuilder = new InterfaceBuilder(54, wikiName);
                            interfaceBuilder.setContent(contentBuilder(getPage(e.getInventory().getContents()) - 1));
                            interfaceBuilder.open((Player) e.getWhoClicked());
                            break;
                        default:
                            interfaceBuilder = new InterfaceBuilder(36, wikiName);
                            interfaceBuilder.setContent(contentCraftBuilder(e.getCurrentItem()));
                            interfaceBuilder.open((Player) e.getWhoClicked());
                            break;
                    }
                } else if (e.getCurrentItem().getType() == Material.BARRIER) {
                    InterfaceBuilder interfaceBuilder = new InterfaceBuilder(54, wikiName);
                    interfaceBuilder.setContent(contentBuilder(0));
                    interfaceBuilder.open((Player) e.getWhoClicked());

                } else if (e.getCurrentItem().getType() == Material.HEART_OF_THE_SEA || e.getCurrentItem().getType() == Material.NETHER_STAR || e.getCurrentItem().getType() == Material.CHORUS_FLOWER) {
                    InterfaceBuilder interfaceBuilder = new InterfaceBuilder(36, wikiName);
                    interfaceBuilder.setContent(contentCraftBuilder(e.getCurrentItem()));
                    interfaceBuilder.open((Player) e.getWhoClicked());

                }
                e.setCancelled(true);
            }
        }
    }

    private int getPage(ItemStack[] content) {
        return Integer.parseInt(content[49].getItemMeta().getDisplayName().split(" ")[1]);
    }

}
