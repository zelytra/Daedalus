package fr.zelytra.daedalus.structure;

public enum StructureEnum {
    ZEUS("zeus", "temple"),
    CORRIDOR("corridor", "build");

    private String name;
    private String type;


    StructureEnum(String name, String type) {
        this.name = name;
        this.type = type;


    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
