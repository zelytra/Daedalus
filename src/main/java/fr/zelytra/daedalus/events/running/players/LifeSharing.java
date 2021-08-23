package fr.zelytra.daedalus.events.running.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.time.TimeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;

public class LifeSharing implements Listener {
    private static List<String> callBack = new ArrayList<>();
    private final int episode = 4;

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();

        if (callBack.contains(player.getName())) {
            callBack.remove(player.getName());
            return;
        }
        Faction faction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);

        if (TimeManager.episode >= episode && faction.getGod() == null && faction.getGodsEnum() == null) {
            for (Player p : faction.getPlayerList())
                if (p.getName() != player.getName()) {

                    if (p.getHealth() <= 2.0)
                        continue;

                    callBack.add(p.getName());
                    p.damage(e.getFinalDamage() / 2.0);
                }
        }
    }
}
