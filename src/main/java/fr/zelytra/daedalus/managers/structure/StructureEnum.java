package fr.zelytra.daedalus.managers.structure;

import fr.zelytra.daedalus.managers.gods.GodsEnum;
import org.bukkit.util.BlockVector;

public enum StructureEnum {
    /*
    Fixed structure need to have a pair origin location.
    Origin is the center of the schematics coordinate at the maze matrix none upscale
     */
    ZEUS("zeusTemple", StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.ZEUS),
    APHRODITE("aphroditeTemple", StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.APHRODITE),
    ARES("aresTemple", StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.ARES),
    ARTEMIS("artemisTemple", StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.ARTEMIS),
    ATHENA("athenaTemple", StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.ATHENA),
    DEMETER("demeterTemple", StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.DEMETER),
    DIONYSOS("dionysosTemple", StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.DIONYSUS),
    HADES("hadesTemple", StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.HADES),
    HERMES("hermesTemple", StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.HERMES),
    POSEIDON("poseidonTemple", StructureType.TEMPLE, false, new BlockVector(0, -3, 0), GodsEnum.POSEIDON),

    MINE1("mine1", StructureType.MINE, true, new BlockVector(0, -23, 0)),

    TEAM_RED("redTeam", StructureType.FIXED, false, new BlockVector(30, 0, 30), new BlockVector(0, -3, 0)),
    TEAM_GREEN("greenTeam", StructureType.FIXED, false, new BlockVector(270, 0, 30), new BlockVector(0, -3, 0)),
    TEAM_YELLOW("yellowTeam", StructureType.FIXED, false, new BlockVector(270, 0, 270), new BlockVector(0, -3, 0)),
    TEAM_BLUE("blueTeam", StructureType.FIXED, false, new BlockVector(30, 0, 270), new BlockVector(0, -3, 0)),
    MINOTAURE("minotaure", StructureType.FIXED, false, new BlockVector(150, 0, 150), new BlockVector(0, -3, 0)),

    HESPERIDES_GARDEN("hesperidesGarden", StructureType.BUILD, false, new BlockVector(0, -8, 0)),
    CIRCEE_ISLAND("circeeIsland", StructureType.BUILD, false, new BlockVector(0, -5, 0)),

    DUNGEON1("dungeon", StructureType.DUNGEON, false, new BlockVector(0, -21, 0));


    private final String name;
    private final StructureType type;
    private BlockVector origin;
    private BlockVector offset;
    private GodsEnum god;
    private Boolean canBlock;


    StructureEnum(String name, StructureType type, Boolean canBlock, BlockVector offset) {
        this.name = name;
        this.type = type;
        this.offset = offset;
        this.canBlock = canBlock;
    }

    StructureEnum(String name, StructureType type, Boolean canBlock, BlockVector offset, GodsEnum god) {
        this.name = name;
        this.type = type;
        this.offset = offset;
        this.god = god;
        this.canBlock = canBlock;
    }

    StructureEnum(String name, StructureType type, Boolean canBlock, BlockVector origin, BlockVector offset) {
        this.name = name;
        this.type = type;
        this.origin = origin;
        this.offset = offset;
        this.canBlock = canBlock;
    }

    public String getName() {
        return this.name;
    }

    public StructureType getType() {
        return this.type;
    }

    public BlockVector getOrigin() {
        return this.origin;
    }

    public BlockVector getOffset() {
        return this.offset;
    }

    public GodsEnum getGod() {
        return god;
    }

    public StructureEnum getByName(String name) {
        for (StructureEnum structure : StructureEnum.values()) {
            if (structure.getName().equalsIgnoreCase(name)) {
                return structure;
            }
        }
        return null;
    }

    public Boolean canBlock() {
        return this.canBlock;
    }
}
