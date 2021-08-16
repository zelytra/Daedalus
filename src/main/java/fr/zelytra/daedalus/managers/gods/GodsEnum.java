package fr.zelytra.daedalus.managers.gods;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.list.*;
import fr.zelytra.daedalus.managers.items.CustomMaterial;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public enum GodsEnum {

    ZEUS(GameSettings.LANG.textOf("godName.zeus"), false, () -> new Zeus(), CustomMaterial.ZEUS_TOTEM),
    POSEIDON(GameSettings.LANG.textOf("godName.poseidon"), false, () -> new Poseidon(), CustomMaterial.POSEIDON_TOTEM),
    HADES(GameSettings.LANG.textOf("godName.hades"), false, () -> new Hades(), CustomMaterial.HADES_TOTEM),
    ARES(GameSettings.LANG.textOf("godName.ares"), false, () -> new Ares(), CustomMaterial.ARES_TOTEM),
    APHRODITE(GameSettings.LANG.textOf("godName.aphrodite"), false, () -> new Aphrodite(), CustomMaterial.APHRODITE_TOTEM),
    DEMETER(GameSettings.LANG.textOf("godName.demeter"), false, () -> new Demeter(), CustomMaterial.DEMETER_TOTEM),
    HERMES(GameSettings.LANG.textOf("godName.hermes"), false, () -> new Hermes(), CustomMaterial.HERMES_TOTEM),
    ARTEMIS(GameSettings.LANG.textOf("godName.artemis"), false, () -> new Artemis(), CustomMaterial.ARTEMIS_TOTEM),
    ATHENA(GameSettings.LANG.textOf("godName.athena"), false, () -> new Athena(), CustomMaterial.ATHENA_TOTEM),
    DIONYSUS(GameSettings.LANG.textOf("godName.dionysus"), false, () -> new Dionysos(), CustomMaterial.DIONYSUS_TOTEM),
    MINOTAURE(GameSettings.LANG.textOf("godName.minotaur"), false, () -> new Minotaure(), CustomMaterial.MINOTAUR_TOTEM);

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
