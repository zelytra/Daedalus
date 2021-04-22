package fr.zelytra.daedalus.managers.loottable;

import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.structure.StructureEnum;
import org.bukkit.Material;

import java.util.HashMap;

public class LootTableManager {
    private HashMap<String, LootTable> structuresLoot;
    private HashMap<String, LootTable> athenaLoot;
    private HashMap<String, LootTable> dionysosPotion;

    public LootTableManager() {
        structuresLoot = new HashMap<>();
        athenaLoot = new HashMap<>();
        dionysosPotion = new HashMap<>();
        lootTableInit();
    }

    public LootTable getByName(String name) {
        if (this.structuresLoot.containsKey(name)) {
            return this.structuresLoot.get(name);
        } else if (this.athenaLoot.containsKey(name)) {
            return this.athenaLoot.get(name);
        }
        else if (this.dionysosPotion.containsKey(name)) {
            return this.dionysosPotion.get(name);
        }
        return null;
    }

    private void lootTableInit() {
        this.structuresLoot.put(StructureEnum.TEAM_RED.getName(), teamSpawn(StructureEnum.TEAM_RED.getName()));
        this.structuresLoot.put(StructureEnum.TEAM_GREEN.getName(), teamSpawn(StructureEnum.TEAM_GREEN.getName()));
        this.structuresLoot.put(StructureEnum.TEAM_BLUE.getName(), teamSpawn(StructureEnum.TEAM_BLUE.getName()));
        this.structuresLoot.put(StructureEnum.TEAM_YELLOW.getName(), teamSpawn(StructureEnum.TEAM_YELLOW.getName()));

        this.athenaLoot.put(GodsEnum.ATHENA.getName() + "_tier1", athenaTableT1());
        this.athenaLoot.put(GodsEnum.ATHENA.getName() + "_tier2", athenaTableT2());
        this.athenaLoot.put(GodsEnum.ATHENA.getName() + "_tier3", athenaTableT3());

        this.dionysosPotion.put(GodsEnum.DIONYSUS.getName() + "_tier1", dionysosTableT1());
        this.dionysosPotion.put(GodsEnum.DIONYSUS.getName() + "_tier2", dionysosTableT2());
    }

    private LootTable dionysosTableT2() {
        LootTable lootTable = new LootTable(GodsEnum.DIONYSUS.getName()+ "_tier2");
        //Container Init
        lootTable.addContainerWhiteList(Material.CHEST);
        lootTable.addContainerWhiteList(Material.BARREL);
        //Loots init
        lootTable.addLoot(LootsEnum.TIER2_INSTANTHEAL2);
        lootTable.addLoot(LootsEnum.TIER2_NIGHTVISION2);
        lootTable.addLoot(LootsEnum.TIER2_SPEED2);
        lootTable.addLoot(LootsEnum.TIER2_STRENGTH2);
        lootTable.addLoot(LootsEnum.TIER2_REGEN2);
        lootTable.addLoot(LootsEnum.TIER2_FIRERESSISTANCE2);
        lootTable.addLoot(LootsEnum.TIER2_JUMP2);
        lootTable.addLoot(LootsEnum.WATER);
        lootTable.addLoot(LootsEnum.TIER2_HAST2);
        lootTable.addLoot(LootsEnum.TIER2_INVISIBILITY1);
        return lootTable;
    }

    private LootTable dionysosTableT1() {
        LootTable lootTable = new LootTable(GodsEnum.DIONYSUS.getName()+ "_tier1");
        //Container Init
        lootTable.addContainerWhiteList(Material.CHEST);
        lootTable.addContainerWhiteList(Material.BARREL);
        //Loots init
        lootTable.addLoot(LootsEnum.TIER1_INSTANTHEAL1);
        lootTable.addLoot(LootsEnum.TIER1_NIGHTVISION1);
        lootTable.addLoot(LootsEnum.TIER1_SPEED1);
        lootTable.addLoot(LootsEnum.TIER1_STRENGTH1);
        lootTable.addLoot(LootsEnum.TIER1_REGEN1);
        lootTable.addLoot(LootsEnum.TIER1_FIRERESSISTANCE1);
        lootTable.addLoot(LootsEnum.TIER1_JUMP1);
        lootTable.addLoot(LootsEnum.WATER);
        lootTable.addLoot(LootsEnum.TIER1_HAST1);
        return lootTable;
    }

    private LootTable athenaTableT3() {
        LootTable lootTable = new LootTable(GodsEnum.ATHENA.getName()+ "_tier3");
        //Container Init
        lootTable.addContainerWhiteList(Material.CHEST);
        lootTable.addContainerWhiteList(Material.BARREL);
        //Loots init
        lootTable.addLoot(LootsEnum.TIER3_DEPTHSTRIDER3);
        lootTable.addLoot(LootsEnum.TIER3_EFFCIENCY3);
        lootTable.addLoot(LootsEnum.TIER3_FEATHERFALLING3);
        lootTable.addLoot(LootsEnum.TIER3_FIREPROTECTION3);
        lootTable.addLoot(LootsEnum.TIER3_FIREASPECT);
        lootTable.addLoot(LootsEnum.TIER3_FLAME);
        lootTable.addLoot(LootsEnum.TIER3_INFINITY);
        lootTable.addLoot(LootsEnum.TIER3_PUNCH2);
        lootTable.addLoot(LootsEnum.TIER3_THORNS2);
        lootTable.addLoot(LootsEnum.TIER3_SEALUCK3);
        lootTable.addLoot(LootsEnum.TIER3_LURE3);
        lootTable.addLoot(LootsEnum.TIER3_PIERCING3);
        lootTable.addLoot(LootsEnum.TIER3_POWER3);
        lootTable.addLoot(LootsEnum.TIER3_PROJECTILPROTECTION3);
        lootTable.addLoot(LootsEnum.TIER3_PROTECTION3);
        lootTable.addLoot(LootsEnum.TIER3_QUICKCHARGE3);
        lootTable.addLoot(LootsEnum.TIER3_SHARPNESS3);
        lootTable.addLoot(LootsEnum.TIER3_SWEEPING3);
        lootTable.addLoot(LootsEnum.TIER3_UNBREAKING3);
        return lootTable;
    }

    private LootTable athenaTableT2() {
        LootTable lootTable = new LootTable(GodsEnum.ATHENA.getName()+ "_tier2");
        //Container Init
        lootTable.addContainerWhiteList(Material.CHEST);
        lootTable.addContainerWhiteList(Material.BARREL);
        //Loots init
        lootTable.addLoot(LootsEnum.TIER2_DEPTHSTRIDER2);
        lootTable.addLoot(LootsEnum.TIER2_EFFCIENCY2);
        lootTable.addLoot(LootsEnum.TIER2_FEATHERFALLING2);
        lootTable.addLoot(LootsEnum.TIER2_FIREPROTECTION2);
        lootTable.addLoot(LootsEnum.TIER2_KNOCKBACK2);
        lootTable.addLoot(LootsEnum.TIER2_SEALUCK2);
        lootTable.addLoot(LootsEnum.TIER2_LURE2);
        lootTable.addLoot(LootsEnum.TIER2_PIERCING2);
        lootTable.addLoot(LootsEnum.TIER2_POWER2);
        lootTable.addLoot(LootsEnum.TIER2_PROJECTILPROTECTION2);
        lootTable.addLoot(LootsEnum.TIER2_PROTECTION2);
        lootTable.addLoot(LootsEnum.TIER2_QUICKCHARGE2);
        lootTable.addLoot(LootsEnum.TIER2_SHARPNESS2);
        lootTable.addLoot(LootsEnum.TIER2_SWEEPING2);
        lootTable.addLoot(LootsEnum.TIER2_UNBREAKING2);
        lootTable.addLoot(LootsEnum.TIER2_MULTISHOT);
        lootTable.addLoot(LootsEnum.TIER2_PUNCH1);
        lootTable.addLoot(LootsEnum.TIER2_THORNS1);
        return lootTable;
    }

    private LootTable athenaTableT1() {
        LootTable lootTable = new LootTable(GodsEnum.ATHENA.getName()+ "_tier1");
        //Container Init
        lootTable.addContainerWhiteList(Material.CHEST);
        lootTable.addContainerWhiteList(Material.BARREL);
        //Loots init
        lootTable.addLoot(LootsEnum.TIER1_DEPTHSTRIDER1);
        lootTable.addLoot(LootsEnum.TIER1_EFFCIENCY1);
        lootTable.addLoot(LootsEnum.TIER1_FEATHERFALLING1);
        lootTable.addLoot(LootsEnum.TIER1_FIREPROTECTION1);
        lootTable.addLoot(LootsEnum.TIER1_KNOCKBACK1);
        lootTable.addLoot(LootsEnum.TIER1_SEALUCK1);
        lootTable.addLoot(LootsEnum.TIER1_LURE1);
        lootTable.addLoot(LootsEnum.TIER1_PIERCING1);
        lootTable.addLoot(LootsEnum.TIER1_POWER1);
        lootTable.addLoot(LootsEnum.TIER1_PROJECTILPROTECTION1);
        lootTable.addLoot(LootsEnum.TIER1_PROTECTION1);
        lootTable.addLoot(LootsEnum.TIER1_QUICKCHARGE1);
        lootTable.addLoot(LootsEnum.TIER1_SHARPNESS1);
        lootTable.addLoot(LootsEnum.TIER1_SWEEPING1);
        lootTable.addLoot(LootsEnum.TIER1_UNBREAKING1);
        return lootTable;
    }

    private LootTable teamSpawn(String teamName) {
        LootTable lootTable = new LootTable(teamName);
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
