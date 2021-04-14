package fr.zelytra.daedalus.events.running.environnement.gods;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Zeus;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.team.Team;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class ZeusHandler implements Listener {
    private final Material invocationBlock = Material.LODESTONE;

    @EventHandler
    public void playerInteract(PlayerInteractEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if ((e.getHand() == EquipmentSlot.HAND && CustomItemStack.hasCustomItemInMainHand(CustomMaterial.ZEUS_TOTEM.getName(), e.getPlayer())) || (e.getHand() == EquipmentSlot.OFF_HAND && CustomItemStack.hasCustomItemInOffHand(CustomMaterial.ZEUS_TOTEM.getName(), e.getPlayer()))) {
                    if (e.getClickedBlock().getType() == invocationBlock) {
                        Player player = e.getPlayer();
                        try {
                            Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(player.getUniqueId());
                            if (playerTeam.getGod() != null) {
                                player.sendMessage(Message.getPlayerPrefixe() + "§cYou cannot summon more than one god.");
                                return;
                            }
                            playerTeam.setGod(player, GodsEnum.ZEUS);
                            Zeus zeus = new Zeus(playerTeam);
                            Bukkit.broadcastMessage("Zeus appear in the maze");
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
        if (e.getEntity() instanceof Player) {
            Player player = ((Player) e.getEntity());
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                try {
                    Team playerTeam = Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(player.getUniqueId());
                    if (playerTeam.getGodEnum() != null && playerTeam.getGodEnum() == GodsEnum.ZEUS) {
                        e.setCancelled(true);
                    }
                } catch (Exception exception) {
                    System.out.println("ERROR team not found");
                }
            }
        }
    }
}
