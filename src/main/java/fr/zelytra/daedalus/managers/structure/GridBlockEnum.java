package fr.zelytra.daedalus.managers.structure;

import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import java.awt.*;
import lombok.Getter;

public enum GridBlockEnum {
	/*
	 * ID: - 1 : Wall - -1 : Team - -2 : Temple - -3 : Mine - -4 : Dungeon - -5 :
	 * Circee island - -6 : Hesperides garden
	 *
	 */
	WALL(1, BlockTypes.BLACK_CONCRETE, new Color(244, 230, 161)), TEAM(-1, BlockTypes.GRAY_CONCRETE,
			new Color(46, 88, 25)), TEMPLE(-2, BlockTypes.BLUE_CONCRETE, new Color(213, 255, 246)), MINE(-3,
					BlockTypes.ORANGE_CONCRETE,
					new Color(184, 108, 43)), DUNGEON(-4, BlockTypes.RED_CONCRETE, new Color(107, 36, 36)), CIRCEE(-5,
							BlockTypes.CYAN_CONCRETE,
							new Color(95, 144, 193)), HESPERIDE(-6, BlockTypes.GREEN_CONCRETE, new Color(250, 150, 19));

	private final int id;
	private final BlockType material;
	private static BlockType pathMaterial = BlockTypes.WHITE_CONCRETE;

	@Getter
	private final Color mapPalette;

	GridBlockEnum(int id, BlockType material, Color mapPalette) {
		this.id = id;
		this.material = material;
		this.mapPalette = mapPalette;
	}

	public static BlockType getBlockType(int id) {
		for (GridBlockEnum block : GridBlockEnum.values()) {
			if (block.id == id) {
				return block.material;
			}
		}
		return pathMaterial;
	}

	public static Color getMapPalette(int id) {
		for (GridBlockEnum block : GridBlockEnum.values()) {
			if (block.id == id) {
				return block.mapPalette;
			}
		}
		return new Color(128, 93, 65);
	}
}
