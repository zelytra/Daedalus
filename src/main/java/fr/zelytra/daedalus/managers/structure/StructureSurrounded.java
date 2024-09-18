package fr.zelytra.daedalus.managers.structure;

import java.util.ArrayList;

public class StructureSurrounded {
    private final ArrayList<Structure> structures = new ArrayList<>();
    private final int areaSize = 20;

    public StructureSurrounded() {
        structures.add(new Structure(StructureEnum.MINE1));
        structures.add(new Structure(StructureEnum.MINE2));
        structures.add(new Structure(StructureEnum.MINE3));
        structures.add(new Structure(StructureEnum.CIRCEE_ISLAND));
    }

    public ArrayList<Structure> getStructures() {
        return structures;
    }

    public int getAreaSize() {
        return areaSize;
    }

}
