package fr.zelytra.daedalus.structure;

import java.util.ArrayList;

public class StructureManager {
    private ArrayList<Structure> temples;
    private ArrayList<Structure> dungeons;
    private ArrayList<Structure> builds;
    private ArrayList<Structure> generateList;


    public StructureManager() {
        initialize();
        randomGenerationList();
    }

    private void initialize() {
        this.temples = new ArrayList<>();
        this.dungeons = new ArrayList<>();
        this.builds = new ArrayList<>();
        //Temples init
        //temples.add(new Structure(StructureEnum.ZEUS));

        //Dungeons init


        //Builds init
        //builds.add(new Structure(StructureEnum.CORRIDOR));
    }

    private void randomGenerationList(){
        this.generateList = new ArrayList<>();
        this.generateList.add(new Structure(StructureEnum.CORRIDOR));
        this.generateList.add(new Structure(StructureEnum.TEAMRED));
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
