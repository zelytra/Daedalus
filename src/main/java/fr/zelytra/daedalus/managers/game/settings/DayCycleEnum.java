package fr.zelytra.daedalus.managers.game.settings;

public enum DayCycleEnum {

    ETERNAL_DAY("Eternal day", 6000),
    ETERNAL_NIGHT("Eternal night", 18000),
    DEFAULT("One cycle per episode", 0);

    private final String text;
    private final long ticks;

    DayCycleEnum(String text, long ticks){
        this.text = text;
        this.ticks = ticks;
    }

    public String getText() {
        return text;
    }

    public long getTicks() {
        return ticks;
    }
}
