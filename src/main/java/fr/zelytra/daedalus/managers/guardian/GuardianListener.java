package fr.zelytra.daedalus.managers.guardian;

import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class GuardianListener implements Listener {

	@EventHandler
	public void onGuardianDamage(EntityDamageEvent e) {
		if (e.getEntityType() != EntityType.VINDICATOR) {
			return;
		}
		if (!Guardian.isGuardian((LivingEntity) e.getEntity())) {
			return;
		}
		Guardian guardian = Guardian.getGuardianFromList((LivingEntity) e.getEntity());
		guardian.getBossBar().setProgress((guardian.getEntity().getHealth()) / guardian.getHealth());
	}

	@EventHandler
	public void onGuardianDeath(EntityDeathEvent e) {
		if (e.getEntityType() != EntityType.VINDICATOR) {
			return;
		}
		if (!Guardian.isGuardian(e.getEntity())) {
			return;
		}
		Guardian guardian = Guardian.getGuardianFromList(e.getEntity());
		if (guardian == null) {
			return;
		}
		guardian.death();
		e.setDroppedExp(150);
		e.getDrops().removeAll(e.getDrops());
		e.getDrops().add(new CustomItemStack(CustomMaterial.DIVINE_HEART, 1).getItem());
		e.getDrops().add(new ItemStack(Material.GOLDEN_APPLE, (ThreadLocalRandom.current().nextInt(1, 7))));
	}
}
