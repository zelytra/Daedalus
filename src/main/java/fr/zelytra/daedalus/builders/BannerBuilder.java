package fr.zelytra.daedalus.builders;

import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.Arrays;

public class BannerBuilder {

    ItemStack banner;

    public BannerBuilder(String name, Pattern... patterns){


        banner = new ItemStack(Material.WHITE_BANNER);
        BannerMeta meta = (BannerMeta) banner.getItemMeta();
        assert meta != null;
        meta.setDisplayName(name);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.setPatterns(Arrays.asList(patterns));
        banner.setItemMeta(meta);

    }

    public ItemStack getBanner() {
        return banner;
    }
}
 