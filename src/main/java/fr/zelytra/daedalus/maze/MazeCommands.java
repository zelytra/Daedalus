package fr.zelytra.daedalus.maze;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MazeCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println(Message.getConsolePrefixe() + "You cannot execute this command.");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 2 && args[0].equalsIgnoreCase("generate")) {

            player.sendMessage(Message.getPlayerPrefixe() + " §cStarting generation...");
            Maze maze = new Maze(Integer.parseInt(args[1]), true);
            int[][] m = maze.getMaze();
            Location location = player.getLocation();

            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.plugin, new Runnable() {
                @Override
                public void run() {
                    for (int x = 0; x < maze.getSize(); x++) {
                        for (int z = 0; z < maze.getSize(); z++) {
                            location.setX(player.getLocation().getX()+x);
                            location.setZ(player.getLocation().getZ()+z);
                            if (m[x][z] == 1) {
                                location.getBlock().setType(Material.BLACK_CONCRETE);
                            } else {
                                location.getBlock().setType(Material.WHITE_CONCRETE);
                            }
                        }
                    }
                }
            });
            return true;
        }

        return false;
    }

    private static void setBlockInNativeChunkSection(World world, int x, int y, int z, int blockId, byte data) {

        net.minecraft.server.v1_14_R1.World nmsWorld = ((CraftWorld) world).getHandle();
        net.minecraft.server.v1_14_R1.Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
        IBlockData ibd = net.minecraft.server.v1_14_R1.Block.getByCombinedId(blockId + (data << 12));

        ChunkSection cs = nmsChunk.getSections()[y >> 4];
        if (cs == nmsChunk.a()) {
            cs = new ChunkSection(y >> 4 << 4);
            nmsChunk.getSections()[y >> 4] = cs;
        }
        cs.setType(x & 15, y & 15, z & 15, ibd);
    }
}
