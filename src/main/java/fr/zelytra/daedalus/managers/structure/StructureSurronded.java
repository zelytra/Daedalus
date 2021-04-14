package fr.zelytra.daedalus.managers.structure;

import java.util.ArrayList;

public class StructureSurronded {
    private ArrayList<Structure> structures = new ArrayList<>();
    private int areaSize = 13;

    public StructureSurronded() {
        structures.add(new Structure(StructureEnum.MINE1));
        structures.add(new Structure(StructureEnum.MINE1));
        structures.add(new Structure(StructureEnum.MINE1));
        structures.add(new Structure(StructureEnum.CIRCEE_ISLAND));
    }

    public ArrayList<Structure> getStructures() {
        return structures;
    }

    public int getAreaSize() {
        return areaSize;
    }

}
