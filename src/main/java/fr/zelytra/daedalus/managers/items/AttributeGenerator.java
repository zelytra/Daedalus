package fr.zelytra.daedalus.managers.items;

import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;

import java.util.UUID;

public abstract class AttributeGenerator {

    public static AttributeModifier attack(int value, EquipmentSlot slot) {
        return new AttributeModifier(UUID.randomUUID(), "attackDamage", value, AttributeModifier.Operation.ADD_NUMBER,slot);
    }

    public static AttributeModifier extraHeart(int value, EquipmentSlot slot) {
        return new AttributeModifier(UUID.randomUUID(), "extra-heart", value, AttributeModifier.Operation.ADD_NUMBER,slot);
    }

    public static AttributeModifier armor(int value, EquipmentSlot slot) {
        return new AttributeModifier(UUID.randomUUID(), "armor", value, AttributeModifier.Operation.ADD_NUMBER,slot);
    }
}
