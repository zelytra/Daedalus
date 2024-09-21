package fr.zelytra.daedalus.managers.items;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.languages.Lang;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

public enum CustomMaterial {
	ZEUS_LIGHTNING("godItem.ZEUS_LIGHTNING", "zeus_lightning", 1, Material.PHANTOM_MEMBRANE,
			ItemType.MISCELLANEOUS), HADES_SCEPTER("godItem.HADES_SCEPTER", "hades_scepter", 2,
					Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS), APHRODITE_HEART("godItem.APHRODITE_HEART",
							"aphrodite_heart", 3, Material.PHANTOM_MEMBRANE,
							ItemType.MISCELLANEOUS), DEMETER_SICKLE("godItem.DEMETER_SICKLE", "demeter_sickle", 4,
									Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),

	DIONYSUS_THYRSUS("godItem.DIONYSUS_THYRSUS", "dionysus_thyrsus", 5, Material.PHANTOM_MEMBRANE,
			ItemType.MISCELLANEOUS), DIONYSUS_CUP("godItem.DIONYSUS_CUP", "dionysus_cup", 25, Material.PHANTOM_MEMBRANE,
					ItemType.MISCELLANEOUS), HERMES_CADUCEUS("godItem.HERMES_CADUCEUS", "hermes_caduceus", 22,
							Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS), ARES_WAR_BANNER(
									"godItem.ARES_WAR_BANNER", "ares_war_banner", 26, Material.PHANTOM_MEMBRANE,
									ItemType.MISCELLANEOUS), ARTEMIS_HORN("godItem.ARTEMIS_HORN", "artemis_horn", 27,
											Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS), POSEIDON_CONCH(
													"godItem.POSEIDON_CONCH", "poseidon_conche", 28,
													Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),

	MINOTAUR_HEAD("godItem.MINOTAUR_HEAD", "minotaur_head", 6, Material.CARVED_PUMPKIN, ItemType.ARMOR, 3, 0,
			EquipmentSlot.HEAD), DIVINE_FRAGMENT("godItem.DIVINE_FRAGMENT", "divine_fragment", 7,
					Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS), DIVINE_HEART("godItem.DIVINE_HEART",
							"divine_heart", 8, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS),

	ZEUS_TOTEM("godItem.ZEUS_TOTEM", "zeus_totem", 9, Material.TOTEM_OF_UNDYING,
			ItemType.MISCELLANEOUS), POSEIDON_TOTEM("godItem.POSEIDON_TOTEM", "poseidon_totem", 10,
					Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS), HADES_TOTEM("godItem.HADES_TOTEM",
							"hades_totem", 11, Material.TOTEM_OF_UNDYING, ItemType.MISCELLANEOUS), ARES_TOTEM(
									"godItem.ARES_TOTEM", "ares_totem", 12, Material.TOTEM_OF_UNDYING,
									ItemType.MISCELLANEOUS), APHRODITE_TOTEM("godItem.APHRODITE_TOTEM",
											"aphrodite_totem", 13, Material.TOTEM_OF_UNDYING,
											ItemType.MISCELLANEOUS), DEMETER_TOTEM("godItem.DEMETER_TOTEM",
													"demeter_totem", 14, Material.TOTEM_OF_UNDYING,
													ItemType.MISCELLANEOUS), HERMES_TOTEM("godItem.HERMES_TOTEM",
															"hermes_totem", 15, Material.TOTEM_OF_UNDYING,
															ItemType.MISCELLANEOUS), ARTEMIS_TOTEM(
																	"godItem.ARTEMIS_TOTEM", "artemis_totem", 16,
																	Material.TOTEM_OF_UNDYING,
																	ItemType.MISCELLANEOUS), ATHENA_TOTEM(
																			"godItem.ATHENA_TOTEM", "athenas_totem", 17,
																			Material.TOTEM_OF_UNDYING,
																			ItemType.MISCELLANEOUS), DIONYSUS_TOTEM(
																					"godItem.DIONYSUS_TOTEM",
																					"dionysus_totem", 18,
																					Material.TOTEM_OF_UNDYING,
																					ItemType.MISCELLANEOUS), MINOTAUR_TOTEM(
																							"godItem.MINOTAUR_TOTEM",
																							"minotaur_totem", 19,
																							Material.TOTEM_OF_UNDYING,
																							ItemType.MISCELLANEOUS),

	MINOTAUR_CHARGE("godItem.MINOTAUR_CHARGE", "minotaur_charge", 20, Material.PHANTOM_MEMBRANE,
			ItemType.MISCELLANEOUS), DIVINE_TRACKER("godItem.DIVINE_TRACKER", "divine_tracker", 23, Material.COMPASS,
					ItemType.MISCELLANEOUS),

	ATHENA_MAP("godItem.ATHENA_MAP", "athena_map", 21, Material.FILLED_MAP, ItemType.MISCELLANEOUS), WALL_BREAKER(
			"godItem.WALL_BREAKER", "wall_breaker", 29, Material.PHANTOM_MEMBRANE, ItemType.MISCELLANEOUS);

	private final String displayName;

	@Getter
	private final String name;

	@Getter
	private final int customModelData;

	@Getter
	private final Material vanillaMaterial;

	@Getter
	private final ItemType itemType;

	@Getter
	private EquipmentSlot slot;

	@Getter
	private int armor;

	@Getter
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

	CustomMaterial(String displayName, String name, int CMD, Material material, ItemType itemType, int armor,
			int extraHeart, EquipmentSlot slot) {
		this.displayName = displayName;
		this.name = name;
		this.customModelData = CMD;
		this.vanillaMaterial = material;
		this.itemType = itemType;
		this.armor = armor;
		this.extraHeart = extraHeart;
		this.slot = slot;
	}

	public String getDisplayName() {
		return GameSettings.LANG.textOf(displayName);
	}

	public String getDisplayName(Lang lang) {
		return lang.textOf(displayName);
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
