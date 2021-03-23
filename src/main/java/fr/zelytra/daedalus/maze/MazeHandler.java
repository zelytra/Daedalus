package fr.zelytra.daedalus.maze;

import com.sk89q.worldedit.world.block.BlockTypes;
import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.structure.Structure;
import fr.zelytra.daedalus.structure.WorldEditHandler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MazeHandler {
    private final Location center;
    private final Maze maze;
    private int wallHeight = 20;

    /**
     * @param location   center of the maze
     * @param size       Size of the maze
     * @param complexity True if complex maze
     * @param land       List of structure to generate
     */
    public MazeHandler(Location location, int size, boolean complexity, ArrayList<Structure> land) {
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
     * @param land       List of structure to generate
     */
    public MazeHandler(Location location, int size, boolean complexity, int wallHeight, ArrayList<Structure> land) {
        this.center = location;
        this.maze = new Maze(size, complexity, land);
        this.wallHeight = wallHeight;
    }

    public void demoGenerateGrid() {
        int[][] grid = this.maze.getGrid();

        Location origin = new Location(center.getWorld(), center.getX() - (this.maze.getSize() / 2.0), center.getY(), center.getZ() - (this.maze.getSize() / 2.0));
        Location block = origin.clone();
        Bukkit.getScheduler().runTask(Daedalus.getInstance(), () -> {
            WorldEditHandler WEH = new WorldEditHandler(block.getWorld());
            int count = 0;
            for (int x = 0; x < this.maze.getSize(); x++) {
                for (int z = 0; z < this.maze.getSize(); z++) {
                    block.setX(origin.getX() + x);
                    block.setZ(origin.getZ() + z);
                    if (grid[x][z] == 1) {
                        WEH.setBlock(block, BlockTypes.BLACK_CONCRETE);
                    } else if (grid[x][z] == -1) {
                        WEH.setBlock(block, BlockTypes.RED_CONCRETE);
                    } else {
                        WEH.setBlock(block, BlockTypes.WHITE_CONCRETE);
                    }
                    count++;
                    int progress = (int) (count * 100 / (Math.pow(maze.getSize(), 2)));
                    logPlayers("§6§lGenerating blocks... [§e" + progress + "%§6]");

                }
            }
            WEH.getEditSession().close();
        });
    }

    public void demoGenerateMaze() {
        int[][] grid = this.maze.getMaze();
        Location origin = new Location(center.getWorld(), center.getX() - (this.maze.getSize() / 2.0), center.getY(), center.getZ() - (this.maze.getSize() / 2.0));
        Location block = origin.clone();
        Bukkit.getScheduler().runTask(Daedalus.getInstance(), () -> {
            WorldEditHandler WEH = new WorldEditHandler(block.getWorld());
            int count = 0;
            for (int x = 0; x < this.maze.getSize(); x++) {
                for (int z = 0; z < this.maze.getSize(); z++) {
                    block.setX(origin.getX() + x);
                    block.setZ(origin.getZ() + z);
                    if (grid[x][z] == 1) {
                        WEH.setBlock(block, BlockTypes.BLACK_CONCRETE);
                    } else if (grid[x][z] == -1) {
                        WEH.setBlock(block, BlockTypes.RED_CONCRETE);
                    } else {
                        WEH.setBlock(block, BlockTypes.WHITE_CONCRETE);
                    }
                    count++;
                    int progress = (int) (count * 100 / (Math.pow(maze.getSize(), 2)));
                    logPlayers("§6§lGenerating blocks... [§e" + progress + "%§6]");
                }
            }
            WEH.getEditSession().close();
        });
    }

    public void demoGenerateScaleMaze() {
        int[][] grid = this.maze.getScaleMaze();
        Location origin = new Location(center.getWorld(), center.getX() - (grid.length / 2.0), center.getY(), center.getZ() - (grid.length / 2.0));
        Location block = origin.clone();
        Bukkit.getScheduler().runTask(Daedalus.getInstance(), () -> {
            WorldEditHandler WEH = new WorldEditHandler(block.getWorld());
            int count = 0;
            for (int x = 0; x < grid.length; x++) {
                for (int z = 0; z < grid.length; z++) {
                    block.setX(origin.getX() + x);
                    block.setZ(origin.getZ() + z);
                    if (grid[x][z] == 1) {
                        WEH.setBlock(block, BlockTypes.BLACK_CONCRETE);
                    } else if (grid[x][z] == -1) {
                        WEH.setBlock(block, BlockTypes.RED_CONCRETE);
                    } else {
                        WEH.setBlock(block, BlockTypes.WHITE_CONCRETE);
                    }
                    count++;
                    int progress = (int) (count * 100 / (Math.pow(grid.length, 2)));
                    logPlayers("§6§lGenerating blocks... [§e" + progress + "%§6]");
                }
            }
            WEH.getEditSession().close();
        });
    }

    public void generateScaleMaze() {
        int[][] grid = this.maze.getScaleMaze();
        Location origin = new Location(center.getWorld(), center.getX() - (grid.length / 2.0), center.getY(), center.getZ() - (grid.length / 2.0));
        Location block = origin.clone();
        Bukkit.getScheduler().runTask(Daedalus.getInstance(), () -> {
            WorldEditHandler WEH = new WorldEditHandler(block.getWorld());
            int count = 0;

            for (int x = 0; x < grid.length; x++) {
                for (int z = 0; z < grid.length; z++) {
                    block.setX(origin.getX() + x);
                    block.setZ(origin.getZ() + z);
                    if (grid[x][z] == 1) {
                        for (int y = (int) origin.getY(); y < origin.getY() + this.wallHeight; y++) {
                            block.setY(y);
                            WEH.setBlock(block, BlockTypes.SMOOTH_SANDSTONE);
                        }
                    }
                    count++;
                    int progress = (int) (count * 100 / (Math.pow(grid.length, 2)));
                    logPlayers("§6§lGenerating blocks... [§e" + progress + "%§6]");
                }
            }
            WEH.getEditSession().close();
        });
    }


    private void logPlayers(String msg) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            BaseComponent txt = new TextComponent(msg);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, txt);
        }

    }
}

