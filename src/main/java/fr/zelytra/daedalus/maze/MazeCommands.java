package fr.zelytra.daedalus.maze;


import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.utils.Message;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MazeCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println(Message.getConsolePrefixe() + "You cannot execute this command.");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 2 && args[0].equalsIgnoreCase("generate")) {
            player.sendMessage(Message.getPlayerPrefixe() + "§cStarting generation...");
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.plugin, () -> {
                Maze maze = new Maze(Integer.parseInt(args[1]), true, player);
                int[][] m = maze.getMaze();
                Location location = player.getLocation();
                location.setY(location.getY() - 1);
                Bukkit.getScheduler().runTask(Daedalus.plugin, () -> {
                    int count = 0;
                    for (int x = 0; x < maze.getSize(); x++) {
                        for (int z = 0; z < maze.getSize(); z++) {
                            location.setX(player.getLocation().getX() + x);
                            location.setZ(player.getLocation().getZ() + z);

                            if (m[x][z] == 1) {
                                location.getBlock().setType(Material.BLACK_CONCRETE);
                            } else {
                                location.getBlock().setType(Material.WHITE_CONCRETE);
                            }
                            count++;
                            int progress = (int) (count * 100 / (Math.pow(maze.getSize(), 2)));
                            logPlayer(player, "§6§lGenerating blocks... [§e" + progress + "%§6]");
                        }
                    }
                });
            });

            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("landGenerate")) {
            player.sendMessage(Message.getPlayerPrefixe() + "§cStarting generation...");
            Bukkit.getScheduler().runTaskAsynchronously(Daedalus.plugin, () -> {
                ArrayList<BoundingBox> land = new ArrayList<>();
                land.add(new BoundingBox(11.0, player.getLocation().getY(), 11.0, 22.0, player.getLocation().getY(), 18.0));
                land.add(new BoundingBox(21.0, player.getLocation().getY(), 21.0, 32.0, player.getLocation().getY(), 36.0));
                Maze maze = new Maze(Integer.parseInt(args[1]), true, player,land);
                int[][] m = maze.getMaze();
                Location location = player.getLocation();
                location.setY(location.getY() - 1);
                Bukkit.getScheduler().runTask(Daedalus.plugin, () -> {
                    int count = 0;
                    for (int x = 0; x < maze.getSize(); x++) {
                        for (int z = 0; z < maze.getSize(); z++) {
                            location.setX(player.getLocation().getX() + x);
                            location.setZ(player.getLocation().getZ() + z);

                            if (m[x][z] == 1) {
                                location.getBlock().setType(Material.BLACK_CONCRETE);
                            }else if (m[x][z] == -1) {
                                location.getBlock().setType(Material.RED_CONCRETE);
                            }else if (m[x][z] == -2) {
                                location.getBlock().setType(Material.YELLOW_CONCRETE);
                            } else {
                                location.getBlock().setType(Material.WHITE_CONCRETE);
                            }
                            count++;
                            int progress = (int) (count * 100 / (Math.pow(maze.getSize(), 2)));
                            logPlayer(player, "§6§lGenerating blocks... [§e" + progress + "%§6]");
                        }
                    }
                });
            });

            return true;
        }

        return false;
    }

    private static void setBlockInNativeChunkSection(World world, int x, int y, int z, int blockId, byte data) {

        net.minecraft.server.v1_16_R3.World nmsWorld = ((CraftWorld) world).getHandle();
        net.minecraft.server.v1_16_R3.Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
        net.minecraft.server.v1_16_R3.IBlockData ibd = net.minecraft.server.v1_16_R3.Block.getByCombinedId(blockId + (data << 12));

        net.minecraft.server.v1_16_R3.ChunkSection cs = nmsChunk.getSections()[y >> 4];
        if (cs == nmsChunk.a()) {
            cs = new net.minecraft.server.v1_16_R3.ChunkSection(y >> 4 << 4);
            nmsChunk.getSections()[y >> 4] = cs;
        }
        cs.setType(x & 15, y & 15, z & 15, ibd);
    }

    private void logPlayer(Player player, String msg) {
        if (player != null) {
            BaseComponent txt = new TextComponent(msg);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, txt);
        }
    }
}
