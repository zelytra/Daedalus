package fr.zelytra.daedalus.events.running.environnement.gods;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Minotaure;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.structure.Structure;
import fr.zelytra.daedalus.managers.structure.StructureType;
import fr.zelytra.daedalus.utils.Message;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MinotaureHandler implements Listener {
    private final Material invocationBlock = Material.LODESTONE;
    private final CustomMaterial invocMaterial = CustomMaterial.MINOTAUR_TOTEM;
    private static ArrayList<Player> growlList = new ArrayList<>();

    @EventHandler
    public void playerInteract(PlayerInteractEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if ((e.getHand() == EquipmentSlot.HAND && CustomItemStack.hasCustomItemInMainHand(invocMaterial.getName(), e.getPlayer())) || (e.getHand() == EquipmentSlot.OFF_HAND && CustomItemStack.hasCustomItemInOffHand(invocMaterial.getName(), e.getPlayer()))) {
                    if (e.getClickedBlock().getType() == invocationBlock) {
                        Player player = e.getPlayer();
                        for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {
                            if (entry.getKey().contains(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ()) && entry.getValue().getType() == StructureType.BASE) {
                                try {
                                    Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
                                    if (playerFaction.getGod() != null) {
                                        player.sendMessage(Message.getPlayerPrefixe() + "§cYou cannot summon more than one god.");
                                        return;
                                    }
                                    playerFaction.setGod(player, GodsEnum.MINOTAURE);
                                    new Minotaure(playerFaction);
                                    growlTask();
                                    vfx(player);
                                    removeHeldItem(e, invocMaterial);
                                    e.getClickedBlock().setType(Material.CHISELED_STONE_BRICKS);
                                } catch (Exception exception) {
                                    exception.printStackTrace();
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

    public void growlTask() {
        Bukkit.getScheduler().runTaskTimer(Daedalus.getInstance(), () -> {
            for (Faction team : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
                if (team.getGodsEnum() != GodsEnum.MINOTAURE) {
                    continue;
                }
                for (Player player : team.getPlayerList()) {
                    if (player == null) {
                        continue;
                    }
                    List<Entity> entities = player.getNearbyEntities(50, 50, 50);
                    for (Entity e : entities) {
                        if (e instanceof Player) {
                            Player target = ((Player) e).getPlayer();
                            Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(target);
                            if (growlList.contains(player)) {
                                return;
                            }

                            if (playerFaction.getGodsEnum() == null) {
                                growlHandler(player);
                                return;
                            }
                            if (playerFaction.getGodsEnum() != null && playerFaction.getGodsEnum() != GodsEnum.MINOTAURE) {
                                if (playerFaction.getGod().getUniqueId() != target.getUniqueId()) {
                                    growlHandler(player);
                                    return;
                                }
                            }
                        }
                    }
                    if (growlList.contains(player)) {
                        growlList.remove(player);
                    }
                }
            }
        }, 0, 50);
    }

    private void growlHandler(Player player) {
        player.sendMessage("§7There is a smell of pulpit not far from there");
        player.playSound(player.getLocation(), Sound.ENTITY_WOLF_GROWL, 10, 0.8f);
        growlList.add(player);
    }


    private void vfx(Player player) {
        Bukkit.broadcastMessage("§9§l♞ Minotaure has appeared in the maze ♞");
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
