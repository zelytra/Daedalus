package fr.zelytra.daedalus.events.running.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerEatGoldenApple implements Listener {

    @EventHandler
    public void onGoldenApple(PlayerItemConsumeEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning() && e.getItem().getType() == Material.GOLDEN_APPLE && !GameSettings.ABSORPTION) {
            Bukkit.getScheduler().runTaskLater(Daedalus.getInstance(), () -> {
                e.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
            }, 1);
        }
    }
}
