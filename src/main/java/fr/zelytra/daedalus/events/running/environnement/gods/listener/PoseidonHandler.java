package fr.zelytra.daedalus.events.running.environnement.gods.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Poseidon;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoseidonHandler implements Listener {

    @EventHandler
    public void playerInteract(GodSpawnEvent e) {

        if (e.getGod() == GodsEnum.POSEIDON) {

            e.getFaction().setGod(e.getPlayer(), GodsEnum.POSEIDON);
            new Poseidon(e.getFaction());
            playerInWater();
            vfx(e.getPlayer());

        }

    }


    public void playerInWater() {
        Bukkit.getScheduler().runTaskTimer(Daedalus.getInstance(), () -> {
            for (Faction team : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
                if (team.getGodsEnum() != GodsEnum.POSEIDON) {
                    continue;
                }
                for (Player player : team.getPlayerList()) {
                    if (player == null)
                        continue;

                    if (team.getGod() != null && player.getName() != team.getGod().getName())
                        continue;

                    if (player.getLocation().getBlock().getType() == Material.WATER) {
                        if (player.getPotionEffect(PotionEffectType.REGENERATION) != null)
                            continue;
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 1, false, true, true));
                    }
                }
            }
        }, 0, 10);


    }

    private void vfx(Player player) {
        Bukkit.broadcastMessage(GameSettings.LANG.textOf("godSpawn.poseidon"));
        Utils.runTotemDisplay(player);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 10, 0.1f);
        }
    }

}
