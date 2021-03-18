package fr.zelytra.daedalus.structure;

import java.util.ArrayList;

public class StructureManager {
    private ArrayList<Structure> temples;
    private ArrayList<Structure> dungeons;
    private ArrayList<Structure> builds;

    private ArrayList<Structure> generateList;


    public StructureManager() {
        initialize();
    }

    private void initialize() {
        //Temples init
        //temples.add(new Structure(StructureEnum.ZEUS));

        //Dungeons init


        //Builds init
        //builds.add(new Structure(StructureEnum.CORRIDOR));
    }

    private void randomGenerationList(){
        //TODO Fonction du tirage random celon paramètre
    }

    public ArrayList<Structure> getTemples() {
        return this.temples;
    }

    public ArrayList<Structure> getDungeons() {
        return this.dungeons;
    }

    public ArrayList<Structure> getBuilds() {
        return this.builds;
    }

    public ArrayList<Structure> getGenerateList() {
        return this.generateList;
    }
}
