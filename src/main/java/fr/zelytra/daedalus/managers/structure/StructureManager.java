package fr.zelytra.daedalus.managers.structure;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.game.settings.TemplesGenerationEnum;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.structure.Structure;

import java.util.ArrayList;

public class StructureManager {
    private ArrayList<Structure> temples;
    private ArrayList<Structure> dungeons;
    private ArrayList<Structure> builds;
    private ArrayList<Structure> fixedStructures;
    private ArrayList<Structure> mine;
    private ArrayList<Structure> generatedList;


    public StructureManager() {
        initialize();
        randomGenerationList();
    }

    private void initialize() {
        this.temples = new ArrayList<>();
        this.dungeons = new ArrayList<>();
        this.builds = new ArrayList<>();
        this.mine = new ArrayList<>();
        this.fixedStructures = new ArrayList<>();
        /*Temples init*/


        /*Dungeons init*/


        /*Builds init*/


        /*Fixed structures init*/
        this.fixedStructures.add(new Structure(StructureEnum.TEAM_RED));
        this.fixedStructures.add(new Structure(StructureEnum.TEAM_BLUE));
        this.fixedStructures.add(new Structure(StructureEnum.TEAM_GREEN));
        this.fixedStructures.add(new Structure(StructureEnum.TEAM_YELLOW));

        /*Mines init*/
        this.mine.add(new Structure(StructureEnum.MINE1));
    }

    private void randomGenerationList() {
        this.generatedList = new ArrayList<>();

        /*Fixed structures*/
        for (Structure structure : this.fixedStructures) {
            generatedList.add(structure);
        }

        /*Mine draw*/

        for (int x = 0; x < GameSettings.getMinesCount(); x++) {
            int random = 0 + (int) (Math.random() * ((this.mine.size() - 0) + 1));
            generatedList.add(this.mine.get(random));
        }

        /*Temples draw*/
        if (GameSettings.getGenerationType() == TemplesGenerationEnum.RANDOM) {
            for (int x = 0; x < GameSettings.getGodLimit(); x++) {
                int random = 0 + (int) (Math.random() * ((this.temples.size() - 0) + 1));
                generatedList.add(this.temples.get(random));
            }
        } else {
            for (GodsEnum god : GameSettings.getGodsList()) {
                for (Structure temple : this.temples) {
                    if (temple.getGod() == god) {
                        generatedList.add(temple);
                    }
                }
            }
        }


    }

    public ArrayList<Structure> getGeneratedList() {
        return this.generatedList;
    }

}
