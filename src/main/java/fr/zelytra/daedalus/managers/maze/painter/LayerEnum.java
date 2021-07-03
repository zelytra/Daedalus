package fr.zelytra.daedalus.managers.maze.painter;


import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;

import java.util.Arrays;
import java.util.List;

public enum LayerEnum {
    layer0(1, -1, BlockTypes.SMOOTH_SANDSTONE),
    layer1(1, 0, BlockTypes.STRIPPED_JUNGLE_WOOD),
    layer2(0.8, 1, BlockTypes.STRIPPED_JUNGLE_WOOD, BlockTypes.SANDSTONE),
    layer3(0.6, 2, BlockTypes.STRIPPED_JUNGLE_WOOD, BlockTypes.SANDSTONE),
    layer4(0.6, 3, BlockTypes.STRIPPED_JUNGLE_WOOD, BlockTypes.SANDSTONE),
    layer5(0.6, 4, BlockTypes.SANDSTONE, BlockTypes.STRIPPED_JUNGLE_WOOD),
    layer6(0.6, 5, BlockTypes.SANDSTONE, BlockTypes.STRIPPED_JUNGLE_WOOD),
    layer7(0.7, 6, BlockTypes.SANDSTONE, BlockTypes.STRIPPED_JUNGLE_WOOD),
    layer8(0.8, 7, BlockTypes.SANDSTONE, BlockTypes.STRIPPED_JUNGLE_WOOD),
    layer9(1, 8, BlockTypes.SANDSTONE),
    layer10(0.6, 9, BlockTypes.SANDSTONE, BlockTypes.SMOOTH_SANDSTONE),
    layer11(0.6, 10, BlockTypes.SANDSTONE, BlockTypes.SMOOTH_SANDSTONE),
    layer12(0.6, 11, BlockTypes.SMOOTH_SANDSTONE, BlockTypes.SANDSTONE),
    layer13(0.6, 12, BlockTypes.SMOOTH_SANDSTONE, BlockTypes.SANDSTONE),
    layer14(0.8, 13, BlockTypes.SMOOTH_SANDSTONE, BlockTypes.SANDSTONE),
    layer15(1, 14, BlockTypes.SMOOTH_SANDSTONE),
    layer16(0.8, 15, BlockTypes.SMOOTH_SANDSTONE, BlockTypes.DEAD_BRAIN_CORAL_BLOCK),
    layer17(0.6, 16, BlockTypes.SMOOTH_SANDSTONE, BlockTypes.DEAD_BRAIN_CORAL_BLOCK),
    layer18(0.7, 17, BlockTypes.DEAD_BRAIN_CORAL_BLOCK, BlockTypes.SMOOTH_SANDSTONE),
    layer19(0.5, 18, BlockTypes.DEAD_BRAIN_CORAL_BLOCK, BlockTypes.BASALT),
    layer20(1, 19, BlockTypes.BASALT);

    private final double luck;
    private final int y;
    private final List<BlockType> blockTypes;

    LayerEnum(double luck, int y, BlockType... blockTypes) {
        this.y = y;
        this.luck = luck;
        this.blockTypes = Arrays.asList(blockTypes);
    }

    public static LayerEnum getByLevel(int y) {
        for (LayerEnum layerEnum : LayerEnum.values())
            if (layerEnum.getY() == y)
                return layerEnum;

        return null;
    }

    public int getY() {
        return this.y;
    }

    public BlockType getBlockTypes() {
        if (Math.random() <= this.luck)
            return this.blockTypes.get(0);
        else
            return this.blockTypes.get(1);
    }
}
