package fr.zelytra.daedalus.maze;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;import org.bukkit.entity.Player;



public class Maze {
    private int[][] maze;
    private int size;
    private final boolean complexity;
    private Player player;

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

    public Maze(int size, boolean complexity, Player player) {
        this.size = size;
        this.complexity = complexity;
        this.maze = new int[this.size][this.size];
        this.player = player;
    }

    private void generateGrid() {
        //Fill outline with wall
        // Wall = 1| void = 0
        logPlayer(this.player,"§6§lGenerated grid...");
        if (this.size % 2 != 0) {
            this.size--;
        }
        int nbr = 2;
        int[] wall = new int[this.size];
        int[] line = new int[this.size];
        //Generate line and wall matrix;
        for (int x = 0; x < this.size; x++) {
            wall[x] = 1;
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
        for (int x = 0; x < this.size; x++) {
            for (int z = 0; z < this.size; z++) {
                if (this.maze[x][z] == 0) {
                    this.maze[x][z] = nbr;
                    nbr++;
                }
            }
        }
    }

    private void generateWay() {
        boolean isGenerated = false;
        logPlayer(this.player,"§6§lGenerated way...");
        while (!isGenerated) {
            int x = (int) Math.random() % (this.size - 2) + 1;

            isGenerated =true;
        }
    }

    public int[][] getMaze() {
        generateGrid();
        generateWay();
        return this.maze;
    }

    public int getSize() {
        return this.size;
    }

    private void logPlayer(Player player,String msg){
        if(player!=null){
            BaseComponent txt = new TextComponent(msg);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,txt);
        }
    }
}
