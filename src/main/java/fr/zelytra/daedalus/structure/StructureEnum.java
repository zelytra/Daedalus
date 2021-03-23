package fr.zelytra.daedalus.structure;

import org.bukkit.util.BlockVector;

public enum StructureEnum {
    ZEUS("zeus", StructureType.TEMPLE),
    CORRIDOR("corridor", StructureType.BUILD),
    TEAMRED("team1", StructureType.FIXED,new BlockVector(21,0,21));


    private final String name;
    private final StructureType type;
    private BlockVector origin;


    StructureEnum(String name, StructureType type) {
        this.name = name;
        this.type = type;
    }

    StructureEnum(String name, StructureType type, BlockVector origin) {
        this.name = name;
        this.type = type;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public StructureType getType() {
        return type;
    }

    public BlockVector getOrigin() {
        return origin;
    }

    public StructureEnum getByName(String name) {
        for (StructureEnum structure : StructureEnum.values()) {
            if(structure.getName().equalsIgnoreCase(name)){
                return structure;
            }
        }
        return null;
    }

}
