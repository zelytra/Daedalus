package fr.zelytra.daedalus.managers.structure;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.loottable.LootTableManager;
import fr.zelytra.daedalus.managers.maze.Maze;
import fr.zelytra.daedalus.managers.skrink.ShrinkManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class StructureManager {
    private ArrayList<Structure> temples;
    private ArrayList<Structure> dungeons;
    private ArrayList<Structure> fixedStructures;
    private ArrayList<Structure> mine;
    @Getter
    private ArrayList<Structure> generatedList;
    @Getter
    @Setter
    private HashMap<BoundingBox, Structure> structuresPosition;
    @Getter
    @Setter
    private Maze maze;
    public StructureSurrounded structureSurrounded;
    @Getter
    private LootTableManager lootTableManager;
    @Getter
    private ShrinkManager shrinkManager;


    public StructureManager() {
        initialize();
        randomGenerationList();
        this.lootTableManager = new LootTableManager();
        this.structureSurrounded = new StructureSurrounded();
        this.shrinkManager = new ShrinkManager();
    }

    private void initialize() {
        this.structuresPosition = new HashMap<>();
        this.temples = new ArrayList<>();
        this.dungeons = new ArrayList<>();
        this.mine = new ArrayList<>();
        this.fixedStructures = new ArrayList<>();

        /*Temples init*/
        this.temples.add(new Structure(StructureEnum.ZEUS));
        this.temples.add(new Structure(StructureEnum.APHRODITE));
        this.temples.add(new Structure(StructureEnum.ARES));
        this.temples.add(new Structure(StructureEnum.ARTEMIS));
        this.temples.add(new Structure(StructureEnum.ATHENA));
        this.temples.add(new Structure(StructureEnum.DEMETER));
        this.temples.add(new Structure(StructureEnum.DIONYSOS));
        this.temples.add(new Structure(StructureEnum.HADES));
        this.temples.add(new Structure(StructureEnum.HERMES));
        this.temples.add(new Structure(StructureEnum.POSEIDON));

        /*Dungeons init*/
        this.dungeons.add(new Structure(StructureEnum.DUNGEON1));
        this.dungeons.add(new Structure(StructureEnum.DUNGEON2));
        this.dungeons.add(new Structure(StructureEnum.DUNGEON3));

        /*Fixed structures init*/
        this.fixedStructures.add(new Structure(StructureEnum.TEAM_RED));
        this.fixedStructures.add(new Structure(StructureEnum.TEAM_BLUE));
        this.fixedStructures.add(new Structure(StructureEnum.TEAM_GREEN));
        this.fixedStructures.add(new Structure(StructureEnum.TEAM_YELLOW));
        this.fixedStructures.add(new Structure(StructureEnum.MINOTAURE));

        /*Mines init*/
        this.mine.add(new Structure(StructureEnum.MINE1));
        this.mine.add(new Structure(StructureEnum.MINE2));
        this.mine.add(new Structure(StructureEnum.MINE3));

    }

    private void randomGenerationList() {
        this.generatedList = new ArrayList<>();

        /*Fixed structures*/
        generatedList.addAll(this.fixedStructures);

        /*Mines draw*/
        for (int x = 0; x < GameSettings.MINES_COUNT; x++) {
            int random = ThreadLocalRandom.current().nextInt(0, 2 + 1);
            generatedList.add(this.mine.get(random));
        }

        /*Temples draw*/

        for (int x = 0; x < GameSettings.GOD_LIMIT; x++) {
            generatedList.add(this.temples.get(x));
        }


        /*Dungeons draw*/
        for (int x = 0; x < GameSettings.DUNGEONS_COUNT; x++) {
            generatedList.add(this.dungeons.get(ThreadLocalRandom.current().nextInt(0, this.dungeons.size())));
        }

        /*Library draw*/
        for (int x = 0; x < GameSettings.LIBRARY; x++) {
            generatedList.add(new Structure(StructureEnum.DAEDALUS_LIBRARY));
        }

        /*Circee draw*/
        for (int x = 0; x < GameSettings.CIRCEE_ISLANDS_COUNT; x++) {
            generatedList.add(new Structure(StructureEnum.CIRCEE_ISLAND));
        }

        /*Hesperide draw*/
        for (int x = 0; x < GameSettings.HESPERIDES_GARDEN_COUNT; x++) {
            generatedList.add(new Structure(StructureEnum.HESPERIDES_GARDEN));
        }

    }
}
