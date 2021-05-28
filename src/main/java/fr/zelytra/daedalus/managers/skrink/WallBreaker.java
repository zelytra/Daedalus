package fr.zelytra.daedalus.managers.skrink;

import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

@AllArgsConstructor
public class WallBreaker implements Workload {

    private final World world;
    private final int x;
    private final int y;
    private final int z;


    @Override
    public void compute() {
        Block block = world.getBlockAt(x, y, z);

        if (block.getType() == Material.SMOOTH_SANDSTONE) {
            block.setType(Material.AIR);
        }
    }
}
