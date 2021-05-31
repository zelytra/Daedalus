package fr.zelytra.daedalus.events.running.environnement.gods;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Artemis;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.structure.Structure;
import fr.zelytra.daedalus.managers.structure.StructureType;
import fr.zelytra.daedalus.utils.Message;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;

import java.util.Map;

public class ArtemisHandler implements Listener {
    private final Material invocationBlock = Material.LODESTONE;
    private final CustomMaterial invocMaterial = CustomMaterial.ARTEMIS_TOTEM;

    @EventHandler
    public void playerInteract(PlayerInteractEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if ((e.getHand() == EquipmentSlot.HAND && CustomItemStack.hasCustomItemInMainHand(invocMaterial.getName(), e.getPlayer())) || (e.getHand() == EquipmentSlot.OFF_HAND && CustomItemStack.hasCustomItemInOffHand(invocMaterial.getName(), e.getPlayer()))) {
                    if (e.getClickedBlock().getType() == invocationBlock) {
                        Player player = e.getPlayer();
                        for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {
                            if (entry.getKey().contains(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ()) && entry.getValue().getType() == StructureType.TEMPLE && entry.getValue().getGod() == GodsEnum.ARTEMIS) {
                                try {
                                    Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
                                    if (playerFaction.getGod() != null) {
                                        player.sendMessage(Message.getPlayerPrefixe() + "§cYou cannot summon more than one god.");
                                        return;
                                    }
                                    playerFaction.setGod(player, GodsEnum.ARTEMIS);
                                    new Artemis(playerFaction);
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
    public void playerInteract(ProjectileHitEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getHitEntity() == null || e.getEntity().getShooter() == null) {
                return;
            }
            if (e.getEntityType() == EntityType.ARROW && e.getEntity().getShooter() instanceof Player && e.getHitEntity() instanceof Player) {
                Player shooter = (Player) e.getEntity().getShooter();
                try {
                    Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(shooter);
                    if (playerFaction.getGod() != null && playerFaction.getGodsEnum() == GodsEnum.ARTEMIS && playerFaction.getGod() == shooter) {
                        Player target = (Player) e.getHitEntity();
                        double distance = Math.sqrt(Math.pow((shooter.getLocation().getX() - target.getLocation().getX()), 2) + Math.pow((shooter.getLocation().getZ() - target.getLocation().getZ()), 2));
                        if (distance >= 15.0) {
                            shooter.setAbsorptionAmount(shooter.getAbsorptionAmount() + 2.0);
                            Bukkit.getScheduler().runTaskLaterAsynchronously(Daedalus.getInstance(), () -> {
                                if (shooter.getAbsorptionAmount() < 2.0) {
                                    shooter.setAbsorptionAmount(0);
                                } else {
                                    shooter.setAbsorptionAmount(shooter.getAbsorptionAmount() - 2.0);
                                }
                            }, 600);
                        }
                    }
                } catch (Exception exception) {
                    System.out.println("ERROR team not found");
                }
            }
        }
    }

    private void vfx(Player player) {
        Bukkit.broadcastMessage("§9§l☾ Artemis has appeared in the maze ☽");
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
