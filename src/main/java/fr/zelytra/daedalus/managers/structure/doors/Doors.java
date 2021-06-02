package fr.zelytra.daedalus.managers.structure.doors;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.BoundingBox;

public class Doors {

    private final BoundingBox area;
    private final Material doorMaterial = Material.SMOOTH_SANDSTONE;

    public Doors(BoundingBox box) {
        this.area = box;
    }

    public void close(DoorsDirection direction) {
        int minY = Daedalus.getInstance().getStructureManager().getMaze().getOrigin().getBlockY();
        int maxY = minY + 20;
        World world = Bukkit.getWorld(Daedalus.WORLD_NAME);
        switch (direction) {
            case NORTH:
                int minX = (int) (area.getMinX() + ((area.getWidthX() - 7) / 2.0));
                int maxX = minX + 7;
                int z = (int) area.getMinZ() - 1;

                for (int x = minX; x < maxX; x++) {
                    for (int y = minY; y < maxY; y++) {
                        assert world != null;
                        world.getBlockAt(x, y, z).setType(doorMaterial);
                    }
                }
                break;

            case EAST:
                int minZ = (int) (area.getMinZ() + ((area.getWidthZ() - 7) / 2.0));
                int maxZ = minZ + 7;
                int x = (int) area.getMaxX();

                for (z = minZ; z < maxZ; z++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(doorMaterial);
                    }
                }
                break;

            case WEST:
                minZ = (int) (area.getMinZ() + ((area.getWidthZ() - 7) / 2.0));
                maxZ = minZ + 7;
                x = (int) area.getMinX() - 1;

                for (z = minZ; z < maxZ; z++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(doorMaterial);
                    }
                }
                break;

            case SOUTH:
                minX = (int) (area.getMinX() + ((area.getWidthX() - 7) / 2.0));
                maxX = minX + 7;
                z = (int) area.getMaxZ();

                for (x = minX; x < maxX; x++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(doorMaterial);
                    }
                }
                break;

            case ALL:
                minX = (int) (area.getMinX() + ((area.getWidthX() - 7) / 2.0));
                maxX = minX + 7;
                z = (int) area.getMinZ() - 1;

                for (x = minX; x < maxX; x++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(doorMaterial);
                    }
                }

                minZ = (int) (area.getMinZ() + ((area.getWidthZ() - 7) / 2.0));
                maxZ = minZ + 7;
                x = (int) area.getMaxX();

                for (z = minZ; z < maxZ; z++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(doorMaterial);
                    }
                }

                minZ = (int) (area.getMinZ() + ((area.getWidthZ() - 7) / 2.0));
                maxZ = minZ + 7;
                x = (int) area.getMinX() - 1;

                for (z = minZ; z < maxZ; z++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(doorMaterial);
                    }
                }

                minX = (int) (area.getMinX() + ((area.getWidthX() - 7) / 2.0));
                maxX = minX + 7;
                z = (int) area.getMaxZ();

                for (x = minX; x < maxX; x++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(doorMaterial);
                    }
                }
            default:
                return;
        }
    }

    public void open(DoorsDirection direction) {
        int minY = Daedalus.getInstance().getStructureManager().getMaze().getOrigin().getBlockY();
        int maxY = minY + 20;
        World world = Bukkit.getWorld(Daedalus.WORLD_NAME);
        switch (direction) {
            case NORTH:
                int minX = (int) (area.getMinX() + ((area.getWidthX() - 7) / 2.0));
                int maxX = minX + 7;
                int z = (int) area.getMinZ() - 1;

                for (int x = minX; x < maxX; x++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }
                break;

            case EAST:
                int minZ = (int) (area.getMinZ() + ((area.getWidthZ() - 7) / 2.0));
                int maxZ = minZ + 7;
                int x = (int) area.getMaxX();

                for (z = minZ; z < maxZ; z++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }
                break;

            case WEST:
                minZ = (int) (area.getMinZ() + ((area.getWidthZ() - 7) / 2.0));
                maxZ = minZ + 7;
                x = (int) area.getMinX() - 1;

                for (z = minZ; z < maxZ; z++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }
                break;

            case SOUTH:
                minX = (int) (area.getMinX() + ((area.getWidthX() - 7) / 2.0));
                maxX = minX + 7;
                z = (int) area.getMaxZ();

                for (x = minX; x < maxX; x++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }
                break;

            case ALL:
                minX = (int) (area.getMinX() + ((area.getWidthX() - 7) / 2.0));
                maxX = minX + 7;
                z = (int) area.getMinZ() - 1;

                for (x = minX; x < maxX; x++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }

                minZ = (int) (area.getMinZ() + ((area.getWidthZ() - 7) / 2.0));
                maxZ = minZ + 7;
                x = (int) area.getMaxX();

                for (z = minZ; z < maxZ; z++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }

                minZ = (int) (area.getMinZ() + ((area.getWidthZ() - 7) / 2.0));
                maxZ = minZ + 7;
                x = (int) area.getMinX() - 1;

                for (z = minZ; z < maxZ; z++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }

                minX = (int) (area.getMinX() + ((area.getWidthX() - 7) / 2.0));
                maxX = minX + 7;
                z = (int) area.getMaxZ();

                for (x = minX; x < maxX; x++) {
                    for (int y = minY; y < maxY; y++) {
                        world.getBlockAt(x, y, z).setType(Material.AIR);
                    }
                }


            default:
                return;
        }
    }
}
