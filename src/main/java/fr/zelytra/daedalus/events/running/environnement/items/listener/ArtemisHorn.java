package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import org.bukkit.*;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArtemisHorn implements Listener {
	private static final NamespacedKey artemisKey = new NamespacedKey(Daedalus.getInstance(), "artemis");

	@EventHandler
	public void onRightClick(CustomItemUseEvent e) {
		int itemCooldown = 30;
		int wolfNumber = 5;

		if (e.getMaterial() != CustomMaterial.ARTEMIS_HORN)
			return;

		Player player = e.getPlayer();

		// Cooldown check
		if (!Cooldown.cooldownCheck(player, CustomMaterial.ARTEMIS_HORN.getName())) {
			return;
		}
		new Cooldown(player, itemCooldown, CustomMaterial.ARTEMIS_HORN.getName());

		List<Entity> wolfs = e.getPlayer().getNearbyEntities(30, 30, 30);

		for (Entity entity : wolfs)
			if (entity instanceof Wolf)
				entity.remove();

		try {
			Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
			// Item action
			for (int x = 1; x <= wolfNumber; x++) {
				Location spawnLoc = player.getLocation();
				spawnLoc.setY(spawnLoc.getY() + 1);
				spawnLoc.setX(new Random().nextInt((int) ((spawnLoc.getX() + 2) - (spawnLoc.getX() - 2)))
						+ (spawnLoc.getX() - 2));
				spawnLoc.setZ(new Random().nextInt((int) ((spawnLoc.getZ() + 2) - (spawnLoc.getZ() - 2)))
						+ (spawnLoc.getZ() - 2));
				Entity entity = player.getWorld().spawnEntity(spawnLoc, EntityType.WOLF);
				Wolf wolf = (Wolf) entity;
				wolf.setAngry(true);
				wolf.setOwner(playerFaction.getGod());
				wolf.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);
				wolf.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 9999999, 1, true, true));
				PersistentDataContainer pdc = entity.getPersistentDataContainer();
				pdc.set(artemisKey, PersistentDataType.STRING, playerFaction.getType().getName());

				Location location = wolf.getLocation();
				location.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, location, 300, 0.1, 0.1, 0.1);
			}

			for (Player p : Bukkit.getOnlinePlayers())
				p.playSound(e.getPlayer().getLocation(), Sound.EVENT_RAID_HORN, 1000, 1);

		} catch (Exception exception) {
			System.out.println("ERROR team not found");
		}
	}

	@EventHandler
	public void mobTarget(EntityTargetEvent e) {
		int radiusTarget = 10;
		Entity entity = e.getEntity();
		if (entity.getType() == EntityType.WOLF) {
			PersistentDataContainer pdc = entity.getPersistentDataContainer();
			if (pdc.has(artemisKey, PersistentDataType.STRING)) {

				Collection<Entity> nearbyEntities = entity.getWorld().getNearbyEntities(entity.getLocation(),
						radiusTarget, radiusTarget, radiusTarget);
				ArrayList<Entity> toTargetPlayer = new ArrayList<>();
				ArrayList<Entity> toTarget = new ArrayList<>();

				for (Entity target : nearbyEntities) {
					if (target instanceof Wolf)
						continue;

					if (target instanceof Player) {
						Player targetedPlayer = (Player) target;
						if (targetedPlayer.getGameMode() != GameMode.SURVIVAL)
							continue;

						Faction targetPlayerTeam = Daedalus.getInstance().getGameManager().getFactionManager()
								.getFactionOf(targetedPlayer);
						if (!targetPlayerTeam.getType().getName()
								.equals(pdc.get(artemisKey, PersistentDataType.STRING))) {
							toTargetPlayer.add(target);
						}
					} else {
						toTarget.add(target);
					}
				}

				if (toTargetPlayer.isEmpty()) {
					if (toTarget.isEmpty()) {
						e.setCancelled(true);
						return;
					}
					e.setTarget(toTarget.get((int) (Math.random() * toTarget.size())));
				} else {
					e.setTarget(toTargetPlayer.get((int) (Math.random() * toTargetPlayer.size())));
				}
			}
		}
	}
}
