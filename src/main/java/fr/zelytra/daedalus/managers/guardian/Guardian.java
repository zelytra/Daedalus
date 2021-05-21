package fr.zelytra.daedalus.managers.guardian;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.utils.Utils;
import net.minecraft.server.v1_16_R3.EntitySkeleton;
import net.minecraft.server.v1_16_R3.EntitySpider;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Guardian implements Listener {
    private static final List<Guardian> guardianList = new ArrayList<>();

    private final LivingEntity entity;
    private final Location spawnLoc;

    private final static NamespacedKey spawnLocKey = new NamespacedKey(Daedalus.getInstance(), "spawnLoc");

    private final int health = 100;
    private final BossBar bossBar;
    private final int respawnCooldown = 30;//600; //in seconds

    public Guardian(Location location) {
        this.spawnLoc = location;
        this.bossBar = Bukkit.createBossBar(this.spawnLocKey, "§bGuardian", BarColor.BLUE, BarStyle.SEGMENTED_6, BarFlag.CREATE_FOG);
        this.entity = initGuardian();
        guardianList.add(this);
    }

    public static List<Guardian> getGuardianList() {
        return guardianList;
    }

    private LivingEntity initGuardian() {


        /* Guardian */
        Entity guardianEntity = this.spawnLoc.getWorld().spawn(this.spawnLoc, Vindicator.class, entity -> {

            entity.setRemoveWhenFarAway(false);
            entity.setCustomName("§bGuardian");
            entity.setCustomNameVisible(true);
            entity.setMaxHealth(health);
            entity.setHealth(health);

            entity.getEquipment().setHelmet(Utils.EnchantedItemStack(Material.NETHERITE_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 2));
            entity.getEquipment().setChestplate(Utils.EnchantedItemStack(Material.NETHERITE_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 3));
            entity.getEquipment().setLeggings(Utils.EnchantedItemStack(Material.NETHERITE_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 2));
            entity.getEquipment().setBoots(Utils.EnchantedItemStack(Material.NETHERITE_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 2));
            entity.getEquipment().setItemInMainHand(Utils.EnchantedItemStack(Material.NETHERITE_AXE, Enchantment.DAMAGE_ALL, 5));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1, false, false));
        });

        /* Ravager */
        this.spawnLoc.getWorld().spawn(this.spawnLoc, Ravager.class, entity -> {
            entity.setPassenger(guardianEntity);
            entity.setRemoveWhenFarAway(false);
        });

        /* Pillager */
        for (int x = 1; x <= 4; x++) {
            this.spawnLoc.getWorld().spawn(this.spawnLoc, Pillager.class, entity -> {
                entity.getEquipment().setItemInMainHand(new ItemStack(Material.CROSSBOW));
                entity.setRemoveWhenFarAway(false);
            });

        }

        /* Backend */
        PersistentDataContainer dataContainer = guardianEntity.getPersistentDataContainer();
        dataContainer.set(spawnLocKey, PersistentDataType.STRING, this.spawnLoc.getWorld() + "," + (int) this.spawnLoc.getX() + "," + (int) this.spawnLoc.getY() + "," + (int) this.spawnLoc.getZ());
        this.bossBar.setProgress(((LivingEntity) guardianEntity).getHealth() / health);


        return (LivingEntity) guardianEntity;
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

    public void customAttack() {

        if (((entity.getHealth() * 100) / entity.getMaxHealth()) >= 50) {
            final int attackLuck = 35;
            /* PHASE 1 */
            if (ThreadLocalRandom.current().nextInt(0, 100) <= attackLuck) {
                switch (ThreadLocalRandom.current().nextInt(0, 4)) {
                    case 0:
                        ejection();
                        break;
                    case 1:
                        dragonBall();
                        break;
                    case 2:
                        jawbone();
                        break;
                    case 3:
                        summonSpider();
                        break;

                }
            }
        } else {
            final int attackLuck = 70;
            /* PHASE 2 */
            if (ThreadLocalRandom.current().nextInt(0, 100) <= attackLuck) {
                switch (ThreadLocalRandom.current().nextInt(0, 4)) {
                    case 0:
                        ejection();
                        break;
                    case 1:
                        dragonBall();
                        break;
                    case 2:
                        summonSkeleton();
                        break;
                    case 3:
                        spell();
                        break;

                }
            }
        }
    }

    private void summonSpider() {
        List<Entity> nearbyEntities = entity.getNearbyEntities(15, 15, 15);
        boolean isPlayer = false;
        for (Entity e : nearbyEntities) {
            if (e instanceof Player) {
                isPlayer = true;
                break;
            }
        }
        if (isPlayer) {
            for (int x = 1; x <= 4; x++) {
                EntitySpider spider = new EntitySpider(EntityTypes.SPIDER, ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle());
                spider.setPosition(entity.getLocation().getBlockX(), entity.getLocation().getBlockY(), entity.getLocation().getBlockZ());
                LivingEntity e = (LivingEntity) spider.getBukkitEntity();
                e.setMaxHealth(28);
                e.setHealth(28);
                e.setCustomName("Guardian");
                ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle().addEntity(spider, CreatureSpawnEvent.SpawnReason.CUSTOM);

            }
        }
    }

    private void summonSkeleton() {
        List<Entity> nearbyEntities = entity.getNearbyEntities(15, 15, 15);
        boolean isPlayer = false;
        for (Entity e : nearbyEntities) {
            if (e instanceof Player) {
                isPlayer = true;
                break;
            }
        }
        if (isPlayer) {
            for (int x = 1; x <= 4; x++) {
                EntitySkeleton skeleton = new EntitySkeleton(EntityTypes.SKELETON, ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle());
                skeleton.setPosition(entity.getLocation().getBlockX(), entity.getLocation().getBlockY(), entity.getLocation().getBlockZ());
                LivingEntity e = (LivingEntity) skeleton.getBukkitEntity();
                ItemStack bow = Utils.EnchantedItemStack(Material.BOW, Enchantment.ARROW_KNOCKBACK, 2);
                bow.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
                e.getEquipment().setItemInMainHand(bow);
                e.getEquipment().setHelmetDropChance(0);
                e.getEquipment().setItemInMainHandDropChance(0);
                e.getEquipment().setHelmet(Utils.EnchantedItemStack(Material.NETHERITE_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 4));
                e.setCustomName("Guardian");
                ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle().addEntity(skeleton, CreatureSpawnEvent.SpawnReason.CUSTOM);
            }
        }
    }

    private void spell() {
        List<Entity> nearbyEntities = entity.getNearbyEntities(8, 8, 8);
        for (Entity e : nearbyEntities) {
            if (e instanceof Player) {
                Player player = ((Player) e).getPlayer();
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1, false, false, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1, false, false, true));
            }
        }
    }

    private void ejection() {
        List<Entity> nearbyEntities = entity.getNearbyEntities(8, 8, 8);
        boolean isPlayer = false;
        for (Entity e : nearbyEntities) {
            if (e instanceof Player) {
                isPlayer = true;
                break;
            }
        }
        if (isPlayer) {

            for (Entity e : nearbyEntities) {
                Vector delta = new Vector(e.getLocation().getX() - entity.getLocation().getX(), 0, e.getLocation().getZ() - entity.getLocation().getZ());
                double norme = Math.sqrt(Math.pow(delta.getX() + 0.01, 2) + Math.pow(delta.getY() + 0.01, 2) + Math.pow(delta.getZ() + 0.01, 2));
                final int coef = 2;

                Vector dir = new Vector((delta.getX() / norme) * coef, (delta.getY() / norme) + 1, (delta.getZ() / norme) * coef);
                e.setVelocity(dir);

                if (e instanceof Player)
                    ((Player) e).playSound(entity.getLocation(), Sound.ITEM_TRIDENT_RETURN, 1, 1);

            }
            entity.getWorld().spawnParticle(Particle.DRAGON_BREATH, entity.getLocation(), 190);
        }

    }

    private void dragonBall() {
        List<Entity> nearbyEntities = entity.getNearbyEntities(15, 15, 15);
        boolean isPlayer = false;
        for (Entity e : nearbyEntities) {
            if (e instanceof Player) {
                isPlayer = true;
                break;
            }
        }
        if (isPlayer) {
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.getInstance(), () -> {
                for (int size = 4; size <= 20; size += 4) {
                    for (int d = 0; d <= 20; d += 1) {
                        Location ballLoc = new Location(entity.getWorld(), entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ());
                        ballLoc.setX(entity.getLocation().getX() + Math.cos(d) * size);
                        ballLoc.setZ(entity.getLocation().getZ() + Math.sin(d) * size);
                        ballLoc.setY(ballLoc.getY() + 10);
                        Bukkit.getScheduler().runTask(Daedalus.getInstance(), () -> {
                            Entity dragonBall = entity.getWorld().spawnEntity(ballLoc, EntityType.FIREBALL);
                            dragonBall.setVelocity(new Vector(0, -2, 0));
                        });

                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    private void jawbone() {
        List<Entity> nearbyEntities = entity.getNearbyEntities(15, 15, 15);
        boolean isPlayer = false;
        for (Entity e : nearbyEntities) {
            if (e instanceof Player) {
                isPlayer = true;
                break;
            }
        }
        if (isPlayer) {
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.getInstance(), () -> {
                double size = 1;

                for (double d = 0; d <= 10; d += 0.2) {
                    Location fangLoc = new Location(entity.getWorld(), entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ());
                    fangLoc.setX(entity.getLocation().getX() + Math.cos(d) * size);
                    fangLoc.setZ(entity.getLocation().getZ() + Math.sin(d) * size);
                    Bukkit.getScheduler().runTask(Daedalus.getInstance(), () -> entity.getLocation().getWorld().spawnEntity(fangLoc, EntityType.EVOKER_FANGS));
                    size += 0.3;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
