package fr.zelytra.daedalus.managers.guardian;

import fr.zelytra.daedalus.Daedalus;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class GuardianBossBar {
	private final int TIME = 3; // in seconds
	private int taskID;
	private boolean running = false;

	public GuardianBossBar() {
		startListener();
	}

	private void startListener() {
		running = true;
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Daedalus.getInstance(), () -> {
			for (Guardian guardian : Guardian.getGuardianList()) {
				guardian.customAttack();
				List<Entity> targets = guardian.getEntity().getNearbyEntities(50, 50, 50);

				for (Entity target : targets) {

					if (target instanceof Player) {

						if (getDistance(target.getLocation(), guardian.getEntity().getLocation()) <= 30)
							guardian.getBossBar().addPlayer((Player) target);
						else {
							if (guardian.getBossBar().getPlayers().contains((Player) target)) {
								guardian.getBossBar().removePlayer((Player) target);
							}
						}
					}
				}
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (targets.contains(p))
						continue;
					else
						guardian.getBossBar().removePlayer(p);
				}
			}
		}, 0, TIME * 20);
	}

	public void stop() {
		if (running) {
			Bukkit.getScheduler().cancelTask(taskID);
		}
	}

	private double getDistance(Location loc1, Location loc2) {
		double deltaX = loc1.getX() - loc2.getX();
		double deltaZ = (loc1.getZ() - loc2.getZ());
		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
	}
}
