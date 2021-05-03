package fr.zelytra.daedalus.events.running.environnement.gods;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Poseidon;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
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
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class PoseidonHandler implements Listener {
    private final Material invocationBlock = Material.LODESTONE;
    private final CustomMaterial invocMaterial = CustomMaterial.POSEIDON_TOTEM;

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
                            playerTeam.setGod(player, GodsEnum.POSEIDON);
                            new Poseidon(playerTeam);
                            playerInWater();
                            vfx(player);
                            removeHeldItem(e, invocMaterial);
                        } catch (Exception exception) {
                            System.out.println("ERROR team not found");
                        }
                    }
                }
            }
        }
    }


    public void playerInWater() {
        Bukkit.getScheduler().runTaskTimer(Daedalus.getInstance(), () -> {
            for (Team team : Daedalus.getInstance().getGameManager().getTeamManager().getTeamList()) {
                if (team.getGodEnum() != GodsEnum.POSEIDON) {
                    continue;
                }
                for (UUID uuid : team.getPlayerList()) {
                    Player player = Bukkit.getPlayer(uuid);
                    if (player.getLocation().getBlock().getType() == Material.WATER) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 0, false, true, true));
                    }
                }
            }
        }, 0, 10);


    }

    private void vfx(Player player) {
        Bukkit.broadcastMessage("§9§l⚓ Posseidon as appear in the maze ⚓");
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