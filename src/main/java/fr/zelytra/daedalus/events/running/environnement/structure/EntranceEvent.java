package fr.zelytra.daedalus.events.running.environnement.structure;

import fr.zelytra.daedalus.managers.guardian.Guardian;
import fr.zelytra.daedalus.managers.structure.StructureType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntranceEvent implements Listener {
    
    @EventHandler
    public void onPlayerEntrance(StructureEntranceEvent e) {
        if (!e.getStructure().hasFirstEntrance() && e.getStructure().getType() == StructureType.TEMPLE) {
            e.getStructure().setFirstEntrance(true);
            for (int x = (int) e.getArea().getMinX(); x <= e.getArea().getMaxX(); x++) {
                for (int y = (int) e.getArea().getMinY(); y <= e.getArea().getMaxY(); y++) {
                    for (int z = (int) e.getArea().getMinZ(); z <= e.getArea().getMaxZ(); z++) {
                        Block bl = Bukkit.getWorld("world").getBlockAt(x, y, z);
                        if (bl.getType() == Material.BEACON) {
                            bl.setType(Material.AIR);
                            new Guardian(bl.getLocation());
                        }
                    }
                }
            }
        }
    }
}
