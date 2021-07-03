package fr.zelytra.daedalus.events.running.environnement.gods.events;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.structure.Structure;
import fr.zelytra.daedalus.managers.structure.StructureType;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;

import java.util.Map;
import java.util.Objects;

public class GodSpawnHandler implements Listener {
    private final Material invocationBlock = Material.LODESTONE;

    @EventHandler
    public void onPlayerInvocGod(PlayerInteractEvent e) {

        if (!Daedalus.getInstance().getGameManager().isRunning()) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (Objects.requireNonNull(e.getClickedBlock()).getType() != invocationBlock) return;
        if (e.getHand() != EquipmentSlot.HAND) return;

        Player player = e.getPlayer();

        for (GodsEnum god : GodsEnum.values()) {
            if (e.getHand() == EquipmentSlot.HAND && !CustomItemStack.hasCustomItemInMainHand(god.getTotem().getName(), player))
                continue;

            for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {

                if (!entry.getKey().contains(e.getClickedBlock().getX(), e.getClickedBlock().getY(), e.getClickedBlock().getZ()))
                    continue;

                if (god == GodsEnum.MINOTAURE) {
                    if (entry.getValue().getType() != StructureType.BASE) continue;
                }else {
                    if (entry.getValue().getType() != StructureType.TEMPLE) continue;
                }
                if (entry.getValue().getGod() != god) continue;


                Faction playerFaction;
                try {
                    playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    System.out.println("ERROR team not found");
                    return;
                }

                if (playerFaction.getGod() != null) {
                    player.sendMessage(Message.getPlayerPrefixe() + "§cYou cannot summon more than one god.");
                    return;
                }

                GodSpawnEvent event = new GodSpawnEvent(god, playerFaction, player);
                Bukkit.getPluginManager().callEvent(event);

                removeHeldItem(e, god.getTotem());
                e.getClickedBlock().setType(Material.CHISELED_STONE_BRICKS);
                return;

            }
            player.sendMessage(Message.getPlayerPrefixe() + "§cYou cannot summon this god here.");
            return;
        }
    }

    private void removeHeldItem(PlayerInteractEvent e, CustomMaterial material) {
        switch (Objects.requireNonNull(e.getHand())) {
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
