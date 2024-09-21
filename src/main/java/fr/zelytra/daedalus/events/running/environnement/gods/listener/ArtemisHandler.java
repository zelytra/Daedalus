package fr.zelytra.daedalus.events.running.environnement.gods.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Artemis;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArtemisHandler implements Listener {

	@EventHandler
	public void playerInteract(GodSpawnEvent e) {

		if (e.getGod() == GodsEnum.ARTEMIS) {

			e.getFaction().setGod(e.getPlayer(), GodsEnum.ARTEMIS);
			new Artemis(e.getFaction());
			vfx(e.getPlayer());
		}
	}

	@EventHandler
	public void playerInteract(ProjectileHitEvent e) {
		if (Daedalus.getInstance().getGameManager().isRunning()) {
			if (e.getHitEntity() == null || e.getEntity().getShooter() == null) {
				return;
			}
			if (e.getEntityType() == EntityType.ARROW && e.getEntity().getShooter() instanceof Player
					&& e.getHitEntity() instanceof Player) {
				Player shooter = (Player) e.getEntity().getShooter();
				try {
					Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager()
							.getFactionOf(shooter);
					if (playerFaction.getGod() != null && playerFaction.getGodsEnum() == GodsEnum.ARTEMIS
							&& playerFaction.getGod() == shooter) {
						Player target = (Player) e.getHitEntity();
						double distance = Math
								.sqrt(Math.pow((shooter.getLocation().getX() - target.getLocation().getX()), 2)
										+ Math.pow((shooter.getLocation().getZ() - target.getLocation().getZ()), 2));
						if (distance >= 15.0) {
							shooter.setAbsorptionAmount(shooter.getAbsorptionAmount() + 2.0);
							Bukkit.getScheduler().runTaskLaterAsynchronously(Daedalus.getInstance(), () -> {
								if (shooter.getAbsorptionAmount() < 2.0) {
									shooter.setAbsorptionAmount(0);
								} else {
									shooter.setAbsorptionAmount(shooter.getAbsorptionAmount() - 2.0);
								}
							}, 600);
						}
					}
				} catch (Exception exception) {
					System.out.println("ERROR team not found");
				}
			}
		}
	}

	private void vfx(Player player) {
		Bukkit.broadcastMessage(GameSettings.LANG.textOf("godSpawn.artemis"));
		Utils.runTotemDisplay(player);
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 10, 0.1f);
		}
	}

	@EventHandler
	public void onWolfAttack(EntityDamageByEntityEvent e) {
		if (Daedalus.getInstance().getGameManager().isRunning() && e.getDamager() instanceof Wolf
				&& e.getEntity() instanceof Player) {

			Player player = (Player) e.getEntity();
			Faction faction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);

			if (faction.getGodsEnum() != null && faction.getGodsEnum() == GodsEnum.ARTEMIS)
				e.setCancelled(true);
		}
	}
}
