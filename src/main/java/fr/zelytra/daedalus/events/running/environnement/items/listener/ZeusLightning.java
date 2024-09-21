package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.utils.Message;
import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ZeusLightning implements Listener {

	@EventHandler
	public void onRightClick(CustomItemUseEvent e) {
		int itemCooldown = 15;
		int radius = 10;

		if (e.getMaterial() != CustomMaterial.ZEUS_LIGHTNING)
			return;

		Player player = e.getPlayer();

		// Item action
		try {
			Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);

			Collection<Entity> nearbyEntities = player.getWorld().getNearbyEntities(player.getLocation(), radius,
					radius, radius);
			Collection<Entity> toStrike = new ArrayList<>();
			for (Entity entity : nearbyEntities) {
				if (entity instanceof Player && ((Player) entity).getGameMode() == GameMode.SURVIVAL) {
					Player target = (Player) entity;
					Faction targetPlayerTeam = Daedalus.getInstance().getGameManager().getFactionManager()
							.getFactionOf(target);
					if (targetPlayerTeam.getType() == playerFaction.getType()) {
						continue;
					}
					toStrike.add(entity);
				} else if (entity instanceof LivingEntity && !(entity instanceof Player))
					toStrike.add(entity);
			}
			if (toStrike.isEmpty()) {
				player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("god.noPlayerToStrike"));
				return;
			}
			// Cooldown check
			if (!Cooldown.cooldownCheck(player, CustomMaterial.ZEUS_LIGHTNING.getName())) {
				return;
			}
			new Cooldown(player, itemCooldown, CustomMaterial.ZEUS_LIGHTNING.getName());

			for (Entity entity : toStrike) {
				if (entity instanceof LivingEntity) {
					player.getWorld().strikeLightningEffect(entity.getLocation());

					if (player.getHealth() <= 5.0)
						player.setHealth(2.0);
					else
						((LivingEntity) entity).damage(5.0);
				}
			}

		} catch (Exception exception) {
			System.out.println("ERROR team not found");
		}
	}
}
