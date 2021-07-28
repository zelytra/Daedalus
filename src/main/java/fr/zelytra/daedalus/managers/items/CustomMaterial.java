package fr.zelytra.daedalus.managers.items;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

public enum CustomMaterial {
    ZEUS_LIGHTNING(GameSettings.LANG.textOf("godItem.ZEUS_LIGHTNING"), "zeus_lightning", 1, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    HADES_SCEPTER(GameSettings.LANG.textOf("godItem.HADES_SCEPTER"), "hades_scepter", 2, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    APHRODITE_HEART(GameSettings.LANG.textOf("godItem.APHRODITE_HEART"), "aphrodite_heart", 3, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    DEMETER_SICKLE(GameSettings.LANG.textOf("godItem.DEMETER_SICKLE"), "demeter_sickle", 4, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),

    DIONYSOS_THYRSUS (GameSettings.LANG.textOf("godItem.DIONYSOS_THYRSUS"), "dionysos_thyrsus", 5, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    DIONYSOS_MUG(GameSettings.LANG.textOf("godItem.DIONYSOS_MUG"), "dionysos_mug", 25, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    HERMES_CADUCEUS(GameSettings.LANG.textOf("godItem.HERMES_CADUCEUS"), "hermes_caduceus", 22, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    ARES_WAR_BANNER(GameSettings.LANG.textOf("godItem.ARES_WAR_BANNER"), "ares_war_banner", 26, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),

    MINOTAUR_HEAD(GameSettings.LANG.textOf("godItem.MINOTAUR_HEAD"), "minotaur_head", 6, Material.CARVED_PUMPKIN, ItemType.ARMOR, 3, 0,EquipmentSlot.HEAD),
    DIVINE_FRAGMENT(GameSettings.LANG.textOf("godItem.DIVINE_FRAGMENT"), "divine_fragment", 7, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    DIVINE_HEART(GameSettings.LANG.textOf("godItem.DIVINE_HEART"), "divine_heart", 8, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),

    ZEUS_TOTEM(GameSettings.LANG.textOf("godItem.ZEUS_TOTEM"), "zeus_totem", 9, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    POSEIDON_TOTEM(GameSettings.LANG.textOf("godItem.POSEIDON_TOTEM"), "poseidon_totem", 10, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    HADES_TOTEM(GameSettings.LANG.textOf("godItem.HADES_TOTEM"), "hades_totem", 11, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    ARES_TOTEM(GameSettings.LANG.textOf("godItem.ARES_TOTEM"), "ares_totem", 12, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    APHRODITE_TOTEM(GameSettings.LANG.textOf("godItem.APHRODITE_TOTEM"), "aphrodite_totem", 13, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    DEMETER_TOTEM(GameSettings.LANG.textOf("godItem.DEMETER_TOTEM"), "demeter_totem", 14, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    HERMES_TOTEM(GameSettings.LANG.textOf("godItem.HERMES_TOTEM"), "hermes_totem", 15, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    ARTEMIS_TOTEM(GameSettings.LANG.textOf("godItem.ARTEMIS_TOTEM"), "artemis_totem", 16, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    ATHENA_TOTEM(GameSettings.LANG.textOf("godItem.ATHENA_TOTEM"), "athenas_totem", 17, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    DIONYSOS_TOTEM(GameSettings.LANG.textOf("godItem.DIONYSOS_TOTEM"), "dionysos_totem", 18, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),
    MINOTAUR_TOTEM(GameSettings.LANG.textOf("godItem.MINOTAUR_TOTEM"), "minotaur_totem", 19, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS),

    MINOTAUR_CHARGE(GameSettings.LANG.textOf("godItem.MINOTAUR_CHARGE"), "minotaur_charge", 20, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),
    MEDUSA_HEAD(GameSettings.LANG.textOf("godItem.MEDUSA_HEAD"), "medusa_head", 21, Material.CARVED_PUMPKIN, ItemType.MISCELLANEOUS),
    DIVINE_TRACKER(GameSettings.LANG.textOf("godItem.DIVINE_TRACKER"), "divine_tracker", 23, Material.COMPASS, ItemType.MISCELLANEOUS),

    ATHENA_MAP(GameSettings.LANG.textOf("godItem.ATHENA_MAP"), "athena_map", 24, Material.FILLED_MAP, ItemType.MISCELLANEOUS);


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
