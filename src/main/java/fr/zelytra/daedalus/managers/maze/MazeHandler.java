package fr.zelytra.daedalus.managers.maze;

import com.sk89q.worldedit.world.block.BlockTypes;
import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.loottable.Loot;
import fr.zelytra.daedalus.managers.loottable.LootTable;
import fr.zelytra.daedalus.managers.structure.GridBlockEnum;
import fr.zelytra.daedalus.managers.structure.Structure;
import fr.zelytra.daedalus.managers.structure.StructureType;
import fr.zelytra.daedalus.managers.structure.WorldEditHandler;
import fr.zelytra.daedalus.utils.Message;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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
                    WEH.setBlock(block, GridBlockEnum.getBlockType(grid[x][z]));
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
                    WEH.setBlock(block, GridBlockEnum.getBlockType(grid[x][z]));
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
                    WEH.setBlock(block, GridBlockEnum.getBlockType(grid[x][z]));
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
        int[][] grid = this.maze.getScaleMaze();
        Daedalus.getInstance().getStructureManager().setMaze(this.maze);
        Location block = origin.clone();

        Bukkit.getScheduler().runTask(Daedalus.getInstance(), () -> {
            /* Generate maze walls */
            Bukkit.broadcastMessage("§6Generating blocks...");
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
                            WEH.setBlock(block, BlockTypes.SMOOTH_SANDSTONE);
                        }
                    }
                    count++;
                    if ((System.currentTimeMillis() - timer) % 100 == 0) {
                        int progress = (int) (count * 100 / (Math.pow(grid.length, 2)));
                        logPlayers("§6§lGenerating blocks... [§e" + progress + "%§6]");
                    }
                }
            }
            WEH.getEditSession().close();

            /* Generate structure schematics */
            Bukkit.broadcastMessage("§6Generating structures...");
            count = 0;
            for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {
                Location location = new Location(origin.getWorld(), entry.getKey().getMinX() + entry.getValue().getOffset().getX(), origin.getY() + entry.getValue().getOffset().getY(), entry.getKey().getMinZ() + entry.getValue().getOffset().getZ());
                WorldEditHandler pasteWE = new WorldEditHandler(location, entry.getValue().getClipboard());
                pasteWE.pasteStructure();

                int progress = ((count * 100) / Daedalus.getInstance().getStructureManager().getStructuresPosition().size());
                logPlayers("§6§lGenerating structures... [§e" + progress + "%§6]");
                count++;
            }

            /* Generate loots */
            count = 0;
            Bukkit.broadcastMessage("§6Generating loots...");
            for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {
                LootTable lootTable = Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(entry.getValue().getName());
                count++;
                if (lootTable == null) {
                    continue;
                }
                World world = Bukkit.getWorld("world");
                for (int x = (int) entry.getKey().getMinX(); x <= entry.getKey().getMaxX(); x++) {
                    for (int y = (int) entry.getKey().getMinY(); y <= entry.getKey().getMaxY(); y++) {
                        for (int z = (int) entry.getKey().getMinZ(); z <= entry.getKey().getMaxZ(); z++) {

                            Block container = world.getBlockAt(x, y, z);
                            if (lootTable.getContainerWhiteList().contains(container.getType())) {

                                ItemStack[] content;

                                switch (container.getType()) {
                                    case CHEST:
                                        Chest chest = (Chest) container.getState();
                                        content = chest.getInventory().getContents();
                                        chest.getInventory().setContents(randomLoot(content, lootTable));

                                        break;
                                    case BARREL:
                                        Barrel barrel = (Barrel) container.getState();
                                        content = barrel.getInventory().getContents();
                                        barrel.getInventory().setContents(randomLoot(content, lootTable));

                                        break;
                                    default:
                                        throw new IllegalStateException("Unexpected value: " + container.getType());
                                }
                            }
                        }
                    }
                }
                int progress = ((count * 100) / Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet().size());
                logPlayers("§6§lGenerating loots... [§e" + progress + "%§6]");
            }
            /* Locating boss spawn area */
            Bukkit.broadcastMessage("§6Locating bosses...");
            count = 0;
            for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {
                if (entry.getValue().getType() == StructureType.TEMPLE) {
                    World world = Bukkit.getWorld("world");

                    for (int x = (int) entry.getKey().getMinX(); x <= entry.getKey().getMaxX(); x++) {
                        for (int y = (int) entry.getKey().getMinY(); y <= entry.getKey().getMaxY(); y++) {
                            for (int z = (int) entry.getKey().getMinZ(); z <= entry.getKey().getMaxZ(); z++) {
                                Block bl = world.getBlockAt(x, y, z);
                                if (bl.getType() == Material.BEACON) {
                                    Bukkit.getScheduler().runTask(Daedalus.getInstance(), () -> {
                                        bl.setType(Material.AIR);
                                        entry.getValue().setBossSpawnLocation(bl.getLocation());
                                    });
                                    break;
                                }
                            }
                        }
                    }
                    count++;
                    int progress = ((count * 100) / GameSettings.GOD_LIMIT);
                    logPlayers("§6§lLocating bosses... [§e" + progress + "%§6]");
                }

            }
            Bukkit.broadcastMessage(Message.getPlayerPrefixe() + "§aMaze generating in " + ((System.currentTimeMillis() - generatingTime) / 1000) % 60 + "s");
        });
    }

    private ItemStack[] randomLoot(ItemStack[] content, LootTable lootTable) {
        for (int i = 0; i <= 8; i++) {
            int slotRandom = (int) (Math.random() * (content.length));
            if (content[slotRandom] != null) {
                continue;
            }
            double random = Math.random();
            for (Loot loot : lootTable.getLoots()) {
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
