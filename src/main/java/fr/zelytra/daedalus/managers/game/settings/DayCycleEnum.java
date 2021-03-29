package fr.zelytra.daedalus.managers.game.settings;

public enum DayCycleEnum {

    ETERNAL_DAY("Eternal day", 6000),
    ETERNAL_NIGHT("Eternal night", 18000),
    DEFAULT("One cycle per episode");

    private String text;
    private long ticks;

    DayCycleEnum(String text){
        this.text = text;
    }

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
