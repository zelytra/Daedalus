package fr.zelytra.daedalus.managers.items;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.languages.Lang;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class CustomItemStack {
  @Getter
  private static final NamespacedKey itemKey =
      new NamespacedKey(Daedalus.getInstance(), "daedalus");

  private static final NamespacedKey descriptionKey =
      new NamespacedKey(Daedalus.getInstance(), "description");

  private static final NamespacedKey dionysusValue =
      new NamespacedKey(Daedalus.getInstance(), "value");
  private static final NamespacedKey dionysusMaxValue =
      new NamespacedKey(Daedalus.getInstance(), "maxValue");

  @Getter private final ItemStack item;
  private final CustomMaterial customMaterial;

  /**
   * @param material Custom material of the item
   * @param amount Item amount
   */
  public CustomItemStack(CustomMaterial material, int amount) {

    this.customMaterial = material;
    if (Objects.requireNonNull(material.getItemType()) == ItemType.ARMOR) {
      this.item = new ItemStack(this.customMaterial.getVanillaMaterial(), amount);
      ItemMeta meta = this.item.getItemMeta();
      assert meta != null;
      meta.setCustomModelData(this.customMaterial.getCustomModelData());
      meta.setDisplayName(this.customMaterial.getDisplayName());

      meta.addAttributeModifier(
          Attribute.GENERIC_ARMOR,
          AttributeGenerator.armor(material.getArmor(), material.getSlot().getGroup()));
      meta.addAttributeModifier(
          Attribute.GENERIC_MAX_HEALTH,
          AttributeGenerator.extraHeart(material.getExtraHeart(), material.getSlot().getGroup()));

      PersistentDataContainer itemData = meta.getPersistentDataContainer();
      itemData.set(itemKey, PersistentDataType.STRING, this.customMaterial.getName());
      // itemData.set(descriptionKey, PersistentDataType.STRING, );
      // meta.setLore(lore);
      this.item.setItemMeta(meta);
    } else {
      ItemMeta meta;
      PersistentDataContainer itemData;
      this.item = new ItemStack(this.customMaterial.getVanillaMaterial(), amount);
      meta = this.item.getItemMeta();
      assert meta != null;
      meta.setCustomModelData(this.customMaterial.getCustomModelData());
      meta.setDisplayName(this.customMaterial.getDisplayName());
      itemData = meta.getPersistentDataContainer();
      itemData.set(itemKey, PersistentDataType.STRING, this.customMaterial.getName());
      // itemData.set(descriptionKey, PersistentDataType.STRING, );
      // meta.setLore(lore);
      this.item.setItemMeta(meta);

      if (material == CustomMaterial.DIONYSUS_CUP) dionysosMugInit();
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

        meta.addAttributeModifier(
            Attribute.GENERIC_ARMOR,
            AttributeGenerator.armor(material.getArmor(), material.getSlot().getGroup()));
        meta.addAttributeModifier(
            Attribute.GENERIC_MAX_HEALTH,
            AttributeGenerator.extraHeart(material.getExtraHeart(), material.getSlot().getGroup()));

        PersistentDataContainer itemData = meta.getPersistentDataContainer();
        itemData.set(itemKey, PersistentDataType.STRING, this.customMaterial.getName());
        // itemData.set(descriptionKey, PersistentDataType.STRING, );
        // meta.setLore(lore);
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
        // itemData.set(descriptionKey, PersistentDataType.STRING, );
        // meta.setLore(lore);
        this.item.setItemMeta(meta);

        if (material == CustomMaterial.DIONYSUS_CUP) dionysosMugInit();

        break;
    }
  }

  public CustomItemStack(CustomMaterial material, Lang lang) {

    this.customMaterial = material;
    switch (material.getItemType()) {
      case ARMOR:
        this.item = new ItemStack(this.customMaterial.getVanillaMaterial());
        ItemMeta meta = this.item.getItemMeta();
        assert meta != null;
        meta.setCustomModelData(this.customMaterial.getCustomModelData());
        meta.setDisplayName(this.customMaterial.getDisplayName(lang));

        meta.addAttributeModifier(
            Attribute.GENERIC_ARMOR,
            AttributeGenerator.armor(material.getArmor(), material.getSlot().getGroup()));
        meta.addAttributeModifier(
            Attribute.GENERIC_MAX_HEALTH,
            AttributeGenerator.extraHeart(material.getExtraHeart(), material.getSlot().getGroup()));

        PersistentDataContainer itemData = meta.getPersistentDataContainer();
        itemData.set(itemKey, PersistentDataType.STRING, this.customMaterial.getName());
        // itemData.set(descriptionKey, PersistentDataType.STRING, );
        // meta.setLore(lore);
        this.item.setItemMeta(meta);
        break;

      default:
        this.item = new ItemStack(this.customMaterial.getVanillaMaterial());
        meta = this.item.getItemMeta();
        assert meta != null;
        meta.setCustomModelData(this.customMaterial.getCustomModelData());
        meta.setDisplayName(this.customMaterial.getDisplayName(lang));
        itemData = meta.getPersistentDataContainer();
        itemData.set(itemKey, PersistentDataType.STRING, this.customMaterial.getName());
        // itemData.set(descriptionKey, PersistentDataType.STRING, );
        // meta.setLore(lore);
        this.item.setItemMeta(meta);

        if (material == CustomMaterial.DIONYSUS_CUP) dionysosMugInit();

        break;
    }
  }

  private void dionysosMugInit() {
    ItemMeta meta = this.item.getItemMeta();
    PersistentDataContainer itemData = meta.getPersistentDataContainer();
    itemData.set(dionysusValue, PersistentDataType.INTEGER, 1);
    itemData.set(dionysusMaxValue, PersistentDataType.INTEGER, 1);

    List<String> lore = new ArrayList<>();
    lore.add("");
    lore.add(GameSettings.LANG.textOf("item.dionysusSlip") + 1 + "§6/§a" + 1);
    lore.add("");
    meta.setLore(lore);

    this.item.setItemMeta(meta);
  }

  public static NamespacedKey getDionysusValueKey() {
    return dionysusValue;
  }

  public static NamespacedKey getDionysusMaxValueKey() {
    return dionysusMaxValue;
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
      return Objects.requireNonNull(itemData.get(itemKey, PersistentDataType.STRING))
          .equalsIgnoreCase(customMaterial.getName());
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
        return Objects.requireNonNull(itemData.get(itemKey, PersistentDataType.STRING))
            .equalsIgnoreCase(name);
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
        return Objects.requireNonNull(itemData.get(itemKey, PersistentDataType.STRING))
            .equalsIgnoreCase(name);
      }
    }
    return false;
  }

  public static CustomMaterial getCustomMaterial(ItemStack item) {
    if (!hasTag(item)) {
      return null;
    }
    return CustomMaterial.getByName(
        item.getItemMeta()
            .getPersistentDataContainer()
            .get(CustomItemStack.getItemKey(), PersistentDataType.STRING));
  }
}
