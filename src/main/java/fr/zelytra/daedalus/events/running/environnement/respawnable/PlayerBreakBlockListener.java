package fr.zelytra.daedalus.events.running.environnement.respawnable;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class PlayerBreakBlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){

        try{
            BlockEnum block = BlockEnum.valueOf(e.getBlock().getType().toString());

            e.setCancelled(true);



            switch (block) {

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
                case DARK_OAK_WOOD: {
                    if(GameSettings.CUT_CLEAN){
                        e.getBlock().setType(Material.AIR);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), block.getItemStack());
                    }else
                        e.getBlock().breakNaturally();

                    break;
                }

                case OBSIDIAN:
                case END_STONE:
                case DIAMOND_ORE:
                case EMERALD_ORE:
                case COAL_ORE:
                case LAPIS_ORE:
                case REDSTONE_ORE:{
                    e.getBlock().breakNaturally();
                    break;
                }

                case OAK_LEAVES:
                case BIRCH_LEAVES:
                case ACACIA_LEAVES:
                case JUNGLE_LEAVES:
                case SPRUCE_LEAVES:
                case DARK_OAK_LEAVES:{
                    e.getBlock().setType(Material.AIR);
                    dropItem(e.getBlock().getLocation(), block.getItemStack(), GameSettings.APPLE_DROP*0.01);
                    break;
                }
            }

            giveXP(e.getPlayer(), block);

            if(block.getMaterial() != Material.ANCIENT_DEBRIS){
                Bukkit.getScheduler().scheduleSyncDelayedTask(Daedalus.getInstance(), ()-> replaceBlock(e.getBlock().getLocation(), block.getMaterial()), block.getSeconds() * 20L);
            }

        }catch (IllegalArgumentException ignored){
        }
    }

    private void giveXP(Player p, BlockEnum block){

        switch (block){

            case IRON_ORE:
                if(GameSettings.CUT_CLEAN) {
                    if (new Random().nextDouble() <= 0.7) {
                        p.giveExp(1);
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
                    }
                }
                break;
            case GOLD_ORE:
                if(GameSettings.CUT_CLEAN) {
                    p.giveExp(1);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
                }
                break;
            case DIAMOND_ORE:
            case EMERALD_ORE:
                p.giveExp(1);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
                break;
            case COAL_ORE:
                if(new Random().nextDouble() <= 0.1) {
                    p.giveExp(1);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
                }
                break;
            case LAPIS_ORE:
                if(new Random().nextDouble() <= 0.2) {
                    p.giveExp(1);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
                }
                break;
            case ANCIENT_DEBRIS:
                if(GameSettings.CUT_CLEAN) {
                    p.giveExp(2);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
                }
                break;
            case REDSTONE_ORE:
                if(new Random().nextDouble() <= 0.7) {
                    p.giveExp(1);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
                }
                break;
            case SAND:
            case RED_SAND:
                if(GameSettings.CUT_CLEAN) {
                    if (new Random().nextDouble() <= 0.1) {
                        p.giveExp(1);
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
                    }
                }
                break;
        }

    }

    private void replaceBlock(Location loc, Material material){

        if(loc.getBlock().getType() != Material.AIR)
            loc.getBlock().breakNaturally();
        loc.getBlock().setType(material);

    }

    private void dropItem(Location loc, ItemStack itemStack, double percent){

        if(new Random().nextDouble() <= percent)
                loc.getWorld().dropItemNaturally(loc, itemStack);

    }
}
 