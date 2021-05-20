package fr.zelytra.daedalus.managers.guardian;

import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

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
    }

    @EventHandler
    public void onGuardianAttackEvent(EntityDamageByEntityEvent e) {


        if (e.getDamager().getType() != EntityType.VINDICATOR) {
            return;
        }
        if (!Guardian.isGuardian((LivingEntity) e.getDamager())) {
            return;
        }
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        Guardian guardian = Guardian.getGuardianFromList((LivingEntity) e.getDamager());



    }

}
