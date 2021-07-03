package fr.zelytra.daedalus.events.running.environnement.gods.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Athena;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AthenaHandler implements Listener {

    private final List<EntityType> blackList = new ArrayList<>();

    {
        blackList.add(EntityType.SHEEP);
        blackList.add(EntityType.CHICKEN);
        blackList.add(EntityType.COW);
        blackList.add(EntityType.PIG);
    }

    @EventHandler
    public void playerInteract(GodSpawnEvent e) {

        if (e.getGod() == GodsEnum.ATHENA) {

            e.getFaction().setGod(e.getPlayer(), GodsEnum.ATHENA);
            new Athena(e.getFaction());
            vfx(e.getPlayer());
        }

    }

    @EventHandler
    public void entityKillEvent(EntityDeathEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getEntity().getKiller() == null) {
                return;
            }
            try {
                Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(e.getEntity().getKiller());
                if (playerFaction.getGodsEnum() == GodsEnum.ATHENA) {
                    switch (e.getEntity().getType()) {
                        case PLAYER:
                            int random = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                            if (random <= 60) {//lvl 3
                                int loot = ThreadLocalRandom.current().nextInt(0, Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.ATHENA.getName() + "_tier3").getLootsEnum().size());
                                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.ATHENA.getName() + "_tier3").getLootsEnum().get(loot).getItem());

                            } else if (random > 60 && random <= 85) {//lvl 2
                                int loot = ThreadLocalRandom.current().nextInt(0, Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.ATHENA.getName() + "_tier2").getLootsEnum().size());
                                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.ATHENA.getName() + "_tier2").getLootsEnum().get(loot).getItem());
                            } else if (random > 85) {//lvl 1
                                int loot = ThreadLocalRandom.current().nextInt(0, Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.ATHENA.getName() + "_tier1").getLootsEnum().size());
                                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.ATHENA.getName() + "_tier1").getLootsEnum().get(loot).getItem());
                            }
                            break;
                        default:
                            if (blackList.contains(e.getEntityType()))
                                break;

                            random = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                            if (random == 1) {//lvl 3
                                int loot = ThreadLocalRandom.current().nextInt(0, Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.ATHENA.getName() + "_tier3").getLootsEnum().size());
                                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.ATHENA.getName() + "_tier3").getLootsEnum().get(loot).getItem());
                            } else if (random > 1 && random <= 5) {//lvl 2
                                int loot = ThreadLocalRandom.current().nextInt(0, Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.ATHENA.getName() + "_tier2").getLootsEnum().size());
                                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.ATHENA.getName() + "_tier2").getLootsEnum().get(loot).getItem());
                            } else if (random > 5 && random <= 20) {//lvl 1
                                int loot = ThreadLocalRandom.current().nextInt(0, Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.ATHENA.getName() + "_tier1").getLootsEnum().size());
                                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Daedalus.getInstance().getStructureManager().getLootTableManager().getByName(GodsEnum.ATHENA.getName() + "_tier1").getLootsEnum().get(loot).getItem());
                            }
                            break;
                    }
                }
            } catch (Exception exception) {
                System.out.println("ERROR team not found");
            }
        }
    }


    private void vfx(Player player) {
        Bukkit.broadcastMessage("§a§l➹ Athena has appeared in the maze ➹");
        Utils.runTotemDisplay(player);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 10, 0.1f);
        }
    }

}
