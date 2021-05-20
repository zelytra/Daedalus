package fr.zelytra.daedalus.managers.loottable;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

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
                if(material==null){
                    System.out.println("LootParser problem with : "+yml.getString(tableName + ".loots." + loot + ".material").toUpperCase());
                    continue;
                }
                int amount = yml.getInt(tableName + ".loots." + loot + ".amount");
                double luck = yml.getDouble(tableName + ".loots." + loot + ".luck");
                table.addLoot(new Loot(new ItemStack(material, amount), luck));
            }
            lootTables.add(table);

        }
    }

    public List<LootTable> getLootTables() {
        return lootTables;
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
