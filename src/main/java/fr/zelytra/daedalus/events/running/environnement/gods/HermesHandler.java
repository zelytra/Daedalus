package fr.zelytra.daedalus.events.running.environnement.gods;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Hermes;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.team.Team;
import fr.zelytra.daedalus.utils.Message;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class HermesHandler implements Listener {
    private final Material invocationBlock = Material.LODESTONE;
    private final CustomMaterial invocMaterial = CustomMaterial.HERMES_TOTEM;

    @EventHandler
    public void playerInteract(PlayerInteractEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if ((e.getHand() == EquipmentSlot.HAND && CustomItemStack.hasCustomItemInMainHand(invocMaterial.getName(), e.getPlayer())) || (e.getHand() == EquipmentSlot.OFF_HAND && CustomItemStack.hasCustomItemInOffHand(invocMaterial.getName(), e.getPlayer()))) {
                    if (e.getClickedBlock().getType() == invocationBlock) {
                        Player player = e.getPlayer();
                        try {
                            Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(player.getUniqueId());
                            if (playerTeam.getGod() != null) {
                                player.sendMessage(Message.getPlayerPrefixe() + "§cYou cannot summon more than one god.");
                                return;
                            }
                            playerTeam.setGod(player, GodsEnum.HERMES);
                            new Hermes(playerTeam);
                            vfx(e.getPlayer());
                            removeHeldItem(e, invocMaterial);
                            doubleJump();
                        } catch (Exception exception) {
                            System.out.println("ERROR team not found");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerFallDamage(EntityDamageEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getEntity() instanceof Player) {
                Player player = ((Player) e.getEntity());
                if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    try {
                        Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(player.getUniqueId());
                        if (playerTeam.getGodEnum() != null && playerTeam.getGodEnum() == GodsEnum.HERMES && playerTeam.getGod().getUniqueId() == e.getEntity().getUniqueId()) {
                            e.setCancelled(true);
                        }
                    } catch (Exception exception) {
                        System.out.println("ERROR team not found");
                    }
                }
            }
        }
    }

    @EventHandler
    public void setFlyOnJump(PlayerToggleFlightEvent e) {
        Player jumper = e.getPlayer();

        if (e.isFlying() && jumper.getGameMode() != GameMode.CREATIVE && jumper.getGameMode() != GameMode.SPECTATOR) {
            jumper.setFlying(false);
            Vector jump = jumper.getLocation().getDirection().multiply(0.2).setY(0.8);
            jumper.setVelocity(jumper.getVelocity().add(jump));
            e.setCancelled(true);
            jumper.setAllowFlight(false);

        }

    }

    private void vfx(Player player) {
        Bukkit.broadcastMessage("§b§l✉ Hermes as appear in the maze ✉");
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

    public void doubleJump() {
        Bukkit.getScheduler().runTaskTimer(Daedalus.getInstance(), () -> {
            for (Team team : Daedalus.getInstance().getGameManager().getTeamManager().getTeamList().values()) {
                if (team.getGodEnum() != GodsEnum.HERMES) {
                    continue;
                }

                if (team.getGod().isOnGround()) {
                team.getGod().setAllowFlight(true);
                }
            }

        }, 0, 13);


    }
}
