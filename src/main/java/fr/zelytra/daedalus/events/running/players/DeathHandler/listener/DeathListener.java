package fr.zelytra.daedalus.events.running.players.DeathHandler.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.events.running.players.DeathHandler.events.DefinitiveDeathEvent;
import fr.zelytra.daedalus.events.running.players.DeathHandler.events.PartielDeathEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.faction.FactionsEnum;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.projectiles.ProjectileSource;

public class DeathListener implements Listener {
	private final Daedalus daedalus = Daedalus.getInstance();
	public static boolean hasMinoSpawn = false;
	private boolean isMinoDead = false;

	@EventHandler
	public void onCustomDeath(EntityDamageEvent e) {

		if (!(e.getEntity() instanceof Player player))
			return;

		boolean isDead = player.getHealth() - e.getFinalDamage() <= 0;

		if (daedalus.getGameManager().isRunning() && isDead) {
			e.setCancelled(true);
			player.setHealth(player.getMaxHealth());
			boolean shrinkHasReachSpawn = daedalus.getStructureManager().getShrinkManager().getBorderRadius() <= 495;
			boolean killByAMino = false;

			if (e instanceof EntityDamageByEntityEvent) {
				EntityDamageByEntityEvent damageByEntityEvent = (EntityDamageByEntityEvent) e;
				ProjectileSource projectileSource = null;

				if (damageByEntityEvent.getDamager() instanceof Projectile)
					projectileSource = ((Projectile) damageByEntityEvent.getDamager()).getShooter();

				if (damageByEntityEvent.getDamager() instanceof Player) { // Kill by a player

					Player killer = (Player) damageByEntityEvent.getDamager();
					killByAMino = daedalus.getGameManager().getFactionManager().getFactionOf(killer)
							.getGodsEnum() == GodsEnum.MINOTAURE; // Tuer par un mino

				} else if (projectileSource != null && projectileSource instanceof Player) { // Kill by projectil

					Player killer = (Player) projectileSource;
					killByAMino = daedalus.getGameManager().getFactionManager().getFactionOf(killer)
							.getGodsEnum() == GodsEnum.MINOTAURE; // Tuer par un mino
				}
			}
			if (e.getCause() == EntityDamageEvent.DamageCause.FALL) { // Kill by falling
				if ((e.getEntity().getLastDamageCause()) instanceof EntityDamageByEntityEvent
						&& ((EntityDamageByEntityEvent) e.getEntity().getLastDamageCause())
								.getDamager() instanceof Player) {
					Player killer = (Player) ((EntityDamageByEntityEvent) e.getEntity().getLastDamageCause())
							.getDamager();
					killByAMino = daedalus.getGameManager().getFactionManager().getFactionOf(killer)
							.getGodsEnum() == GodsEnum.MINOTAURE; // Tuer par un mino
				}
			}

			boolean isMemberOfMino = daedalus.getGameManager().getFactionManager().getFactionOf(player)
					.getGodsEnum() == GodsEnum.MINOTAURE; // Quand on est un mino

			// Mino PVE partiel death
			if (isMemberOfMino && isPVEDeath(e)) {

				PartielDeathEvent event = new PartielDeathEvent(player, e);
				Bukkit.getPluginManager().callEvent(event);
				return;
			}

			if (hasMinoSpawn && !isMinoDead && daedalus.getGameManager().getFactionManager()
					.getFactionOf(FactionsEnum.MINOTAUR).getGod() != null) {
				if (player.getUniqueId() == daedalus.getGameManager().getFactionManager()
						.getFactionOf(FactionsEnum.MINOTAUR).getGod().getUniqueId()) {
					isMinoDead = true;
					minotaursDeathFX();
				}
			}

			if ((hasMinoSpawn && isMinoDead) || killByAMino || shrinkHasReachSpawn || isMemberOfMino) {

				DefinitiveDeathEvent event = new DefinitiveDeathEvent(player, e);
				Bukkit.getPluginManager().callEvent(event);
				winListener();

			} else {

				PartielDeathEvent event = new PartielDeathEvent(player, e);
				Bukkit.getPluginManager().callEvent(event);
			}
		}
	}

	private boolean isPVEDeath(EntityDamageEvent e) {

		ProjectileSource projectileSource = null;

		if (e instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent damageByEntityEvent = (EntityDamageByEntityEvent) e;
			if (damageByEntityEvent.getDamager() instanceof Projectile)
				projectileSource = ((Projectile) damageByEntityEvent.getDamager()).getShooter();

			if (projectileSource != null && projectileSource instanceof Player) // Shoot by a mob
				return false;

			if (damageByEntityEvent.getDamager() instanceof Player)
				return false;

			if (e.getCause() == EntityDamageEvent.DamageCause.FALL && e.getEntity().getLastDamageCause() != null
					&& ((EntityDamageByEntityEvent) e.getEntity().getLastDamageCause().getEntity())
							.getDamager() instanceof Player) // Kill by falling
				return false;
		}

		return true;
	}

	@EventHandler
	public void onMinoSpawn(GodSpawnEvent e) {
		if (e.getGod() == GodsEnum.MINOTAURE)
			hasMinoSpawn = true;
	}

	private void minotaursDeathFX() {
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(GameSettings.LANG.textOf("death.minotaur"));
		Bukkit.broadcastMessage("");
	}

	private void winListener() {
		Faction winner = null;
		int teamAliveCount = 0;
		if (daedalus.getGameManager().isRunning()) {
			for (Faction team : daedalus.getGameManager().getFactionManager().getFactionList()) {
				if (team.getType() == FactionsEnum.SPECTATOR) {
					continue;
				}
				int playerCount = 0;
				for (Player player : team.getPlayerList()) {
					if (team.isAlive(player) && player.isOnline()) {
						playerCount++;
					}
				}
				if (playerCount > 0) {
					winner = team;
					teamAliveCount++;
				}
			}
			if (teamAliveCount == 1) {
				daedalus.getGameManager().stop();
				winFX(winner);
			}
		}
	}

	private void winFX(Faction team) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendTitle(
					"§l" + team.getType().getChatColor() + team.getType().getName()
							+ GameSettings.LANG.textOf("event.victoryTitle"),
					GameSettings.LANG.textOf("event.victorySubTitle"), 5, 100, 5);
		}
	}
}
