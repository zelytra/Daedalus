package fr.zelytra.daedalus.events.running.environnement.gods;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Dionysos;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.loottable.LootsEnum;
import fr.zelytra.daedalus.managers.structure.Structure;
import fr.zelytra.daedalus.managers.structure.StructureType;
import fr.zelytra.daedalus.managers.team.Team;
import fr.zelytra.daedalus.utils.Message;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class DionysosHandler implements Listener {
    private final Material invocationBlock = Material.LODESTONE;
    private final CustomMaterial invocMaterial = CustomMaterial.DIONYSOS_TOTEM;

    @EventHandler
    public void playerInteract(PlayerInteractEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if ((e.getHand() == EquipmentSlot.HAND && CustomItemStack.hasCustomItemInMainHand(invocMaterial.getName(), e.getPlayer())) || (e.getHand() == EquipmentSlot.OFF_HAND && CustomItemStack.hasCustomItemInOffHand(invocMaterial.getName(), e.getPlayer()))) {
                    if (e.getClickedBlock().getType() == invocationBlock) {
                        Player player = e.getPlayer();
                        for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {
                            if (entry.getKey().contains(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ()) && entry.getValue().getType() == StructureType.TEMPLE && entry.getValue().getGod() == GodsEnum.DIONYSUS) {
                                try {
                                    Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(player.getUniqueId());
                                    if (playerTeam.getGod() != null) {
                                        player.sendMessage(Message.getPlayerPrefixe() + "§cYou cannot summon more than one god.");
                                        return;
                                    }
                                    playerTeam.setGod(player, GodsEnum.DIONYSUS);
                                    new Dionysos(playerTeam);
                                    vfx(player);
                                    removeHeldItem(e, invocMaterial);
                                    e.getClickedBlock().setType(Material.CHISELED_STONE_BRICKS);
                                } catch (Exception exception) {
                                    System.out.println("ERROR team not found");
                                }
                                return;
                            }
                        }
                        player.sendMessage(Message.getPlayerPrefixe() + "§cYou cannot summon this god here.");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDrinking(PlayerItemConsumeEvent e) {
        if (e.getItem().getType() == Material.POTION) {
            try {
                Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(e.getPlayer().getUniqueId());
                if (playerTeam.getGod() == null) {
                    return;
                }
                if (playerTeam.getGodEnum() == GodsEnum.DIONYSUS) {
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

    private void removeHeldItem(PlayerInteractEvent e, CustomMaterial material) {
        switch (e.getHand()) {
            case HAND:
                if (CustomItemStack.hasCustomItemInMainHand(material.getName(), e.getPlayer()))
                    e.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                break;
            case OFF_HAND:
                if (CustomItemStack.hasCustomItemInOffHand(material.getName(), e.getPlayer()))
                    e.getPlayer().getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                break;
            default:
                break;
        }
    }

}
