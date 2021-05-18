package fr.zelytra.daedalus.managers.guardian;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.utils.Utils;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.EntityVindicator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Guardian implements Listener {
    private static final List<Guardian> guardianList = new ArrayList<>();

    private final LivingEntity entity;
    private final Location spawnLoc;

    private final static NamespacedKey spawnLocKey = new NamespacedKey(Daedalus.getInstance(), "spawnLoc");

    private final int health = 100;
    private final BossBar bossBar;
    private final int respawnCooldown = 10; //in seconds

    public Guardian(Location location) {
        this.spawnLoc = location;
        this.bossBar = Bukkit.createBossBar(this.spawnLocKey, "§bGuardian", BarColor.BLUE, BarStyle.SEGMENTED_6, BarFlag.CREATE_FOG);
        this.entity = initGuardian();
        guardianList.add(this);
    }

    private LivingEntity initGuardian() {
        EntityVindicator guardian = new EntityVindicator(EntityTypes.VINDICATOR, ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle());
        guardian.setPosition(this.spawnLoc.getBlockX(), this.spawnLoc.getBlockY(), this.spawnLoc.getBlockZ());
        LivingEntity entity = (LivingEntity) guardian.getBukkitEntity();

        /* Frontend */
        entity.setRemoveWhenFarAway(false);
        entity.setCustomName("§bGuardian");
        entity.setCustomNameVisible(true);
        entity.setMaxHealth(health);
        entity.setHealth(health);

        entity.getEquipment().setHelmet(Utils.EnchantedItemStack(Material.DIAMOND_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL,2));
        entity.getEquipment().setChestplate(Utils.EnchantedItemStack(Material.NETHERITE_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL,2));
        entity.getEquipment().setLeggings(Utils.EnchantedItemStack(Material.DIAMOND_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL,2));
        entity.getEquipment().setBoots(Utils.EnchantedItemStack(Material.DIAMOND_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL,2));

        entity.getEquipment().setItemInMainHand(Utils.EnchantedItemStack(Material.NETHERITE_AXE, Enchantment.DAMAGE_ALL,3));

        /* Backend */
        PersistentDataContainer dataContainer = entity.getPersistentDataContainer();
        dataContainer.set(spawnLocKey, PersistentDataType.STRING, this.spawnLoc.getWorld() + "," + (int) this.spawnLoc.getX() + "," + (int) this.spawnLoc.getY() + "," + (int) this.spawnLoc.getZ());
        this.bossBar.setProgress(entity.getHealth() / health);
        ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle().addEntity(guardian);

        return entity;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public int getHealth() {
        return health;
    }

    public static boolean isGuardian(LivingEntity entity) {
        return entity.getPersistentDataContainer().has(spawnLocKey, PersistentDataType.STRING);
    }

    @Nullable
    public static Guardian getGuardianFromList(LivingEntity entity) {
        for (Guardian guardian : guardianList) {
            if (guardian.getEntity() == entity) {
                return guardian;
            }
        }
        return null;
    }


    public void death() {
        guardianList.remove(this);
        this.bossBar.removeAll();
        Bukkit.getScheduler().runTaskLater(Daedalus.getInstance(), () -> new Guardian(this.spawnLoc), respawnCooldown * 20);
    }
}
