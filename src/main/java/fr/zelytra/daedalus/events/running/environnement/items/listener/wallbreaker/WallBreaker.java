package fr.zelytra.daedalus.events.running.environnement.items.listener.wallbreaker;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.maze.Maze;
import fr.zelytra.daedalus.managers.maze.Vector2;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Random;

public class WallBreaker implements Listener {
    int itemCooldown = 5;

    @EventHandler
    public void onCustomItemUse(CustomItemUseEvent e) {
        if (e.getMaterial() != CustomMaterial.WALL_BREAKER) return;

        if (!Daedalus.getInstance().getGameManager().isRunning()) return;

        if (Daedalus.getInstance().getStructureManager().getStructuresPosition().isEmpty()) return;


        Maze maze = Daedalus.getInstance().getStructureManager().getMaze();
        if (maze == null) return;

        Player player = e.getPlayer();
        Block block = player.getTargetBlock(10);
        Vector2 matrixCoordinate = new Vector2((int) (block.getX() - maze.getOrigin().getX() + 1), (int) (block.getZ() - maze.getOrigin().getZ() + 1));

        if (maze.getMaze()[matrixCoordinate.x][matrixCoordinate.z] != 1) {
            player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("event.wallBreakerNotAWall"));
            return;
        }

        //Cooldown check
        if (!Cooldown.cooldownCheck(e.getPlayer(), CustomMaterial.WALL_BREAKER.getName())) return;
        new Cooldown(e.getPlayer(), itemCooldown, CustomMaterial.WALL_BREAKER.getName());

        for (WallShape shape : WallShape.values()) {
            if (block.getY() + shape.getY() < maze.getOrigin().getY())
                continue;

            switch (player.getFacing()) {
                case NORTH:
                case SOUTH:
                    matrixCoordinate = new Vector2((int) (block.getX() + shape.getX() - maze.getOrigin().getX() + 1), (int) (block.getZ() - maze.getOrigin().getZ() + 1));
                    if (maze.getMaze()[matrixCoordinate.x][matrixCoordinate.z] != 1)
                        continue;
                    Location target = new Location(player.getWorld(), block.getX() + shape.getX(), block.getY() + shape.getY(), block.getZ());
                    breakBlockTask(target, shape);
                    break;

                case EAST:
                case WEST:
                    matrixCoordinate = new Vector2((int) (block.getX() - maze.getOrigin().getX() + 1), (int) (block.getZ() + shape.getX() - maze.getOrigin().getZ() + 1));
                    if (maze.getMaze()[matrixCoordinate.x][matrixCoordinate.z] != 1)
                        continue;
                    target = new Location(player.getWorld(), block.getX(), block.getY() + shape.getY(), block.getZ() + shape.getX());
                    breakBlockTask(target, shape);
                    break;

            }
        }
        removeHeldItem(e.getEvent(), CustomMaterial.WALL_BREAKER);
    }

    private void breakBlockTask(Location target, WallShape wallShape) {

        BlockState blockState = target.getBlock().getState();

        if (Math.random() * 100 <= wallShape.getLuck()) {
            Bukkit.getScheduler().runTaskLater(Daedalus.getInstance(), () -> {

                target.getBlock().setType(Material.AIR);
                target.getWorld().spawnParticle(Particle.BLOCK_CRACK, target, 50, target.getBlock().getBlockData());

                for (Player p : Bukkit.getOnlinePlayers())
                    p.playSound(target, Sound.BLOCK_BASALT_BREAK, (float) 0.6, (float) 0.1);

                Bukkit.getScheduler().runTaskLater(Daedalus.getInstance(), () -> {

                    blockState.update(true);
                    target.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, target, 300, 1, 1, 1, 1);

                    for (Player p : Bukkit.getOnlinePlayers())
                        p.playSound(target, Sound.BLOCK_BASALT_PLACE, (float) 0.6, (float) 1);

                }, (long) wallShape.getTime() * 20 + new Random().nextInt(10));

            }, new Random().nextInt(10));
        }


    }

    private void removeHeldItem(PlayerInteractEvent e, CustomMaterial material) {

        switch (Objects.requireNonNull(e.getHand())) {
            case HAND:
                if (CustomItemStack.hasCustomItemInMainHand(material.getName(), e.getPlayer())) {
                    if (e.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) {
                        ItemStack newItem = e.getPlayer().getInventory().getItemInMainHand();
                        newItem.setAmount(newItem.getAmount() - 1);
                        e.getPlayer().getInventory().setItemInMainHand(newItem);

                    } else
                        e.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));


                }
                break;
            case OFF_HAND:
                if (CustomItemStack.hasCustomItemInOffHand(material.getName(), e.getPlayer())) {
                    if (e.getPlayer().getInventory().getItemInOffHand().getAmount() > 1) {
                        ItemStack newItem = e.getPlayer().getInventory().getItemInOffHand();
                        newItem.setAmount(newItem.getAmount() - 1);
                        e.getPlayer().getInventory().setItemInOffHand(newItem);
                        return;
                    } else
                        e.getPlayer().getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                }
                break;
            default:
                break;
        }
    }
}
