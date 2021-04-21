package fr.zelytra.daedalus.managers.gods;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum GodsEnum {

    ZEUS("Zeus", false),
    POSEIDON("Poseidon", false),
    HADES("Hades", false),
    ARES("Ares", false),
    APHRODITE("Aphrodite", false),
    DEMETER("Demeter", false),
    HERMES("Hermes", false),
    ARTEMIS("Artemis", false),
    ATHENA("Athena", false),
    DIONYSUS("Dionysus", false);

    private final String name;
    private boolean selected;

    GodsEnum(String name, boolean selected){
        this.name = name;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public static GodsEnum getRandomGod()  {
        final List<GodsEnum> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        return VALUES.get(new Random().nextInt(VALUES.size()));
    }
}
