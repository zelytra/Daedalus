package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.loottable.LootsEnum;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DionysosMug implements Listener {
    private int itemCooldown = 60;

    @EventHandler
    public void onRightClick(CustomItemUseEvent e) {

        if (e.getMaterial() != CustomMaterial.DIONYSOS_MUG) return;
        ItemMeta meta = e.getItem().getItemMeta();
        int maxValue = meta.getPersistentDataContainer().get(CustomItemStack.getDionysusMaxValueKey(), PersistentDataType.INTEGER);
        int value = meta.getPersistentDataContainer().get(CustomItemStack.getDionysusValueKey(), PersistentDataType.INTEGER);

        if (e.getEvent().getAction() == Action.RIGHT_CLICK_BLOCK && e.getEvent().getClickedBlock().getRelative(e.getEvent().getBlockFace()).getType() == Material.WATER) {

            if (!Cooldown.cooldownCheck(e.getPlayer(), CustomMaterial.DIONYSOS_MUG.getName()))
                return;


            e.getPlayer().sendMessage(Message.getPlayerPrefixe() + "§6*hick*... Cheers !");
            updateItem(e, maxValue, maxValue);

            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(e.getPlayer().getLocation(), Sound.ITEM_BOTTLE_FILL_DRAGONBREATH, 2, 1);
            }

            new Cooldown(e.getPlayer(), itemCooldown, CustomMaterial.DIONYSOS_MUG.getName());
            return;
        }

        if (value > 0) {
            e.getPlayer().addPotionEffects(getRandomPotion());
            meta.getPersistentDataContainer().set(CustomItemStack.getDionysusValueKey(), PersistentDataType.INTEGER, value - 1);
            e.getItem().setItemMeta(meta);
            updateItem(e, value - 1, maxValue);

            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(e.getPlayer().getLocation(), Sound.ITEM_HONEY_BOTTLE_DRINK, 2, 1);
            }

        } else {
            e.getPlayer().sendMessage(Message.getPlayerPrefixe() + "§6Huuu... It's empty *hick*");
            return;
        }


    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent e) {
        ItemStack[] items = e.getInventory().getMatrix();
        boolean asMug = false;
        boolean asBottle = false;
        ItemStack result = null;
        ItemStack bottles = null;

        for (ItemStack item : items) {
            if (item == null)
                continue;

            if (CustomItemStack.getCustomMaterial(item) != null && CustomItemStack.getCustomMaterial(item) == CustomMaterial.DIONYSOS_MUG) {
                asMug = true;
                result = item.clone();
                continue;
            }
            if (item.getType() == Material.GLASS_BOTTLE && item.getAmount()==1) {
                asBottle = true;
                bottles = item;
                continue;
            }
            return;


        }

        if (asBottle && asMug) {
            ItemMeta meta = result.getItemMeta();
            PersistentDataContainer itemData = meta.getPersistentDataContainer();

            int maxValue = meta.getPersistentDataContainer().get(CustomItemStack.getDionysusMaxValueKey(), PersistentDataType.INTEGER) + bottles.getAmount();
            int value = meta.getPersistentDataContainer().get(CustomItemStack.getDionysusValueKey(), PersistentDataType.INTEGER);

            itemData.set(CustomItemStack.getDionysusMaxValueKey(), PersistentDataType.INTEGER, maxValue > 10 ? 10 : maxValue);
            itemData.set(CustomItemStack.getDionysusValueKey(), PersistentDataType.INTEGER, value);

            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("§6§lSip: §a" + value + "§6/§a" + (maxValue > 10 ? 10 : maxValue));
            lore.add("");
            meta.setLore(lore);

            result.setItemMeta(meta);
            e.getInventory().setResult(result);
        }

    }


    private Collection<PotionEffect> getRandomPotion() {
        List<PotionEffect> potions = new ArrayList<>();

        if ((Math.random() * 100) <= 10.0)
            potions.add(LootsEnum.NAUSEA.getPotionEffect());

        if ((Math.random() * 100) <= 80.0) {

            if ((Math.random() * 100) <= 80.0) {

                int loot = ThreadLocalRandom.current().nextInt(0, Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.DIONYSUS.getName() + "_tier1").getLootsEnum().size());
                potions.add(Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.DIONYSUS.getName() + "_tier1").getLootsEnum().get(loot).getPotionEffect());

            } else {

                int loot = ThreadLocalRandom.current().nextInt(0, Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.DIONYSUS.getName() + "_tier2").getLootsEnum().size());
                potions.add(Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.DIONYSUS.getName() + "_tier2").getLootsEnum().get(loot).getPotionEffect());

            }
        }
        return potions;
    }

    private void updateItem(CustomItemUseEvent e, int value, int maxValue) {
        ItemMeta meta = e.getItem().getItemMeta();
        PersistentDataContainer itemData = meta.getPersistentDataContainer();
        itemData.set(CustomItemStack.getDionysusMaxValueKey(), PersistentDataType.INTEGER, maxValue);
        itemData.set(CustomItemStack.getDionysusValueKey(), PersistentDataType.INTEGER, value);

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§6§lSip: §a" + value + "§6/§a" + maxValue);
        lore.add("");
        meta.setLore(lore);

        e.getItem().setItemMeta(meta);
    }
}
