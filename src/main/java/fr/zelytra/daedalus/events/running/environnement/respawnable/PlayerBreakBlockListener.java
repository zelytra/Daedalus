package fr.zelytra.daedalus.events.running.environnement.respawnable;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerBreakBlockListener implements Listener {
    private static HashMap<Location, BukkitTask> taskIDs = new HashMap<>();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        try {
            BlockEnum block = BlockEnum.valueOf(e.getBlock().getType().toString());
            e.setDropItems(false);

            switch (block) {
                case STONE:
                case IRON_ORE:
                case GOLD_ORE:
                case SAND:
                case ANCIENT_DEBRIS:
                case RED_SAND:
                case OAK_LOG:
                case OAK_WOOD:
                case BIRCH_LOG:
                case BIRCH_WOOD:
                case ACACIA_LOG:
                case ACACIA_WOOD:
                case JUNGLE_LOG:
                case JUNGLE_WOOD:
                case SPRUCE_LOG:
                case SPRUCE_WOOD:
                case DARK_OAK_LOG:
                case GRAVEL:
                case END_STONE:
                case DARK_OAK_WOOD: {
                    if (GameSettings.CUT_CLEAN) {
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), block.getItemStack());
                    }
                    break;
                }
                case OBSIDIAN:
                case DIAMOND_ORE:
                case EMERALD_ORE:
                case COAL_ORE:
                case LAPIS_ORE:
                case REDSTONE_ORE: {
                    e.setDropItems(true);
                    break;
                }

                case OAK_LEAVES:
                case BIRCH_LEAVES:
                case ACACIA_LEAVES:
                case JUNGLE_LEAVES:
                case SPRUCE_LEAVES:
                case DARK_OAK_LEAVES: {
                    dropItem(e.getBlock().getLocation(), block.getItemStack(), GameSettings.APPLE_DROP * 0.01);
                    break;
                }
            }

            giveXP(block, e);
            final BlockState state = e.getBlock().getState();
            if (block.getMaterial() != Material.ANCIENT_DEBRIS) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(Daedalus.getInstance(), () -> replaceBlock(state), block.getSeconds() * 20L);
            }

        } catch (IllegalArgumentException ignored) {
        }
    }

    private void giveXP(BlockEnum block, BlockBreakEvent e) {

        switch (block) {
            case IRON_ORE:
                if (GameSettings.CUT_CLEAN) {
                    popXP(e.getBlock().getLocation(), 1);
                }
                break;
            case GOLD_ORE:
                if (GameSettings.CUT_CLEAN) {
                    popXP(e.getBlock().getLocation(), ThreadLocalRandom.current().nextInt(0, 5));
                }
                break;

            case ANCIENT_DEBRIS:
                if (GameSettings.CUT_CLEAN) {
                    popXP(e.getBlock().getLocation(), 2);
                }
                break;
            case SAND:
            case RED_SAND:
                if (GameSettings.CUT_CLEAN) {
                    popXP(e.getBlock().getLocation(), ThreadLocalRandom.current().nextInt(0, 10) / 10);
                }
                break;
        }

    }

    private void replaceBlock(BlockState state) {
        if (state.getBlock().getLocation().getBlock().getType() != Material.AIR) {
            state.getBlock().getLocation().getBlock().breakNaturally();
        }

        Collection<Entity> list = state.getWorld().getNearbyEntities(state.getLocation(), 1, 1, 1);
        if (!list.isEmpty()) {
            BukkitTask taskID = Bukkit.getScheduler().runTaskTimer(Daedalus.getInstance(), () -> {
                Collection<Entity> nearbyEntities = state.getWorld().getNearbyEntities(state.getLocation(), 1, 1, 1);
                if (nearbyEntities.isEmpty()) {
                    state.update(true);
                    int id = taskIDs.get(state.getLocation()).getTaskId();
                    taskIDs.remove(state.getLocation());
                    Bukkit.getScheduler().cancelTask(id);

                }
            }, 0, 40);
            taskIDs.put(state.getLocation(), taskID);

        } else
            state.update(true);

    }

    private void dropItem(Location loc, ItemStack itemStack, double percent) {

        if (new Random().nextDouble() <= percent)
            loc.getWorld().dropItemNaturally(loc, itemStack);

    }

    private void popXP(Location location, int amount) {
        if (amount <= 0) {
            return;
        }
        (location.getWorld().spawn(location, ExperienceOrb.class)).setExperience(amount);

    }
}
 