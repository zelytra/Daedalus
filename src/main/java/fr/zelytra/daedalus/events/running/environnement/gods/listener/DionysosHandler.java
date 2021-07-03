package fr.zelytra.daedalus.events.running.environnement.gods.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Dionysos;
import fr.zelytra.daedalus.managers.loottable.LootsEnum;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.concurrent.ThreadLocalRandom;

public class DionysosHandler implements Listener {

    @EventHandler
    public void playerInteract(GodSpawnEvent e) {

        if (e.getGod() == GodsEnum.DIONYSUS) {

            e.getFaction().setGod(e.getPlayer(), GodsEnum.DIONYSUS);
            new Dionysos(e.getFaction());
            vfx(e.getPlayer());
        }

    }

    @EventHandler
    public void onDrinking(PlayerItemConsumeEvent e) {
        if (e.getItem().getType() == Material.POTION) {
            try {
                Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(e.getPlayer());
                if (playerFaction.getGod() == null) {
                    return;
                }
                if (playerFaction.getGodsEnum() == GodsEnum.DIONYSUS) {
                    int random = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                    if (random <= 80) {
                        random = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                        if (random <= 25) {
                            e.getPlayer().addPotionEffect(LootsEnum.NAUSEA.getPotionEffect());
                        }
                        random = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                        if (random <= 65) {
                            int loot = ThreadLocalRandom.current().nextInt(0, Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.DIONYSUS.getName() + "_tier1").getLootsEnum().size());
                            e.getPlayer().addPotionEffect(Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.DIONYSUS.getName() + "_tier1").getLootsEnum().get(loot).getPotionEffect());
                        } else {
                            int loot = ThreadLocalRandom.current().nextInt(0, Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.DIONYSUS.getName() + "_tier2").getLootsEnum().size());
                            e.getPlayer().addPotionEffect(Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.DIONYSUS.getName() + "_tier2").getLootsEnum().get(loot).getPotionEffect());
                        }

                    } else {
                        e.getPlayer().addPotionEffect(LootsEnum.NAUSEA.getPotionEffect());
                    }
                }
            } catch (Exception exception) {
                System.out.println("ERROR team not found");
            }
        }
    }


    private void vfx(Player player) {
        Bukkit.broadcastMessage("§5§l✌ Dionysos has appeared in the maze ✌");
        Utils.runTotemDisplay(player);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 10, 0.1f);
        }
    }

}
