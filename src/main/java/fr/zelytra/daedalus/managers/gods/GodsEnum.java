package fr.zelytra.daedalus.managers.gods;

import fr.zelytra.daedalus.managers.gods.list.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public enum GodsEnum {

    ZEUS("Zeus", false,()->new Zeus()),
    POSEIDON("Poseidon", false,()->new Poseidon()),
    HADES("Hades", false,()->new Hades()),
    ARES("Ares", false,()->new Ares()),
    APHRODITE("Aphrodite", false,()->new Aphrodite()),
    DEMETER("Demeter", false,()->new Demeter()),
    HERMES("Hermes", false,()->new Hermes()),
    ARTEMIS("Artemis", false,()->new Artemis()),
    ATHENA("Athena", false,()->new Athena()),
    DIONYSUS("Dionysus", false,()->new Dionysos()),
    MINOTAURE("Minotaur",false,()->new Minotaure());

    private final String name;
    private boolean selected;
    private final Supplier<Gods> factory;


    GodsEnum(String name, boolean selected,Supplier<Gods> factory){
        this.name = name;
        this.selected = selected;
        this.factory = factory;
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

    public Gods getGod(){
        return this.factory.get();
    }
}
