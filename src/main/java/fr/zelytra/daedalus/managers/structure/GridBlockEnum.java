package fr.zelytra.daedalus.managers.structure;

import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.map.MapPalette;


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
    WALL(1, BlockTypes.BLACK_CONCRETE, MapPalette.matchColor(0, 0, 0)),
    TEAM(-1, BlockTypes.GRAY_CONCRETE, MapPalette.matchColor(0, 255, 0)),
    TEMPLE(-2, BlockTypes.BLUE_CONCRETE, MapPalette.matchColor(0, 0, 255)),
    MINE(-3, BlockTypes.ORANGE_CONCRETE, MapPalette.matchColor(255, 0, 255)),
    DUNGEON(-4, BlockTypes.RED_CONCRETE, MapPalette.matchColor(255, 0, 0)),
    CIRCEE(-5, BlockTypes.CYAN_CONCRETE, MapPalette.matchColor(125, 125, 255)),
    HESPERIDE(-6, BlockTypes.GREEN_CONCRETE, MapPalette.matchColor(0, 125, 0));

    private final int id;
    private final BlockType material;
    private static BlockType pathMaterial = BlockTypes.WHITE_CONCRETE;
    private final Byte mapPalette;

    GridBlockEnum(int id, BlockType material, Byte mapPalette) {
        this.id = id;
        this.material = material;
        this.mapPalette = mapPalette;
    }

    public Byte getMapPalette() {
        return mapPalette;
    }

    public static BlockType getBlockType(int id) {
        for (GridBlockEnum block : GridBlockEnum.values()) {
            if (block.id == id) {
                return block.material;
            }
        }
        return pathMaterial;
    }

    public static Byte getMapPalette(int id) {
        for (GridBlockEnum block : GridBlockEnum.values()) {
            if (block.id == id) {
                return block.mapPalette;
            }
        }
        return MapPalette.matchColor(255, 255, 255);
    }
}
