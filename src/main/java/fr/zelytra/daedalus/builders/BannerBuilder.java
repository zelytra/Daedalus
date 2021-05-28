package fr.zelytra.daedalus.builders;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class BannerBuilder {

    ItemStack banner;
    public static NamespacedKey bannerKey = new NamespacedKey(Daedalus.getInstance(),"banner");

    public BannerBuilder(String name, Material bannerMaterial,  Pattern... patterns){


        banner = new ItemStack(bannerMaterial);
        BannerMeta meta = (BannerMeta) banner.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.setPatterns(Arrays.asList(patterns));
        meta.getPersistentDataContainer().set(bannerKey, PersistentDataType.STRING,"customBanner");
        banner.setItemMeta(meta);

    }

    public ItemStack getBanner() {
        return banner;
    }
}
 