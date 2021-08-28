package fr.zelytra.daedalus.events.running.environnement.items.listener.wallbreaker;

public enum WallShape {
    BLOCK1(0, 0, 100, 5),
    BLOCK2(1, 0, 100, 5),
    BLOCK3(-1, 0, 100, 5),
    BLOCK4(0, -1, 100, 5),
    BLOCK5(1, -1, 100, 5),
    BLOCK6(-1, -1, 100, 5),
    BLOCK7(0, 1, 100, 5),

    BLOCK8(1, 1, 70, 4.5),
    BLOCK9(2, 1, 70, 4.5),
    BLOCK10(-1, 1, 70, 4.5),
    BLOCK11(-2, 1, 70, 4.5),
    BLOCK12(-2, 0, 70, 4.5),
    BLOCK13(2, 0, 70, 4.5),
    BLOCK14(0, 0, 70, 4.5),
    BLOCK15(0, -2, 70, 4.5),

    BLOCK16(0, 2, 50, 4),
    BLOCK17(1, 2, 50, 4),
    BLOCK18(-1, 2, 50, 4),
    BLOCK19(3, 0, 50, 4),
    BLOCK20(-3, 0, 50, 4),
    BLOCK21(2, -1, 50, 4),
    BLOCK22(-2, -1, 50, 4),
    BLOCK23(-1, -2, 50, 4),
    BLOCK24(-2, -2, 50, 4),
    BLOCK25(1, -2, 50, 4),
    BLOCK26(2, -2, 50, 4);

    private final int x, y;
    private final int luck;
    private final double time;


    WallShape(int x, int y, int luck, double time) {
        this.x = x;
        this.y = y;
        this.luck = luck;
        this.time = time;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLuck() {
        return luck;
    }

    public double getTime() {
        return time;
    }
}
