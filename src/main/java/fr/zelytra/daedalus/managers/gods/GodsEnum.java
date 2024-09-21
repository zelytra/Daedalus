package fr.zelytra.daedalus.managers.gods;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.list.*;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import lombok.Getter;

public enum GodsEnum {
  ZEUS("godName.zeus", false, Zeus::new, CustomMaterial.ZEUS_TOTEM),
  POSEIDON("godName.poseidon", false, Poseidon::new, CustomMaterial.POSEIDON_TOTEM),
  HADES("godName.hades", false, Hades::new, CustomMaterial.HADES_TOTEM),
  ARES("godName.ares", false, Ares::new, CustomMaterial.ARES_TOTEM),
  APHRODITE("godName.aphrodite", false, Aphrodite::new, CustomMaterial.APHRODITE_TOTEM),
  DEMETER("godName.demeter", false, Demeter::new, CustomMaterial.DEMETER_TOTEM),
  HERMES("godName.hermes", false, Hermes::new, CustomMaterial.HERMES_TOTEM),
  ARTEMIS("godName.artemis", false, Artemis::new, CustomMaterial.ARTEMIS_TOTEM),
  ATHENA("godName.athena", false, Athena::new, CustomMaterial.ATHENA_TOTEM),
  DIONYSUS("godName.dionysus", false, Dionysos::new, CustomMaterial.DIONYSUS_TOTEM),
  MINOTAURE("godName.minotaur", false, Minotaure::new, CustomMaterial.MINOTAUR_TOTEM);

  private final String name;
  @Getter private boolean selected;
  private final Supplier<Gods> factory;
  @Getter private final CustomMaterial totem;

  GodsEnum(String name, boolean selected, Supplier<Gods> factory, CustomMaterial totem) {
    this.name = name;
    this.selected = selected;
    this.factory = factory;
    this.totem = totem;
  }

  public String getName() {
    return GameSettings.LANG.textOf(name);
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
