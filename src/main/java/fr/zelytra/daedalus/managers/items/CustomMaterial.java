package fr.zelytra.daedalus.managers.items;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

public enum CustomMaterial {
    ZEUS_LIGHTNING("§e§lZeus's Lightning", "zeus_lightning", 1, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    HADES_SCEPTER("§4§lHades's Scepter", "hades_scepter", 2, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    APHRODITE_HEART("§d§lAphrodite's Heart", "aphrodite_heart", 3, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    DEMETER_SICKLE("§2§lDemeter's Sickle", "demeter_sickle", 4, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    DIONYSOS_CUP("§5§lDionysos's Cup", "dionysos_cup", 5, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),

    MINOTAUR_HEAD("§5§lMinotaur's Head", "minotaur_head", 6, Material.CARVED_PUMPKIN, ItemType.ARMOR, 3, 10,EquipmentSlot.HEAD),
    DIVINE_FRAGMENT("§f§lDivine Fragment", "divine_fragment", 7, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    DIVINE_HEART("§c§lDivine Heart", "divine_heart", 8, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),

    ZEUS_TOTEM("§e§lZeus's Totem", "zeus_totem", 9, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    POSEIDON_TOTEM("§3§lPoseidon's Totem", "poseidon_totem", 10, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    HADES_TOTEM("§4§lHades's Totem", "hades_totem", 11, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    ARES_TOTEM("§c§lAres's Totem", "ares_totem", 12, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    APHRODITE_TOTEM("§d§lAphrodite's Totem", "aphrodite_totem", 13, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    DEMETER_TOTEM("§2§lDemeter's Totem", "demeter_totem", 14, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    HERMES_TOTEM("§b§lHermes's Totem", "hermes_totem", 15, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    ARTEMIS_TOTEM("§9§lArtemis's Totem", "artemis_totem", 16, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    ATHENA_TOTEM("§a§lAthenas's Totem", "athenas_totem", 17, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    DIONYSOS_TOTEM("§5§lDionysos's Totem", "dionysos_totem", 18, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    MINOTAUR_TOTEM("§6§lMinotaur's Totem", "minotaur_totem", 19, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),

    MINOTAUR_CHARGE("§6§lMinotaur's Charge", "minotaur_charge", 20, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    MEDUSA_HEAD("§7§lMedusa Head", "medusa_head", 21, Material.CARVED_PUMPKIN, ItemType.MISCELLANEOUS),
    HERMES_TRUC("§7§lHermesTruc", "hermes_truc", 22, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS);


    private final String displayName;
    private final String name;
    private final int customModelData;
    private final Material vanillaMaterial;
    private final ItemType itemType;

    private EquipmentSlot slot;
    private int armor;
    private int damage;
    private int extraHeart;

    CustomMaterial(String displayName, String name, int CMD, Material material, ItemType itemType) {
        this.displayName = displayName;
        this.name = name;
        this.customModelData = CMD;
        this.vanillaMaterial = material;
        this.itemType = itemType;
    }

    CustomMaterial(String displayName, String name, String description, int CMD, Material material, ItemType itemType) {
        this.displayName = displayName;
        this.name = name;
        this.customModelData = CMD;
        this.vanillaMaterial = material;
        this.itemType = itemType;
    }

    CustomMaterial(String displayName, String name, int CMD, Material material, ItemType itemType, int armor, int extraHeart,EquipmentSlot slot) {
        this.displayName = displayName;
        this.name = name;
        this.customModelData = CMD;
        this.vanillaMaterial = material;
        this.itemType = itemType;
        this.armor = armor;
        this.extraHeart = extraHeart;
        this.slot = slot;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public Material getVanillaMaterial() {
        return vanillaMaterial;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }

    public int getExtraHeart() {
        return extraHeart;
    }

    public EquipmentSlot getSlot() {
        return slot;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public static CustomMaterial getByName(String name) {
        for (CustomMaterial material : CustomMaterial.values()) {
            if (material.getName().equalsIgnoreCase(name)) {
                return material;
            }
        }
        return null;
    }

}
