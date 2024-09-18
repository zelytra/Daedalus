package fr.zelytra.daedalus.managers.structure;

import fr.zelytra.daedalus.managers.gods.GodsEnum;
import lombok.Getter;
import org.bukkit.util.BlockVector;

public enum StructureEnum {
    /*
    Fixed structure need to have a pair origin location.
    Origin is the center of the schematics coordinate at the maze matrix none upscale

    ID:
    - -1 : Team
    - -2 : Temple
    - -3 : Mine
    - -4 : Dungeon
    - -5 : Circee island
    - -6 : Hesperides garden
    - -7 : Library

     */
    ZEUS("zeusTemple", -2, StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.ZEUS),
    APHRODITE("aphroditeTemple", -2, StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.APHRODITE),
    ARES("aresTemple", -2, StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.ARES),
    ARTEMIS("artemisTemple", -2, StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.ARTEMIS),
    ATHENA("athenaTemple", -2, StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.ATHENA),
    DEMETER("demeterTemple", -2, StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.DEMETER),
    DIONYSOS("dionysosTemple", -2, StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.DIONYSUS),
    HADES("hadesTemple", -2, StructureType.TEMPLE, false, new BlockVector(0, -85, 0), GodsEnum.HADES),
    HERMES("hermesTemple", -2, StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.HERMES),
    POSEIDON("poseidonTemple", -2, StructureType.TEMPLE, false, new BlockVector(0, -84, 0), GodsEnum.POSEIDON),

    MINE1("mine1", -3, StructureType.MINE, true, new BlockVector(0, -23, 0)),
    MINE2("mine2", -3, StructureType.MINE, true, new BlockVector(0, -23, 0)),
    MINE3("mine3", -3, StructureType.MINE, true, new BlockVector(0, -23, 0)),

    TEAM_RED("redTeam", -1, StructureType.BASE, true, new BlockVector(30, 0, 30), new BlockVector(0, -3, 0)),
    TEAM_GREEN("greenTeam", -1, StructureType.BASE, true, new BlockVector(270, 0, 30), new BlockVector(0, -3, 0)),
    TEAM_YELLOW("yellowTeam", -1, StructureType.BASE, true, new BlockVector(270, 0, 270), new BlockVector(0, -3, 0)),
    TEAM_BLUE("blueTeam", -1, StructureType.BASE, true, new BlockVector(30, 0, 270), new BlockVector(0, -3, 0)),
    MINOTAURE("minotaure", -1, StructureType.BASE, true, new BlockVector(150, 0, 150), new BlockVector(0, -33, 0),GodsEnum.MINOTAURE),

    HESPERIDES_GARDEN("hesperidesGarden", -6, StructureType.HESPERIDES_GARDEN, true, new BlockVector(0, -3, 0)),
    CIRCEE_ISLAND("circeeIsland", -5, StructureType.CIRCE_ISLAND, true, new BlockVector(0, -3, 0)),
    DAEDALUS_LIBRARY("daedalusLibrary", -7, StructureType.LIBRARY, false, new BlockVector(0, -3, 0)),

    DUNGEON1("dungeon1", -4, StructureType.DUNGEON, true, new BlockVector(0, -41, 0)),
    DUNGEON2("dungeon2", -4, StructureType.DUNGEON, true, new BlockVector(0, -41, 0)),
    DUNGEON3("dungeon3", -4, StructureType.DUNGEON, true, new BlockVector(0, -41, 0));


    @Getter
    private final String name;
    @Getter
    private final StructureType type;
    @Getter
    private BlockVector origin;
    @Getter
    private final BlockVector offset;
    @Getter
    private GodsEnum god;
    private final Boolean canBlock;
    private final int id;


    StructureEnum(String name, int id, StructureType type, Boolean canBlock, BlockVector offset) {
        this.name = name;
        this.type = type;
        this.offset = offset;
        this.canBlock = canBlock;
        this.id = id;
    }

    StructureEnum(String name, int id, StructureType type, Boolean canBlock, BlockVector offset, GodsEnum god) {
        this.name = name;
        this.type = type;
        this.offset = offset;
        this.god = god;
        this.canBlock = canBlock;
        this.id = id;
    }

    StructureEnum(String name, int id, StructureType type, Boolean canBlock, BlockVector origin, BlockVector offset) {
        this.name = name;
        this.type = type;
        this.origin = origin;
        this.offset = offset;
        this.canBlock = canBlock;
        this.id = id;
    }

    StructureEnum(String name, int id, StructureType type, Boolean canBlock, BlockVector origin, BlockVector offset,GodsEnum god) {
        this.name = name;
        this.type = type;
        this.origin = origin;
        this.offset = offset;
        this.canBlock = canBlock;
        this.god = god;
        this.id = id;
    }

    public StructureEnum getByName(String name) {
        for (StructureEnum structure : StructureEnum.values()) {
            if (structure.getName().equalsIgnoreCase(name)) {
                return structure;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public Boolean canBlock() {
        return this.canBlock;
    }
}
