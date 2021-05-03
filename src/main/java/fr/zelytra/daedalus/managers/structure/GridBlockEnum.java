package fr.zelytra.daedalus.managers.structure;

import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;


public enum GridBlockEnum {
    /*
    ID:
    - 1 : Wall
    - -1 : Team
    - -2 : Temple
    - -3 : Mine
    - -4 : Dungeon
    - -5 : Circee island
    - -6 : Hesperides garden

     */
    WALL(1, BlockTypes.BLACK_CONCRETE),
    TEAM(-1, BlockTypes.GRAY_CONCRETE),
    TEMPLE(-2, BlockTypes.BLUE_CONCRETE),
    MINE(-3, BlockTypes.ORANGE_CONCRETE),
    DUNGEON(-4, BlockTypes.RED_CONCRETE),
    CIRCEE(-5, BlockTypes.CYAN_CONCRETE),
    HESPERIDE(-6, BlockTypes.GREEN_CONCRETE);

    private final int id;
    private final BlockType material;
    private static BlockType pathMaterial = BlockTypes.WHITE_CONCRETE;

    GridBlockEnum(int id, BlockType material) {
        this.id = id;
        this.material = material;
    }

    public static BlockType getBlockType(int id) {
        for (GridBlockEnum block : GridBlockEnum.values()) {
            if (block.id == id) {
                return block.material;
            }
        }
        return pathMaterial;
    }

}
