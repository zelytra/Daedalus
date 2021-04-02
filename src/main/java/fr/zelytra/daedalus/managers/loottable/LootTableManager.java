package fr.zelytra.daedalus.managers.loottable;

import fr.zelytra.daedalus.managers.structure.StructureEnum;
import org.bukkit.Material;

import java.util.HashMap;

public class LootTableManager {
    private HashMap<String, LootTable> structuresLoot;

    public LootTableManager() {
        structuresLoot = new HashMap<>();
        lootTableInit();
    }
    public LootTable getByName(String name){
        if(this.structuresLoot.containsKey(name)){
            return this.structuresLoot.get(name);
        }
        return null;
    }

    private void lootTableInit() {
        this.structuresLoot.put(StructureEnum.TEAM_RED.getName(),teamSpawn());
    }

    private LootTable teamSpawn() {
        LootTable lootTable = new LootTable(StructureEnum.TEAM_RED.getName());
        //Container Init
        lootTable.addContainerWhiteList(Material.CHEST);
        lootTable.addContainerWhiteList(Material.BARREL);
        //Loots init
        lootTable.addLoot(LootsEnum.COOKED_PORKCHOP);
        lootTable.addLoot(LootsEnum.COOKED_BEEF);
        lootTable.addLoot(LootsEnum.COOKED_CHICKEN);
        lootTable.addLoot(LootsEnum.COOKED_FISH);
        lootTable.addLoot(LootsEnum.COOKED_MUTTON);
        lootTable.addLoot(LootsEnum.COOKED_RABBIT);
        lootTable.addLoot(LootsEnum.COOKED_SALMON);
        lootTable.addLoot(LootsEnum.OAK_PLANKS);
        lootTable.addLoot(LootsEnum.STICK);
        lootTable.addLoot(LootsEnum.STRING);
        lootTable.addLoot(LootsEnum.LEATHER);
        lootTable.addLoot(LootsEnum.PAPER);
        lootTable.addLoot(LootsEnum.WHEAT);
        lootTable.addLoot(LootsEnum.FEATHER);
        lootTable.addLoot(LootsEnum.FLINT);
        lootTable.addLoot(LootsEnum.ARROW);
        lootTable.addLoot(LootsEnum.GLASS_BOTTLE);
        lootTable.addLoot(LootsEnum.APPLE);
        lootTable.addLoot(LootsEnum.IRON_NUGGET);
        lootTable.addLoot(LootsEnum.COAL);
        lootTable.addLoot(LootsEnum.BOOK);
        lootTable.addLoot(LootsEnum.PUMPKIN_SEEDS);
        lootTable.addLoot(LootsEnum.MELON_SEEDS);
        lootTable.addLoot(LootsEnum.COCOA_BEANS);
        lootTable.addLoot(LootsEnum.IRON_INGOT);
        lootTable.addLoot(LootsEnum.GOLD_NUGGET);
        lootTable.addLoot(LootsEnum.BUCKET);
        lootTable.addLoot(LootsEnum.EGG);
        lootTable.addLoot(LootsEnum.BOW);
        lootTable.addLoot(LootsEnum.CROSSBOW);
        lootTable.addLoot(LootsEnum.SHIELD);
        lootTable.addLoot(LootsEnum.IRON_PICKAXE);
        lootTable.addLoot(LootsEnum.IRON_AXE);
        lootTable.addLoot(LootsEnum.GOLD_INGOT);
        lootTable.addLoot(LootsEnum.IRON_SWORD);
        lootTable.addLoot(LootsEnum.EXPERIENCE_BOTTLE);
        lootTable.addLoot(LootsEnum.CHAINMAIL_HELMET);
        lootTable.addLoot(LootsEnum.CHAINMAIL_CHESTPLATE);
        lootTable.addLoot(LootsEnum.CHAINMAIL_LEGGINGS);
        lootTable.addLoot(LootsEnum.CHAINMAIL_BOOTS);
        lootTable.addLoot(LootsEnum.IRON_HELMET);
        lootTable.addLoot(LootsEnum.IRON_CHESTPLATE);
        lootTable.addLoot(LootsEnum.IRON_LEGGINGS);
        lootTable.addLoot(LootsEnum.IRON_BOOTS);
        lootTable.addLoot(LootsEnum.GOLDEN_APPLE);
        return lootTable;
    }
}
