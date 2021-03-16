package fr.zelytra.daedalus.events.waiting.players;

import net.minecraft.server.v1_16_R3.BlockPosition;
import net.minecraft.server.v1_16_R3.Blocks;
import net.minecraft.server.v1_16_R3.IBlockData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

public class BlockAsync implements Runnable{
    private Block block;

    public BlockAsync(Block block){
        this.block = block;
    }

    @Override
    public void run() {

    }

//    private void setBlock(World world, Location location, net.minecraft.server.v1_16_R3.Block block, boolean applyPhysics) {
//        net.minecraft.server.v1_16_R3.World nmsWorld = ((CraftWorld) world).getHandle();
//        net.minecraft.server.v1_16_R3.Chunk nmsChunk = nmsWorld.getChunkAt(location.getBlockX() >> 4, location.getBlockZ() >> 4);
//        BlockPosition bp = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
//        IBlockData ibd = block.getBlockData();
//        nmsChunk.setType(bp, ibd, applyPhysics);
//    }
}
