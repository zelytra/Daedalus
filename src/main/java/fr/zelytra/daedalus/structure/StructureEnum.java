package fr.zelytra.daedalus.structure;

import org.bukkit.util.BlockVector;

public enum StructureEnum {
    /*
    Fixed structure need to have a pair origin location.
    Origin is the center of the schematics coordinate at the maze matrix none upscale
     */
    ZEUS("zeus", StructureType.TEMPLE, 1),
    CORRIDOR("corridor", StructureType.BUILD),
    TEAM_RED("redTeam", StructureType.FIXED, 1, new BlockVector(100, 0, 100),new BlockVector(0, -2, 0)),
    TEAM_GREEN("greenTeam", StructureType.FIXED, 1, new BlockVector(200, 0,100),new BlockVector(0, -2, 0)),
    TEAM_YELLOW("yellowTeam", StructureType.FIXED, 1, new BlockVector(200, 0, 200),new BlockVector(0, -2, 0)),
    TEAM_BLUE("blueTeam", StructureType.FIXED, 1, new BlockVector(100, 0, 200),new BlockVector(0, -2, 0));


    private final String name;
    private final StructureType type;
    private BlockVector origin;
    private BlockVector offset;
    private int number;


    StructureEnum(String name, StructureType type) {
        this.name = name;
        this.type = type;
    }

    StructureEnum(String name, StructureType type, int number) {
        this.name = name;
        this.type = type;
        this.number = number;
    }

    StructureEnum(String name, StructureType type, int number, BlockVector origin,BlockVector offset) {
        this.name = name;
        this.type = type;
        this.number = number;
        this.origin = origin;
        this.offset = offset;
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

    public int getNumber() {
        return this.number;
    }

    public BlockVector getOffset() {
        return this.offset;
    }

    public StructureEnum getByName(String name) {
        for (StructureEnum structure : StructureEnum.values()) {
            if (structure.getName().equalsIgnoreCase(name)) {
                return structure;
            }
        }
        return null;
    }

}
