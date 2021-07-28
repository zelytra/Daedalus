package fr.zelytra.daedalus.events.running.players.DeathHandler.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.players.DeathHandler.events.PartielDeathEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PartielDeathListener implements Listener {
    private final List<CustomMaterial> whitelist = new ArrayList<>();

    {
        whitelist.add(CustomMaterial.DIVINE_FRAGMENT);
        whitelist.add(CustomMaterial.DIVINE_HEART);
        whitelist.add(CustomMaterial.DIVINE_TRACKER);
    }

    @EventHandler
    public void onPartielDeath(PartielDeathEvent e) {

        Player player = e.getPlayer();

        for (int x = 0; x < player.getInventory().getContents().length; x++) {
            ItemStack item = player.getInventory().getContents()[x];
            if ((!CustomItemStack.hasTag(item) || whitelist.contains(CustomItemStack.getCustomMaterial(item))) && item != null) {
                player.getWorld().dropItem(player.getLocation(), item);
                player.getInventory().getContents()[x].setType(Material.AIR);
            }
        }

        player.setSaturation(20.0f);
        player.getInventory().clear();

        respawnFX(e.getEvent());
        Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
        player.teleport(playerFaction.getType().getSpawn());
    }

    private void respawnFX(EntityDamageEvent e) {
        Player player = (Player) e.getEntity();
        player.getWorld().strikeLightningEffect(player.getLocation());
        Faction faction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
        switch (e.getCause()) {
            case ENTITY_ATTACK:
                if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player)
                    Bukkit.broadcastMessage(faction.getType().getPrefix() +
                            Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf((Player) ((EntityDamageByEntityEvent) e).getDamager()).getType().getPrefix() +
                            ((EntityDamageByEntityEvent) e).getDamager().getName() +
                            GameSettings.LANG.textOf("death.defaultByPlayerPrefix") + e.getEntity().getName()
                            + GameSettings.LANG.textOf("death.defaultByPlayerSuffix"));
                else
                    Bukkit.broadcastMessage(GameSettings.LANG.textOf("death.defaultPrefix") + faction.getType().getPrefix() + e.getEntity().getName() + GameSettings.LANG.textOf("death.defaultSuffixe"));
                break;
            default:
                Bukkit.broadcastMessage(GameSettings.LANG.textOf("death.defaultPrefix") + faction.getType().getPrefix() + e.getEntity().getName() + GameSettings.LANG.textOf("death.defaultSuffixe"));
                break;
        }
    }
}
