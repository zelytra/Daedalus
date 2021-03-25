package fr.zelytra.daedalus.events.running.environnement;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBreakBlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){


        try{
            OreEnum ore = OreEnum.valueOf(e.getBlock().getType().toString());

            e.setCancelled(true);

            switch (ore) {

                case IRON_ORE:
                case GOLD_ORE:{
                    e.getBlock().setType(Material.AIR);
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), ore.getItemStack());
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
            }

            if(ore.getMaterial() != Material.ANCIENT_DEBRIS)
                Bukkit.getScheduler().scheduleSyncDelayedTask(Daedalus.getInstance(), ()-> replaceBlock(e.getBlock().getLocation(), ore.getMaterial()), ore.getTicks());

        }catch (IllegalArgumentException ignored){
        }
    }

    private void replaceBlock(Location loc, Material material){

        if(loc.getBlock().getType() != Material.AIR)
            loc.getBlock().breakNaturally();
        loc.getBlock().setType(material);

    }
}
 