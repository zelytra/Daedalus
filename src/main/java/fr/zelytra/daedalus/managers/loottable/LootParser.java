package fr.zelytra.daedalus.managers.loottable;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LootParser {
    private FileConfiguration yml;
    private List<LootTable> lootTables = new ArrayList<>();

    public LootParser() {
        InputStream is = Daedalus.getInstance().getResource("lootTables" + ".yml");
        Reader reader = new InputStreamReader(is);
        yml = YamlConfiguration.loadConfiguration(reader);
        read();
    }

    private void read() {
        for (String tableName : yml.getKeys(false)) {
            LootTable table = new LootTable(tableName);

            for (String container : yml.getStringList(tableName + ".containers")) {
                table.addContainerWhiteList(Material.getMaterial(container.toUpperCase()));
            }

            for (String loot : yml.getConfigurationSection(tableName + ".loots").getKeys(false)) {
                Material material = Material.getMaterial(yml.getString(tableName + ".loots." + loot + ".material").toUpperCase());
                if (material == null) {
                    System.out.println("LootParser problem with : " + yml.getString(tableName + ".loots." + loot + ".material").toUpperCase());
                    continue;
                }
                int amount = yml.getInt(tableName + ".loots." + loot + ".amount");
                ItemStack item = new ItemStack(material, amount);

                /* Enchant reader */
                if (yml.getString(tableName + ".loots." + loot + ".enchant.type") != null) {
                    if (Enchantment.getByName(yml.getString(tableName + ".loots." + loot + ".enchant.type").toUpperCase()) == null) {
                        System.out.println("LootParser problem with : " + yml.getString(tableName + ".loots." + loot + ".enchant.type").toUpperCase());
                        continue;
                    }
                    if (material != Material.ENCHANTED_BOOK) {
                        ItemMeta meta = item.getItemMeta();
                        meta.addEnchant(Enchantment.getByName(yml.getString(tableName + ".loots." + loot + ".enchant.type").toUpperCase()), yml.getInt(yml.getString(tableName + ".loots." + loot + ".enchant.level")), true);
                        item.setItemMeta(meta);
                    } else {
                        item = Utils.BookEnchantedItemStack(material, Enchantment.getByName(yml.getString(tableName + ".loots." + loot + ".enchant.type").toUpperCase()), yml.getInt(tableName + ".loots." + loot + ".enchant.level"));
                    }
                }

                /* Potion reader */
                if (yml.getString(tableName + ".loots." + loot + ".effect.type") != null) {
                    if (PotionEffectType.getByName(yml.getString(tableName + ".loots." + loot + ".effect.type").toUpperCase()) == null) {
                        System.out.println("LootParser problem with : " + yml.getString(tableName + ".loots." + loot + ".effect.type").toUpperCase());
                        continue;
                    }
                    if (material == Material.POTION || material == Material.SPLASH_POTION) {
                        PotionMeta potionMeta = (PotionMeta) item.getItemMeta();

                        PotionEffectType potionEffectType = PotionEffectType.getByName(yml.getString(tableName + ".loots." + loot + ".effect.type").toUpperCase());
                        int duration = yml.getInt(tableName + ".loots." + loot + ".effect.duration") * 20;
                        int amplifier = yml.getInt(tableName + ".loots." + loot + ".effect.amplifier");

                        potionMeta.setColor(Color.fromRGB(ThreadLocalRandom.current().nextInt(0, 255 + 1), ThreadLocalRandom.current().nextInt(0, 255 + 1), ThreadLocalRandom.current().nextInt(0, 255 + 1)));
                        potionMeta.addCustomEffect(new PotionEffect(potionEffectType, duration, amplifier), true);
                        item.setItemMeta(potionMeta);
                        ItemMeta meta = item.getItemMeta();
                        meta.displayName(Component.text().content("Potion of " + potionEffectType.getName().toLowerCase()).build());
                        item.setItemMeta(meta);

                    } else {
                        System.out.println("LootParser problem : Cannot add potion effect on " + yml.getString(tableName + ".loots." + loot) + " item type");
                    }

                }

                double luck = yml.getDouble(tableName + ".loots." + loot + ".luck");
                table.addLoot(new Loot(item, luck));
            }
            lootTables.add(table);

        }
    }

    public LootTable getByName(String name) {
        for (LootTable table : lootTables) {
            if (table.getName().equalsIgnoreCase(name)) {
                return table;
            }
        }
        return null;
    }
}
