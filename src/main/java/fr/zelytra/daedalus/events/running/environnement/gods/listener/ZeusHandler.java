package fr.zelytra.daedalus.events.running.environnement.gods.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Zeus;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class ZeusHandler implements Listener {

	@EventHandler
	public void playerInteract(GodSpawnEvent e) {

		if (e.getGod() == GodsEnum.ZEUS) {

			e.getFaction().setGod(e.getPlayer(), GodsEnum.ZEUS);
			new Zeus(e.getFaction());
			vfx(e.getPlayer());
		}
	}

	@EventHandler
	public void playerFallDamage(EntityDamageEvent e) {
		if (Daedalus.getInstance().getGameManager().isRunning()) {
			if (e.getEntity() instanceof Player) {
				Player player = ((Player) e.getEntity());
				if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
					try {
						Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager()
								.getFactionOf(player);
						if (playerFaction.getGodsEnum() != null && playerFaction.getGodsEnum() == GodsEnum.ZEUS) {
							e.setCancelled(true);
						}
					} catch (Exception exception) {
						System.out.println("ERROR team not found");
					}
				}
			}
		}
	}

	private void vfx(Player player) {
		Bukkit.broadcastMessage(GameSettings.LANG.textOf("godSpawn.zeus"));
		Utils.runTotemDisplay(player);
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 10, 0.1f);
		}
	}
}
