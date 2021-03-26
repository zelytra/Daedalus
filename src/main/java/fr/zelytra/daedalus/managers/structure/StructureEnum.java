package fr.zelytra.daedalus.managers.structure;

import fr.zelytra.daedalus.managers.gods.GodsEnum;
import org.bukkit.util.BlockVector;

public enum StructureEnum {
    /*
    Fixed structure need to have a pair origin location.
    Origin is the center of the schematics coordinate at the maze matrix none upscale
     */
    ZEUS("zeus", StructureType.TEMPLE, new BlockVector(0, -3, 0), GodsEnum.ZEUS),
    CORRIDOR("corridor", StructureType.BUILD, new BlockVector(0, 0, 0)),

    MINE1("mine1", StructureType.MINE, new BlockVector(0, 0, 0)),

    TEAM_RED("redTeam", StructureType.FIXED, new BlockVector(100, 0, 100), new BlockVector(0, -3, 0)),
    TEAM_GREEN("greenTeam", StructureType.FIXED, new BlockVector(200, 0, 100), new BlockVector(0, -3, 0)),
    TEAM_YELLOW("yellowTeam", StructureType.FIXED, new BlockVector(200, 0, 200), new BlockVector(0, -3, 0)),
    TEAM_BLUE("blueTeam", StructureType.FIXED, new BlockVector(100, 0, 200), new BlockVector(0, -3, 0));


    private final String name;
    private final StructureType type;
    private BlockVector origin;
    private BlockVector offset;
    private GodsEnum god;


    StructureEnum(String name, StructureType type, BlockVector offset) {
        this.name = name;
        this.type = type;
        this.offset = offset;
    }

    StructureEnum(String name, StructureType type, BlockVector offset, GodsEnum god) {
        this.name = name;
        this.type = type;
        this.offset = offset;
        this.god = god;
    }

    StructureEnum(String name, StructureType type, BlockVector origin, BlockVector offset) {
        this.name = name;
        this.type = type;
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

}
