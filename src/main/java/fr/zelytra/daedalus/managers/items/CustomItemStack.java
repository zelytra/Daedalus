package fr.zelytra.daedalus.managers.items;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;


public class CustomItemStack {
    private final static NamespacedKey itemKey = new NamespacedKey(Daedalus.getInstance(), "daedalus");
    private final static NamespacedKey descriptionKey = new NamespacedKey(Daedalus.getInstance(), "description");

    private final ItemStack item;
    private final CustomMaterial customMaterial;

    /**
     * @param material Custom material of the item
     * @param amount   Item amount
     */
    public CustomItemStack(CustomMaterial material, int amount) {

        this.customMaterial = material;
        switch (material.getItemType()) {
            case ARMOR:
                this.item = new ItemStack(this.customMaterial.getVanillaMaterial(), amount);
                ItemMeta meta = this.item.getItemMeta();
                assert meta != null;
                meta.setCustomModelData(this.customMaterial.getCustomModelData());
                meta.setDisplayName(this.customMaterial.getDisplayName());

                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, AttributeGenerator.armor(material.getArmor(), material.getSlot()));
                meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, AttributeGenerator.extraHeart(material.getExtraHeart(), material.getSlot()));

                PersistentDataContainer itemData = meta.getPersistentDataContainer();
                itemData.set(itemKey, PersistentDataType.STRING, this.customMaterial.getName());
                //itemData.set(descriptionKey, PersistentDataType.STRING, );
                //meta.setLore(lore);
                this.item.setItemMeta(meta);
                break;

            default:
                this.item = new ItemStack(this.customMaterial.getVanillaMaterial(), amount);
                meta = this.item.getItemMeta();
                assert meta != null;
                meta.setCustomModelData(this.customMaterial.getCustomModelData());
                meta.setDisplayName(this.customMaterial.getDisplayName());
                itemData = meta.getPersistentDataContainer();
                itemData.set(itemKey, PersistentDataType.STRING, this.customMaterial.getName());
                //itemData.set(descriptionKey, PersistentDataType.STRING, );
                //meta.setLore(lore);
                this.item.setItemMeta(meta);
                break;

        }

    }

    /**
     * @param material Custom material of the item
     */

    public CustomItemStack(CustomMaterial material) {

        this.customMaterial = material;
        switch (material.getItemType()) {
            case ARMOR:
                this.item = new ItemStack(this.customMaterial.getVanillaMaterial());
                ItemMeta meta = this.item.getItemMeta();
                assert meta != null;
                meta.setCustomModelData(this.customMaterial.getCustomModelData());
                meta.setDisplayName(this.customMaterial.getDisplayName());

                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, AttributeGenerator.armor(material.getArmor(), material.getSlot()));
                meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, AttributeGenerator.extraHeart(material.getExtraHeart(), material.getSlot()));

                PersistentDataContainer itemData = meta.getPersistentDataContainer();
                itemData.set(itemKey, PersistentDataType.STRING, this.customMaterial.getName());
                //itemData.set(descriptionKey, PersistentDataType.STRING, );
                //meta.setLore(lore);
                this.item.setItemMeta(meta);
                break;

            default:
                this.item = new ItemStack(this.customMaterial.getVanillaMaterial());
                meta = this.item.getItemMeta();
                assert meta != null;
                meta.setCustomModelData(this.customMaterial.getCustomModelData());
                meta.setDisplayName(this.customMaterial.getDisplayName());
                itemData = meta.getPersistentDataContainer();
                itemData.set(itemKey, PersistentDataType.STRING, this.customMaterial.getName());
                //itemData.set(descriptionKey, PersistentDataType.STRING, );
                //meta.setLore(lore);
                this.item.setItemMeta(meta);
                break;

        }

    }

    public ItemStack getItem() {
        return item;
    }

    public static boolean hasTag(ItemStack item) {
        if (item != null && item.getItemMeta() != null) {
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer itemData = meta.getPersistentDataContainer();
            return itemData.has(itemKey, PersistentDataType.STRING);
        } else {
            return false;
        }
    }

    public static boolean hasTag(ItemStack item, CustomMaterial customMaterial) {

        if (CustomItemStack.hasTag(item)) {
            ItemMeta meta = item.getItemMeta();
            assert meta != null;
            PersistentDataContainer itemData = meta.getPersistentDataContainer();
            return Objects.requireNonNull(itemData.get(itemKey, PersistentDataType.STRING)).equalsIgnoreCase(customMaterial.getName());
        }

        return false;
    }

    public static boolean hasCustomItemInMainHand(String name, Player player) {
        player.getInventory().getItemInMainHand();
        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
            if (CustomItemStack.hasTag(player.getInventory().getItemInMainHand())) {
                ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                assert meta != null;
                PersistentDataContainer itemData = meta.getPersistentDataContainer();
                return Objects.requireNonNull(itemData.get(itemKey, PersistentDataType.STRING)).equalsIgnoreCase(name);
            }
        }
        return false;
    }

    public static boolean hasCustomItemInOffHand(String name, Player player) {
        player.getInventory().getItemInOffHand();
        if (player.getInventory().getItemInOffHand().getType() != Material.AIR) {
            if (CustomItemStack.hasTag(player.getInventory().getItemInOffHand())) {
                ItemMeta meta = player.getInventory().getItemInOffHand().getItemMeta();
                assert meta != null;
                PersistentDataContainer itemData = meta.getPersistentDataContainer();
                return Objects.requireNonNull(itemData.get(itemKey, PersistentDataType.STRING)).equalsIgnoreCase(name);
            }
        }
        return false;
    }

    public static CustomMaterial getCustomMaterial(ItemStack item) {
        if (!hasTag(item)) {
            return null;
        }
        return CustomMaterial.getByName(item.getItemMeta().getPersistentDataContainer().get(CustomItemStack.getItemKey(), PersistentDataType.STRING));
    }

    public static NamespacedKey getDescriptionKey() {
        return descriptionKey;
    }

    public static NamespacedKey getItemKey() {
        return itemKey;
    }


}
