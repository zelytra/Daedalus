package fr.zelytra.daedalus.managers.game.settings;

public enum BlockCooldownEnum {

    COAL_ORE(30),
    IRON_ORE(45),
    GOLD_ORE(120),
    DIAMOND_ORE(180),
    LAPIS_ORE(120),
    EMERALD_ORE(240),
    REDSTONE_ORE(85),
    ANCIENT_DEBRIS(-1),
    END_STONE(120),
    OBSIDIAN(60),
    LEAVES(5),
    SAND(5),
    RED_SAND(5),
    WOOD(7),
    STONE(7);

    private int seconds;

    BlockCooldownEnum(int seconds){
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
