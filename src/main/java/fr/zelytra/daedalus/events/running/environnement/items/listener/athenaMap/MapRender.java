package fr.zelytra.daedalus.events.running.environnement.items.listener.athenaMap;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.maze.Maze;
import fr.zelytra.daedalus.managers.maze.Vector2;
import fr.zelytra.daedalus.managers.structure.GridBlockEnum;
import java.util.Collection;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.map.*;
import org.jetbrains.annotations.NotNull;

public class MapRender extends MapRenderer {

	@SneakyThrows
	@Override
	public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player) {
		Maze maze = Daedalus.getInstance().getStructureManager().getMaze();
		Vector2 matrixCoordinate = new Vector2((int) (map.getCenterX() - maze.getOrigin().getX() + 1),
				(int) (map.getCenterZ() - maze.getOrigin().getZ() + 1));
		final int canvasSize = 128;
		for (int pixelX = 0; pixelX < canvasSize; pixelX++) {
			for (int pixelZ = 0; pixelZ < canvasSize; pixelZ++) {
				try {
					canvas.setPixelColor(pixelX, pixelZ,
							GridBlockEnum.getMapPalette(
									maze.getMaze()[matrixCoordinate.x - ((canvasSize / 2) - pixelX)][matrixCoordinate.z
											- ((canvasSize / 2) - pixelZ)]));
				} catch (Exception ignored) {
					canvas.setPixelColor(pixelX, pixelZ, GridBlockEnum.WALL.getMapPalette());
				}
			}
		}
		Collection<Entity> entityList = new Location(Bukkit.getWorld(Daedalus.WORLD_NAME), map.getCenterX(), 100,
				map.getCenterZ()).getNearbyEntities(128, 255, 128);
		MapCursorCollection cursors = new MapCursorCollection();
		for (Entity entity : entityList) {
			if (entity instanceof Player) {
			}
		}
		canvas.setCursors(cursors);
	}
}
