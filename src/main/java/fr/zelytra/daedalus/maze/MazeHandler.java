package fr.zelytra.daedalus.maze;

import fr.zelytra.daedalus.Daedalus;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;

public class MazeHandler {
    private final Location center;
    private final Maze maze;
    private int scale;
    private int wallHeight;

    /**
     * @param location   center of the maze
     * @param size       Size of the maze
     * @param complexity True if complex maze
     * @param land       List of structure to generate
     */
    public MazeHandler(Location location, int size, boolean complexity, ArrayList<BoundingBox> land) {
        this.center = location;
        this.maze = new Maze(size, complexity, land);
    }

    /**
     * @param location   center of the maze
     * @param size       Size of the maze
     * @param complexity True if complex maze
     */
    public MazeHandler(Location location, int size, boolean complexity) {
        this.center = location;
        this.maze = new Maze(size, complexity);
    }

    /**
     * @param location   center of the maze
     * @param size       Size of the maze
     * @param complexity True if complex maze
     * @param scale      Scale of corridors
     */
    public MazeHandler(Location location, int size, boolean complexity, int scale, int wallHeight) {
        this.center = location;
        this.maze = new Maze(size, complexity);
        this.scale = scale;
        this.wallHeight = wallHeight;
    }

    /**
     * @param location   center of the maze
     * @param size       Size of the maze
     * @param complexity True if complex maze
     * @param scale      Scale of corridors
     * @param land       List of structure to generate
     */
    public MazeHandler(Location location, int size, boolean complexity, int scale, int wallHeight, ArrayList<BoundingBox> land) {
        this.center = location;
        this.maze = new Maze(size, complexity,land);
        this.scale = scale;
        this.wallHeight = wallHeight;
    }

    public void demoGenerateGrid() {
        int[][] grid = this.maze.getGrid();

        Location origin = new Location(center.getWorld(), center.getX() - (this.maze.getSize() / 2.0), center.getY(), center.getZ() - (this.maze.getSize() / 2.0));
        Location block = origin.clone();
        Bukkit.getScheduler().runTask(Daedalus.plugin, () -> {
            int count = 0;
            for (int x = 0; x < this.maze.getSize(); x++) {
                for (int z = 0; z < this.maze.getSize(); z++) {
                    block.setX(origin.getX() + x);
                    block.setZ(origin.getZ() + z);
                    if (grid[x][z] == 1) {
                        block.getBlock().setType(Material.BLACK_CONCRETE);
                    } else {
                        block.getBlock().setType(Material.WHITE_CONCRETE);
                    }
                    count++;
                    int progress = (int) (count * 100 / (Math.pow(maze.getSize(), 2)));
                    logPlayers("§6§lGenerating blocks... [§e" + progress + "%§6]");

                }
            }
        });
    }

    public void demoGenerateMaze() {
        int[][] grid = this.maze.getMaze();
        Location origin = new Location(center.getWorld(), center.getX() - (this.maze.getSize() / 2.0), center.getY(), center.getZ() - (this.maze.getSize() / 2.0));
        Location block = origin.clone();
        Bukkit.getScheduler().runTask(Daedalus.plugin, () -> {
            int count = 0;
            for (int x = 0; x < this.maze.getSize(); x++) {
                for (int z = 0; z < this.maze.getSize(); z++) {
                    block.setX(origin.getX() + x);
                    block.setZ(origin.getZ() + z);
                    if (grid[x][z] == 1) {
                        block.getBlock().setType(Material.BLACK_CONCRETE);
                    } else if (grid[x][z] == -1) {
                        block.getBlock().setType(Material.RED_CONCRETE);
                    } else {
                        block.getBlock().setType(Material.WHITE_CONCRETE);
                    }
                    count++;
                    int progress = (int) (count * 100 / (Math.pow(maze.getSize(), 2)));
                    logPlayers("§6§lGenerating blocks... [§e" + progress + "%§6]");
                }
            }
        });
    }

    public void demoGenerateScaleMaze() {
        int[][] grid = this.maze.getScaleMaze(this.scale);
        Location origin = new Location(center.getWorld(), center.getX() - (grid.length / 2.0), center.getY(), center.getZ() - (grid.length / 2.0));
        Location block = origin.clone();
        Bukkit.getScheduler().runTask(Daedalus.plugin, () -> {
            int count = 0;
            for (int x = 0; x < grid.length; x++) {
                for (int z = 0; z < grid.length; z++) {
                    block.setX(origin.getX() + x);
                    block.setZ(origin.getZ() + z);
                    if (grid[x][z] == 1) {
                        block.getBlock().setType(Material.BLACK_CONCRETE);
                    } else if (grid[x][z] == -1) {
                        block.getBlock().setType(Material.RED_CONCRETE);
                    } else {
                        block.getBlock().setType(Material.WHITE_CONCRETE);
                    }
                    count++;
                    int progress = (int) (count * 100 / (Math.pow(grid.length, 2)));
                    logPlayers("§6§lGenerating blocks... [§e" + progress + "%§6]");
                }
            }
        });
    }

    public void generateScaleMaze() {
        int[][] grid = this.maze.getScaleMaze(this.scale);
        Location origin = new Location(center.getWorld(), center.getX() - (grid.length / 2.0), center.getY(), center.getZ() - (grid.length / 2.0));
        Location block = origin.clone();
        Bukkit.getScheduler().runTask(Daedalus.plugin, () -> {
            int count = 0;
            for (int x = 0; x < grid.length; x++) {
                for (int z = 0; z < grid.length; z++) {
                    block.setX(origin.getX() + x);
                    block.setZ(origin.getZ() + z);
                    if (grid[x][z] == 1) {
                        for (int y = (int) origin.getY(); y < origin.getY() + this.wallHeight; y++) {
                            block.setY(y);
                            block.getBlock().setType(Material.STONE_BRICKS);
                        }
                    }
                    count++;
                    int progress = (int) (count * 100 / (Math.pow(grid.length, 2)));
                    logPlayers("§6§lGenerating blocks... [§e" + progress + "%§6]");
                }
            }
        });
    }


    private void logPlayers(String msg) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            BaseComponent txt = new TextComponent(msg);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, txt);
        }

    }
}

