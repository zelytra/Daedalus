package fr.zelytra.daedalus.events.running.environnement.mobCutclean;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MobCutClean implements Listener {

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            return;
        }
        if (GameSettings.CUT_CLEAN && FoodEnum.containMob(e.getEntityType())) {
            FoodEnum animal = FoodEnum.getByMob(e.getEntityType());
            List<ItemStack> loots = new ArrayList<>();
            for (ItemStack item : e.getDrops()) {
                if (item.getType() == animal.getRaw()) {
                    loots.add(new ItemStack(animal.getCooked(), item.getAmount()));
                    continue;
                }
                loots.add(item);
            }
            e.getDrops().clear();
            e.getDrops().addAll(loots);
        }
    }
}
