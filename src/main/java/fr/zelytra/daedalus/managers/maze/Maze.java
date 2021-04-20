package fr.zelytra.daedalus.managers.maze;


import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.structure.Structure;
import fr.zelytra.daedalus.managers.structure.StructureType;
import fr.zelytra.daedalus.utils.Message;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.HashMap;


public class Maze {

    private final int[][] maze;
    private int[][] scaleMaze;
    private int size;
    private final boolean complexity;
    private ArrayList<Structure> land;
    private final int scale = 7;
    private HashMap<BoundingBox, Structure> structurePosition;
    private final int spacing = 5;
    private Location origin;
    private int nbr = 2;

    /**
     * Maze object
     *
     * @param size       Size of the maze
     * @param complexity True to generate an hard maze
     */
    public Maze(int size, boolean complexity) {
        this.size = size;
        if (this.size % 2 == 0) {
            this.size--;
        }
        this.complexity = complexity;
        this.maze = new int[this.size][this.size];
    }

    public Maze(int size, boolean complexity, ArrayList<Structure> land) {
        this.structurePosition = new HashMap<>();
        this.size = size;
        if (this.size % 2 == 0) {
            this.size--;
        }
        this.complexity = complexity;
        this.maze = new int[this.size][this.size];
        this.land = land;
    }

    private void generateGrid() {
        // Fill outline with wall
        // Wall = 1| void = 0
        Bukkit.broadcastMessage("§6§lGenerating grid...");

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

        Bukkit.broadcastMessage("§6§lLocking structures area... ");
        for (Structure area : land) {

            int width = (area.getRegion().getWidth() + 1) / (this.scale + 1) + ((area.getRegion().getWidth() + 1) / (this.scale + 1)) - 1;
            int length = (area.getRegion().getLength() + 1) / (this.scale + 1) + ((area.getRegion().getLength() + 1) / (this.scale + 1)) - 1;
            int originX;
            int originZ;
            if (area.getType() == StructureType.FIXED) {
                originX = (int) (area.getOrigin().getX() - (width / 2.0));
                originZ = (int) (area.getOrigin().getZ() - (length / 2.0));
                generateStructureGrid(new Vector2(originX, originZ), area, width, length);
                //Surrounded Structure
                for (Structure surroundedStruct : Daedalus.getInstance().getStructureManager().structureSurrounded.getStructures()) {
                    int sWidth = (surroundedStruct.getRegion().getWidth() + 1) / (this.scale + 1) + ((surroundedStruct.getRegion().getWidth() + 1) / (this.scale + 1)) - 1;
                    int sLength = (surroundedStruct.getRegion().getLength() + 1) / (this.scale + 1) + ((surroundedStruct.getRegion().getLength() + 1) / (this.scale + 1)) - 1;
                    Vector2 coordinate = getRandomStructurePosition(sWidth, sLength, this.spacing, new Vector2(originX, originZ), Daedalus.getInstance().getStructureManager().structureSurrounded.getAreaSize());
                    if (coordinate == null) {
                        continue;
                    }
                    generateStructureGrid(coordinate, surroundedStruct, sWidth, sLength);
                }

            } else {
                Vector2 coordinate = getRandomStructurePosition(width, length, this.spacing, new Vector2(this.size / 2, this.size / 2), this.size);
                if (coordinate == null) {
                    continue;
                }
                originX = coordinate.x;
                originZ = coordinate.z;
                generateStructureGrid(new Vector2(originX, originZ), area, width, length);
            }

        }
        Daedalus.getInstance().getStructureManager().setStructuresPosition(structurePosition);
    }

    private void generateWay() {
        long time = System.currentTimeMillis();
        int WallsPerLine = (this.size / 2); //get walls per line
        int WallsCount = (WallsPerLine * (WallsPerLine - 1)) * 2;//walls in a square area of size x size

        Vector2[] Murs = new Vector2[WallsCount];

        int idx = 0;

        for (int z = 1; z < this.size; z += 2) {
            for (int x = 2; x < this.size - 1; x += 2) {
                Murs[idx] = new Vector2(x, z);
                idx++;
            }
            if (z + 1 != this.size - 1) {
                for (int x = 1; x < this.size; x += 2) {
                    Murs[idx] = new Vector2(x, z + 1);
                    idx++;
                }
            }
        }
        long timer = System.currentTimeMillis();
        Bukkit.broadcastMessage("§6§lGenerating maze...");
        while (idx > 0) {

            int pos = (int) (Math.random() * (idx));

            int x = Murs[pos].x;
            int z = Murs[pos].z;

            Murs[pos] = Murs[idx - 1];

            idx--;

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
            int progress = 0;
            for (int i = 1; i < this.size - 1; i += 2) {
                for (int j = 1; j < this.size - 1; j += 2) {
                    if (this.maze[i][j] <= -1) {
                        continue;
                    }
                    if (this.maze[1][1] != this.maze[i][j]) {
                        progress++;
                    }
                }
            }
            if ((System.currentTimeMillis() - timer) % 500 == 0) {
                progress = (int) ((((Math.pow(this.size - 1, 2) / 4.0) - progress) * 100) / (Math.pow(this.size - 1, 2) / 4.0));
                logPlayer("§6§lGenerating maze... [§e" + progress + "%§6]");
            }
        }
        if (complexity) {
            for (int i = 0; i < this.size; i++) {
                boolean isStructure;
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
                            if (this.maze[a][b] <= -1) {
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

        Bukkit.broadcastMessage("§6§lMaze generated in " + (System.currentTimeMillis() - time) + "ms");
    }

    //Thanks Nicolas61x
    private int[][] generateScaleMaze(int scale) {
        int X = 0;
        Bukkit.broadcastMessage("§6§lScaling maze...");
        for (int i = 0; i < this.size; i++) {
            if (i % 2 == 0) {
                X++;
            } else {
                X += scale;
            }
        }
        this.scaleMaze = new int[X][X];

        int zPtr = 0;

        for (int z = 0; z < this.size; z++) {

            int xPtr = 0;
            int zScale = z % 2 == 0 ? 1 : scale;

            for (int x = 0; x < this.size; x++) {
                int xScale = x % 2 == 0 ? 1 : scale;

                for (int sz = 0; sz < zScale; sz++) {
                    for (int sx = 0; sx < xScale; sx++) {
                        this.scaleMaze[xPtr + sx][zPtr + sz] = this.maze[x][z];
                    }
                }
                xPtr += xScale;
            }
            zPtr += zScale;
        }

        return this.scaleMaze;
    }

    public int[][] getGrid() {
        generateGrid();
        return this.maze;

    }

    public int[][] getWay() {
        generateGrid();
        generateWay();
        return this.maze;

    }

    public int[][] getMaze() {
        return this.scaleMaze;
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

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public int getScale() {
        return scale;
    }

    private Vector2 getRandomStructurePosition(int width, int length, int spacing, Vector2 origin, int range) {
        int security = 0;
        boolean structureAround = true;
        Vector2 coordinate = new Vector2();
        while (structureAround) {
            //Random position selector
            coordinate.x = randomValueInRange(3, this.size - 2 - width, origin.x, range);
            coordinate.z = randomValueInRange(3, this.size - 2 - length, origin.z, range);

            //Check structures around
            structureAround = false;
            int endX = (coordinate.x + spacing + width > this.size) ? this.size : coordinate.x + width + spacing;
            int endZ = (coordinate.z + spacing + length > this.size) ? this.size : coordinate.z + length + spacing;

            for (int x = (coordinate.x - spacing < 0) ? 0 : coordinate.x - spacing; x < endX; x++) {
                for (int z = (coordinate.z - spacing < 0) ? 0 : coordinate.z - spacing; z < endZ; z++) {
                    if (this.maze[x][z] <= -1) {
                        structureAround = true;
                        break;
                    }
                }
            }
            security++;
            if (security >= 10000) {
                Bukkit.broadcastMessage(Message.getPlayerPrefixe() + "§c Random placement has time out. §l[SKIPPING]");
                return null;
            }
        }

        return coordinate;
    }

    private void generateStructureGrid(Vector2 coordinate, Structure area, int width, int length) {
        //Bounding box creation
        BoundingBox areaBox = new BoundingBox(Math.floor(((coordinate.x / 2) * (this.scale + 1) + coordinate.x % 2) + this.origin.getX()), 0, Math.floor(((coordinate.z / 2) * (this.scale + 1) + coordinate.z % 2) + this.origin.getZ()), Math.floor((((coordinate.x / 2) * (this.scale + 1) + coordinate.x % 2) + area.getRegion().getWidth()) + this.origin.getX()), 255, Math.floor(((coordinate.z / 2 * (this.scale + 1) + coordinate.z % 2) + area.getRegion().getLength()) + this.origin.getZ()));
        this.structurePosition.put(areaBox, area);
        for (int x = coordinate.x; x < (coordinate.x + width); x++) {
            for (int z = coordinate.z; z < (coordinate.z + length); z++) {
                this.maze[x][z] = area.getID();
            }
        }
        //Door creation
        int midX = width / 2;
        int midZ = length / 2;
        for (int i = 1; i <= 2; i++) {
            this.maze[coordinate.x + midX][coordinate.z - i] = nbr++;
            this.maze[coordinate.x - i][coordinate.z + midZ] = nbr++;

            this.maze[(coordinate.x + width - 1) - midX][(coordinate.z + length - 1) + i] = nbr++;
            this.maze[(coordinate.x + width - 1) + i][(coordinate.z + length - 1) - midZ] = nbr++;
        }
    }

    private int randomValueInRange(int min, int max, int pos, int range) {
        if (pos - range >= max)
            return max;
        else if (pos + range <= min)
            return min;

        int val;
        do {
            val = (int) (Math.random() * ((range + 1) * 2) - (range + 1)) + pos;

        } while (val > max || val < min || val % 2 == 0);

        return val;

    }
}
