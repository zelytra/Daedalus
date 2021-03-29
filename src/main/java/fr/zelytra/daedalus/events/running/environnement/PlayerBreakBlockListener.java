package fr.zelytra.daedalus.events.running.environnement;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerBreakBlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){


        try{
            BlockEnum block = BlockEnum.valueOf(e.getBlock().getType().toString());

            e.setCancelled(true);



            switch (block) {

                case IRON_ORE:
                case GOLD_ORE:{
                    e.getBlock().setType(Material.AIR);
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), block.getItemStack());
                    break;
                }

                case OBSIDIAN:
                case END_STONE:
                case ANCIENT_DEBRIS:
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
                    dropItem(e.getBlock().getLocation(), block.getItemStack(), 0.08);
                    break;
                }
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
                    dropItem(e.getBlock().getLocation(), block.getItemStack(), -1);
                    break;
                }
            }

            if(block.getMaterial() != Material.ANCIENT_DEBRIS){
                Bukkit.getScheduler().scheduleSyncDelayedTask(Daedalus.getInstance(), ()-> replaceBlock(e.getBlock().getLocation(), block.getMaterial()), block.getSeconds() * 20L);
            }

        }catch (IllegalArgumentException ignored){
        }
    }

    private void replaceBlock(Location loc, Material material){

        if(loc.getBlock().getType() != Material.AIR)
            loc.getBlock().breakNaturally();
        loc.getBlock().setType(material);

    }

    private void dropItem(Location loc, ItemStack itemStack, double percent){

        if(percent == -1)
            loc.getWorld().dropItemNaturally(loc, itemStack);
        else
            if(Math.random() <= percent)
                loc.getWorld().dropItemNaturally(loc, itemStack);

    }
}
 