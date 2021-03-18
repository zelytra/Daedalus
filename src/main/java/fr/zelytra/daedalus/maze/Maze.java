package fr.zelytra.daedalus.maze;


import fr.zelytra.daedalus.utils.Message;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;


public class Maze {
    private final int[][] maze;
    private int size;
    private final boolean complexity;
    private ArrayList<BoundingBox> land;

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

    public Maze(int size, boolean complexity, ArrayList<BoundingBox> land) {
        this.size = size;
        this.complexity = complexity;
        this.maze = new int[this.size][this.size];
        this.land = land;
    }

    private void generateGrid() {
        //Fill outline with wall
        // Wall = 1| void = 0
        logPlayer("§6§lGenerating grid...");
        if (this.size % 2 == 0) {
            this.size--;
        }
        int nbr = 2;
        int[] line = new int[this.size];
        //Generate line and wall matrix;
        for (int x = 0; x < this.size; x++) {
            if (x % 2 == 1) {
                line[x] = 0;
            } else {
                line[x] = 1;
            }
        }
        for (int x = 0; x < this.size; x++) {
            this.maze[x][0] = 1;
            if (!(x % 2 == 0)) {
                for (int z = 0; z < this.size; z++) {
                    this.maze[z][x] = line[z];
                }
            } else {
                for (int z = 0; z < this.size; z++) {
                    this.maze[z][x] = 1;
                }
            }
        }
        //Fill case with value
        for (int x = 1; x < this.size - 1; x += 2) {
            for (int z = 1; z < this.size - 1; z += 2) {
                if (this.maze[x][z] == 0) {
                    this.maze[x][z] = nbr;
                    nbr++;
                }
            }
        }
        //Generating structure area
        if (land != null && !land.isEmpty()) {
            for (BoundingBox area : land) {
                for (int x = (int) area.getMinX(); x < area.getMaxX(); x++) {
                    for (int z = (int) area.getMinZ(); z < area.getMaxZ(); z++) {
                        this.maze[x][z] = -1;
                    }
                }
                //Generate 4 gates
                int midX = (int) area.getWidthX() / 2;
                int midZ = (int) area.getWidthZ() / 2;
                for (int i = 1; i <= 2; i++) {
                    this.maze[(int) area.getMinX() + midX][(int) area.getMinZ() - i] = -2;
                    this.maze[(int) area.getMinX() - i][(int) area.getMinZ() + midZ] = -2;

                    this.maze[(int) (area.getMaxX() - 1) - midX][(int) (area.getMaxZ() - 1) + i] = -2;
                    this.maze[(int) (area.getMaxX() - 1) + i][(int) (area.getMaxZ() - 1) - midZ] = -2;
                }
            }
        }
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
        for (int i = 1; i < this.size - 1; i++) {
            for (int j = 1; j < this.size - 1; j++) {
                if (this.maze[i][j] == -2) {
                    this.maze[i][j] = 0;
                }
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

    public int[][] getScaleMaze(int scale) {
        generateGrid();
        generateWay();
        return generateScaleMaze(scale);
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
