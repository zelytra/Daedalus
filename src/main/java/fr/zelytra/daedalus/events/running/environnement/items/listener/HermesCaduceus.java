package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HermesCaduceus implements Listener {

    int itemCooldown = 45;
    int effectRadius = 10;

    @EventHandler
    public void onItemUse(CustomItemUseEvent e) {
        if (e.getMaterial() != CustomMaterial.HERMES_CADUCEUS) return;

        try {
            Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(e.getPlayer());

            Collection<Entity> nearbyEntities = e.getPlayer().getWorld().getNearbyEntities(e.getPlayer().getLocation(), effectRadius, effectRadius, effectRadius);
            Collection<Entity> toStrike = new ArrayList<>();
            for (Entity entity : nearbyEntities) {
                if (entity instanceof Player && ((Player) entity).getGameMode() == GameMode.SURVIVAL) {
                    Player target = (Player) entity;
                    Faction targetPlayerTeam = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(target);
                    if (targetPlayerTeam.getType() == playerFaction.getType()) {
                        continue;
                    }
                    toStrike.add(entity);
                }
            }
            if (toStrike.isEmpty()) {
                e.getPlayer().sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("god.noPlayerToCurse"));
                return;
            }
            //Cooldown check
            if (!Cooldown.cooldownCheck(e.getPlayer(), CustomMaterial.HERMES_CADUCEUS.getName())) {
                return;
            }
            new Cooldown(e.getPlayer(), itemCooldown, CustomMaterial.HERMES_CADUCEUS.getName());

            for (Player player : Bukkit.getOnlinePlayers())
                player.playSound(e.getPlayer().getLocation(),Sound.ENTITY_ILLUSIONER_CAST_SPELL,2,1);

            for (Entity entity : toStrike) {
                if (entity instanceof LivingEntity) {
                    List<ItemStack> hotBar = new ArrayList<>();

                    for (int x = 0; x <= 8; x++)
                        hotBar.add(((Player) entity).getInventory().getContents()[x]);

                    Collections.shuffle(hotBar);

                    for (int x = 0; x <= 8; x++)
                        ((Player) entity).getInventory().setItem(x, hotBar.get(x));

                    ((Player) entity).playSound(entity.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                    entity.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("god.hermesActive"));

                }
            }

        } catch (Exception exception) {
            System.out.println("ERROR team not found");
        }

    }
}
