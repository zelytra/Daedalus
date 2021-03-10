package fr.zelytra.daedalus.maze;

public class Maze {
    private int[][] maze;
    private final int size;
    private final boolean complexity;

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

    private void generate() {
        //Fill outline with wall
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
            if (x % 2 == 0) {
                for (int z = 1; z < this.size-1; z++) {
                    this.maze[x][z]=line[z];
                }
            }
            else{
                for (int z = 0; z < this.size; z++) {
                    this.maze[x][z]=1;
                }
            }
        }
        //Fill case value
        for (int x = 0; x < this.size; x++) {
            for (int z = 0; z < this.size; z++) {

            }
        }


    }

    public int[][] getMaze(){
        generate();
        return this.maze;
    }

    public int getSize() {
        return this.size;
    }
}
