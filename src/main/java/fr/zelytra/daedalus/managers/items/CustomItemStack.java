package fr.zelytra.daedalus.managers.items;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class CustomItemStack {
    private final static NamespacedKey itemKey = new NamespacedKey(Daedalus.getInstance(), "daedalus");
    private final NamespacedKey descriptionKey = new NamespacedKey(Daedalus.getInstance(), "description");

    private final ItemStack item;
    private CustomMaterial customMaterial;
    private int amount;

    /**
     * @param material Custom material of the item
     * @param amount   Item amount
     */
    public CustomItemStack(CustomMaterial material, int amount) {

        this.customMaterial = material;
        this.amount = amount;

        //Creating itemstack
        this.item = new ItemStack(this.customMaterial.getVanillaMaterial(), this.amount);
        ItemMeta meta = this.item.getItemMeta();
        meta.setCustomModelData(this.customMaterial.getCustomModelData());
        meta.setDisplayName(this.customMaterial.getDisplayName());
        PersistentDataContainer itemData = meta.getPersistentDataContainer();
        itemData.set(itemKey, PersistentDataType.STRING, this.customMaterial.getName());
        //itemData.set(descriptionKey, PersistentDataType.STRING, );
        //meta.setLore(lore);
        this.item.setItemMeta(meta);

    }

    public ItemStack getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static boolean hasTag(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer itemData = meta.getPersistentDataContainer();
        if (itemData.has(itemKey, PersistentDataType.STRING)) {
            return true;
        }
        return false;
    }


}
