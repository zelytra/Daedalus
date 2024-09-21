package fr.zelytra.daedalus.managers.skrink;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class WallBreaker implements Workload {

  private final World world;
  private final int x;
  private final int y;
  private final int z;
  @Getter private boolean marker = false;

  public WallBreaker(World world, int x, int y, int z) {
    this.world = world;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public void compute() {
    if (x == 0 && z == 0) {
      marker = true;
      return;
    }

    Block block = world.getBlockAt(x, y, z);
    block.setType(Material.AIR);
  }
}
