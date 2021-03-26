package fr.zelytra.daedalus.managers.game.settings;

public enum BlockCooldown {

    COAL_ORE(30),
    IRON_ORE(45),
    GOLD_ORE(120),
    DIAMOND_ORE(300),
    LAPIS_ORE(120),
    EMERALD_ORE(240),
    REDSTONE_ORE(85),
    ANCIENT_DEBRIS(-1),
    END_STONE(120),
    OBSIDIAN(60),
    LEAVES(5),
    WOOD(5);

    private int seconds;

    BlockCooldown(int seconds){
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
