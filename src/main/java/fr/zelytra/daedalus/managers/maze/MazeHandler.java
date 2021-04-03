package fr.zelytra.daedalus.managers.maze;

import com.sk89q.worldedit.world.block.BlockTypes;
import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.loottable.LootTable;
import fr.zelytra.daedalus.managers.loottable.LootsEnum;
import fr.zelytra.daedalus.managers.structure.Structure;
import fr.zelytra.daedalus.managers.structure.WorldEditHandler;
import fr.zelytra.daedalus.utils.Message;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Barrel;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.Map;

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

    public void demoGenerateGrid() {
        Location origin = getOrigin(this.maze.getSize());
        this.maze.setOrigin(origin);
        int[][] grid = this.maze.getGrid();
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
        Location origin = getOrigin(this.maze.getSize());
        this.maze.setOrigin(origin);
        int[][] grid = this.maze.getWay();
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
        Location origin = getOrigin(((this.maze.getSize() * this.maze.getScale() - 1 * this.maze.getScale()) / 2) + ((this.maze.getSize() - 1) / 2) + 1);
        this.maze.setOrigin(origin);
        int[][] grid = this.maze.getScaleMaze();
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
        final long generatingTime = System.currentTimeMillis();

        Location origin = getOrigin(((this.maze.getSize() * this.maze.getScale() - 1 * this.maze.getScale()) / 2) + ((this.maze.getSize() - 1) / 2) + 1);
        this.maze.setOrigin(origin);
        final int waiting = 5;
        int[][] grid = this.maze.getScaleMaze();
        Daedalus.getInstance().getStructureManager().setMaze(this.maze);
        Location block = origin.clone();

        Bukkit.getScheduler().runTask(Daedalus.getInstance(), () -> {
            //Generate maze walls
            Bukkit.broadcastMessage("§6§lGenerating blocks...");
            WorldEditHandler WEH = new WorldEditHandler(block.getWorld());
            int count = 0;
            long timer = System.currentTimeMillis();
            for (int x = 0; x < grid.length; x++) {
                for (int z = 0; z < grid.length; z++) {
                    block.setX(origin.getX() + x);
                    block.setZ(origin.getZ() + z);
                    if (grid[x][z] == 1) {
                        for (int y = (int) origin.getY(); y < origin.getY() + this.wallHeight; y++) {
                            block.setY(y);
                            count++;
                            WEH.setBlock(block, BlockTypes.SMOOTH_SANDSTONE);
                        }
                    }
                }
                if ((System.currentTimeMillis() - timer) % 3000 == 0) {
                    int progress = (int) (count * 100 / (Math.pow(grid.length, 2)));
                    logPlayers("§6§lGenerating blocks... [§e" + progress + "%§6]");
                }
            }
            WEH.getEditSession().close();

            //Generate structure schematics
            Bukkit.broadcastMessage("§6§lGenerating structures...");
            count = 0;
            for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {
                Location location = new Location(origin.getWorld(), entry.getKey().getMinX() + entry.getValue().getOffset().getX(), origin.getY() + entry.getValue().getOffset().getY(), entry.getKey().getMinZ() + entry.getValue().getOffset().getZ());
                WorldEditHandler pasteWE = new WorldEditHandler(location, entry.getValue().getClipboard());
                pasteWE.pasteStructure();

                int progress = (int) ((count * 100) / Daedalus.getInstance().getStructureManager().getStructuresPosition().size());
                logPlayers("§6§lGenerating structures... [§e" + progress + "%§6]");
                count++;
            }

            //Generate loots
            count = 0;
            timer = System.currentTimeMillis();
            Bukkit.broadcastMessage("§6§lGenerating loots...");
            for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {
                LootTable lootTable = Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(entry.getValue().getName());
                if (lootTable == null) {
                    continue;
                }
                for (int x = (int) entry.getKey().getMinX(); x <= entry.getKey().getMaxX(); x++) {
                    for (int y = (int) entry.getKey().getMinY(); y <= entry.getKey().getMaxY(); y++) {
                        for (int z = (int) entry.getKey().getMinZ(); z <= entry.getKey().getMaxZ(); z++) {

                            Block container = Bukkit.getWorld("world").getBlockAt(x, y, z);
                            if (lootTable.getContainerWhiteList().contains(container.getType())) {

                                ItemStack[] content;
                                Chest chest = null;
                                Barrel barrel = null;

                                switch (container.getType()) {
                                    case CHEST:
                                        chest = (Chest) container.getState();
                                        content = chest.getInventory().getContents();
                                        chest.getInventory().setContents(randomLoot(content, lootTable));
                                        chest.update();
                                        break;
                                    case BARREL:
                                        barrel = (Barrel) container.getState();
                                        content = barrel.getInventory().getContents();
                                        barrel.getInventory().setContents(randomLoot(content, lootTable));
                                        barrel.update();
                                        break;
                                    default:
                                        throw new IllegalStateException("Unexpected value: " + container.getType());
                                }
                            }
                        }
                    }
                }
                if ((System.currentTimeMillis() - timer) % 1000 == 0) {
                    int progress = (int) ((count * 100) / Daedalus.getInstance().getStructureManager().getStructuresPosition().size());
                    logPlayers("§6§lGenerating loots... [§e" + progress + "%§6]");
                }
                count++;
            }
            Bukkit.broadcastMessage(Message.getPlayerPrefixe() + "§aMaze generating in " + ((System.currentTimeMillis() - generatingTime) / 1000) % 60 + "s");
        });
    }

    private ItemStack[] randomLoot(ItemStack[] content, LootTable lootTable) {
        for (int i = 0; i <= 16; i++) {
            int slotRandom = (int) (Math.random() * (content.length));
            if (content[slotRandom] != null) {
                continue;
            }
            double random = Math.random();
            for (LootsEnum loot : lootTable.getLoots()) {
                if (loot.getLuck() > random) {
                    content[slotRandom] = loot.getItem();
                    break;
                }
                random -= loot.getLuck();
            }

        }
        return content;
    }


    private void logPlayers(String msg) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            BaseComponent txt = new TextComponent(msg);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, txt);
        }

    }

    private Location getOrigin(int gridSize) {
        return new Location(center.getWorld(), center.getX() - (gridSize / 2.0) + 1, center.getY(), center.getZ() - (gridSize / 2.0) + 1);
    }

}
