package fr.zelytra.daedalus.managers.gods;

import fr.zelytra.daedalus.managers.gods.list.*;
import fr.zelytra.daedalus.managers.items.CustomMaterial;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public enum GodsEnum {

    ZEUS("Zeus", false, () -> new Zeus(), CustomMaterial.ZEUS_TOTEM),
    POSEIDON("Poseidon", false, () -> new Poseidon(), CustomMaterial.POSEIDON_TOTEM),
    HADES("Hades", false, () -> new Hades(), CustomMaterial.HADES_TOTEM),
    ARES("Ares", false, () -> new Ares(), CustomMaterial.ARES_TOTEM),
    APHRODITE("Aphrodite", false, () -> new Aphrodite(), CustomMaterial.APHRODITE_TOTEM),
    DEMETER("Demeter", false, () -> new Demeter(), CustomMaterial.DEMETER_TOTEM),
    HERMES("Hermes", false, () -> new Hermes(), CustomMaterial.HERMES_TOTEM),
    ARTEMIS("Artemis", false, () -> new Artemis(), CustomMaterial.ARTEMIS_TOTEM),
    ATHENA("Athena", false, () -> new Athena(), CustomMaterial.ATHENA_TOTEM),
    DIONYSUS("Dionysus", false, () -> new Dionysos(), CustomMaterial.DIONYSOS_TOTEM),
    MINOTAURE("Minotaur", false, () -> new Minotaure(), CustomMaterial.MINOTAUR_TOTEM);

    private final String name;
    private boolean selected;
    private final Supplier<Gods> factory;
    private final CustomMaterial totem;


    GodsEnum(String name, boolean selected, Supplier<Gods> factory, CustomMaterial totem) {
        this.name = name;
        this.selected = selected;
        this.factory = factory;
        this.totem = totem;
    }

    public String getName() {
        return name;
    }

    public CustomMaterial getTotem() {
        return totem;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public static GodsEnum getRandomGod() {
        final List<GodsEnum> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        return VALUES.get(new Random().nextInt(VALUES.size()));
    }

    public Gods getGod() {
        return this.factory.get();
    }
}
