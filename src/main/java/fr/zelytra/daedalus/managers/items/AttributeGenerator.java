package fr.zelytra.daedalus.managers.items;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;

public abstract class AttributeGenerator {

	public static AttributeModifier attack(double value, EquipmentSlotGroup slot) {
		NamespacedKey namespacedKey = new NamespacedKey(Daedalus.getInstance(), "attackDamage");
		return new AttributeModifier(namespacedKey, value, AttributeModifier.Operation.ADD_NUMBER, slot);
	}

	public static AttributeModifier extraHeart(double value, EquipmentSlotGroup slot) {
		NamespacedKey namespacedKey = new NamespacedKey(Daedalus.getInstance(), "extra-heart");
		return new AttributeModifier(namespacedKey, value, AttributeModifier.Operation.ADD_NUMBER, slot);
	}

	public static AttributeModifier armor(double value, EquipmentSlotGroup slot) {
		NamespacedKey namespacedKey = new NamespacedKey(Daedalus.getInstance(), "armor");
		return new AttributeModifier(namespacedKey, value, AttributeModifier.Operation.ADD_NUMBER, slot);
	}
}
