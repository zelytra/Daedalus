package fr.zelytra.daedalus.maze;


import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.structure.StructureType;
import fr.zelytra.daedalus.structure.Structure;
import fr.zelytra.daedalus.utils.Message;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockVector;

import java.util.ArrayList;
import java.util.HashMap;


public class Maze {

    private final int[][] maze;
    private int size;
    private final boolean complexity;
    private ArrayList<Structure> land;
    private final int scale = 7;
    private HashMap<BlockVector, Structure> structurePosition;
    private final int spacing = 5;

    /**
     * Maze object
     *
     * @param size       Size of the maze
     * @param complexity True to generate an hard maze
     */
    public Maze(int size, boolean complexity) {
        this.size = size;
        this.complexity = complexity;
        this.maze = new int[this.size][this.size];
    }

    public Maze(int size, boolean complexity, ArrayList<Structure> land) {
        this.structurePosition = new HashMap<>();
        this.size = size;
        this.complexity = complexity;
        this.maze = new int[this.size][this.size];
        this.land = land;
    }

    private void generateGrid() {
        // Fill outline with wall
        // Wall = 1| void = 0
        logPlayer("§6§lGenerating grid...");
        if (this.size % 2 == 0) {
            this.size--;
        }
        int nbr = 2;
        
        //Generate line and wall matrix;
        
        for (int x = 1; x < this.size - 1; x += 2) {
            for (int z = 1; z < this.size - 1; z += 2) {
                this.maze[x][z] = nbr;
                nbr++;
            }
        }
        
        //Generating structure area
        int count = 0;
        for (Structure area : land) {

            int width = (area.getRegion().getWidth() + 1) / (this.scale + 1) + ((area.getRegion().getWidth() + 1) / (this.scale + 1)) - 1;
            int length = (area.getRegion().getLength() + 1) / (this.scale + 1) + ((area.getRegion().getLength() + 1) / (this.scale + 1)) - 1;
            int originX = 0;
            int originZ = 0;
            boolean structureAround = true;

            if (area.getType() == StructureType.FIXED) {
                originX = (int) (area.getOrigin().getX() - (width / 2.0));
                originZ = (int) (area.getOrigin().getZ() - (length / 2.0));
            } else {
                int security = 0;
                while (structureAround) {
                    //Random position selector
                    originX = 3 + ((int) (Math.random() * (this.size - 8 - width) / 2)) * 2;
                    originZ = 3 + ((int) (Math.random() * (this.size - 8 - length) / 2)) * 2;
                    //Check structures around
                    structureAround = false;
                    for (int x = originX - this.spacing < 0 ? 0 : originX - this.spacing; x < (originX + this.spacing + width > this.size ? this.size : originX + width + this.spacing); x++) {
                        for (int z = originZ - this.spacing < 0 ? 0 : originZ - this.spacing; z < (originZ + this.spacing + length > this.size ? this.size : originZ + length + this.spacing); z++) {
                            if (this.maze[x][z] == -1) {
                                structureAround = true;
                                break;
                            }
                        }
                    }
                    security++;
                    if (security >= 10000) {
                        Bukkit.broadcastMessage(Message.getPlayerPrefixe() + "§c" + area.getName() + " random placement has time out. §l[SKIPPING]");
                        break;
                    }
                }
            }
            BlockVector areaBox = new BlockVector((originX / 2) * (this.scale + 1) + originX % 2, 0, (originZ / 2) * (this.scale + 1) + originZ % 2);
            this.structurePosition.put(areaBox, area);
            for (int x = originX; x < (originX + width); x++) {
                for (int z = originZ; z < (originZ + length); z++) {
                    this.maze[x][z] = -1;
                }
            }
            int midX = width / 2;
            int midZ = length / 2;
            for (int i = 1; i <= 2; i++) {
                this.maze[originX + midX][originZ - i] = nbr++;
                this.maze[originX - i][originZ + midZ] = nbr++;

                this.maze[(originX + width - 1) - midX][(originZ + length - 1) + i] = nbr++;
                this.maze[(originX + width - 1) + i][(originZ + length - 1) - midZ] = nbr++;
            }

            int progress = (count * 100) / land.size();
            logPlayer("§6§lLocking structures area... [§e" + progress + "%§6]");
            count++;

        }
        Daedalus.getInstance().getGameManager().getStructureManager().setStructuresPosition(structurePosition);
    }

    private void generateWay() {
        boolean isGenerated = false;
        int count = 0;
        long time = System.currentTimeMillis();
        while (!isGenerated) {
            int x = 1 + (int) (Math.random() * (this.size - 2));
            int z;
            if (x % 2 != 0) {
                z = 1 + ((int) (Math.random() * (this.size - 4) / 2)) * 2 + 1;
            } else {
                z = 1 + ((int) (Math.random() * (this.size - 2) / 2)) * 2;
            }

            int cell1;
            int cell2;

            if (this.maze[x - 1][z] == 1) {
                cell1 = this.maze[x][z - 1];
                cell2 = this.maze[x][z + 1];
            } else {
                cell1 = this.maze[x - 1][z];
                cell2 = this.maze[x + 1][z];
            }
            if (cell1 <= -1 || cell2 <= -1 || this.maze[x][z] <= -1) {
                continue;
            }
            if (cell1 != cell2) {
                this.maze[x][z] = cell1;

                for (int i = 1; i < this.size - 1; i += 2) {
                    for (int j = 1; j < this.size - 1; j += 2) {
                        if (this.maze[i][j] == cell2) {
                            this.maze[i][j] = cell1;
                        }
                    }
                }
            }

            isGenerated = true;
            int progress = 0;
            for (int i = 1; i < this.size - 1; i += 2) {
                for (int j = 1; j < this.size - 1; j += 2) {
                    if (this.maze[i][j] == -1) {
                        continue;
                    }
                    if (this.maze[1][1] != this.maze[i][j]) {
                        isGenerated = false;
                        progress++;
                    }
                }
            }

            progress = (int) ((((Math.pow(this.size - 1, 2) / 4.0) - progress) * 100) / (Math.pow(this.size - 1, 2) / 4.0));
            logPlayer("§6§lGenerating maze... [§e" + progress + "%§6]");
            count++;
            if (count > 100000000) {
                System.out.println("Generation time out");
                Bukkit.broadcastMessage(Message.getPlayerPrefixe() + "§cERROR Generation Timeout");
                return;
            }
        }
        if (complexity) {
            for (int i = 0; i < this.size; i++) {
                boolean isStructure = false;
                do {
                    isStructure = false;
                    int x = 1 + (int) (Math.random() * (this.size - 2));
                    int z;
                    if (x % 2 != 0) {
                        z = 1 + ((int) (Math.random() * (this.size - 4) / 2)) * 2 + 1;
                    } else {
                        z = 1 + ((int) (Math.random() * (this.size - 2) / 2)) * 2;
                    }

                    for (int a = x - 1; a <= x + 1; a++) {
                        for (int b = z - 1; b <= z + 1; b++) {
                            if (this.maze[a][b] == -1) {
                                isStructure = true;
                                break;
                            }
                        }
                    }
                    if (this.maze[x][z] == 1 && !isStructure) {
                        this.maze[x][z] = 0;
                    }
                } while (isStructure);

            }
        }

        Bukkit.broadcastMessage(Message.getPlayerPrefixe() + "§aMaze generated in " + (System.currentTimeMillis() - time) + "ms");
    }

    //Thanks Nicolas61x
    private int[][] generateScaleMaze(int scale) {
        int X = 0;
        logPlayer("§6§lScaling maze...");
        for (int i = 0; i < this.size; i++) {
            if (i % 2 == 0) {
                X++;
            } else {
                X += scale;
            }
        }
        int[][] maze2 = new int[X][X];

        int zPtr = 0;

        for (int z = 0; z < this.size; z++) {

            int xPtr = 0;
            int zScale = z % 2 == 0 ? 1 : scale;

            for (int x = 0; x < this.size; x++) {
                int xScale = x % 2 == 0 ? 1 : scale;

                for (int sz = 0; sz < zScale; sz++) {
                    for (int sx = 0; sx < xScale; sx++) {
                        maze2[xPtr + sx][zPtr + sz] = this.maze[x][z];
                    }
                }
                xPtr += xScale;
            }
            zPtr += zScale;
        }

        return maze2;
    }

    public int[][] getGrid() {
        generateGrid();
        return this.maze;

    }

    public int[][] getMaze() {
        generateGrid();
        generateWay();
        return this.maze;

    }

    public int[][] getScaleMaze() {
        generateGrid();
        generateWay();
        return generateScaleMaze(this.scale);
    }

    public int getSize() {
        return this.size;
    }

    private void logPlayer(String msg) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            BaseComponent txt = new TextComponent(msg);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, txt);
        }
    }

}
