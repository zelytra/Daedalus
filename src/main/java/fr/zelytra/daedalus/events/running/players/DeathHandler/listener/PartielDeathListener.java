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

    private final List<Material> armorBlackList = new ArrayList<>();

    {
        armorBlackList.add(Material.NETHERITE_CHESTPLATE);
        armorBlackList.add(Material.NETHERITE_HELMET);
        armorBlackList.add(Material.NETHERITE_LEGGINGS);
        armorBlackList.add(Material.NETHERITE_BOOTS);
        armorBlackList.add(Material.NETHERITE_SWORD);
    }

    @EventHandler
    public void onPartielDeath(PartielDeathEvent e) {

        Player player = e.getPlayer();
        List<ItemStack> activeItems = new ArrayList<>();

        for (int x = 0; x < player.getInventory().getContents().length; x++) {

            ItemStack item = player.getInventory().getContents()[x];

            if (item != null && armorBlackList.contains(item.getType())) {

                player.getInventory().getContents()[x].setType(Material.AIR);
                player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.NETHERITE_SCRAP, 1));

            } else if ((!CustomItemStack.hasTag(item) || whitelist.contains(CustomItemStack.getCustomMaterial(item))) && item != null) {

                player.getWorld().dropItem(player.getLocation(), item);
                player.getInventory().getContents()[x].setType(Material.AIR);

            } else if (CustomItemStack.hasTag(item))
                activeItems.add(item);

        }

        player.setSaturation(20.0f);
        player.getInventory().clear();

        respawnFX(e.getEvent());
        Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
        player.teleport(playerFaction.getType().getSpawn());

        if (activeItems != null)
            for (ItemStack item : activeItems)
                player.getInventory().addItem(item);

    }

    private void respawnFX(EntityDamageEvent e) {
        Player player = (Player) e.getEntity();
        player.getWorld().strikeLightningEffect(player.getLocation());
        Faction faction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
        switch (e.getCause()) {
            case ENTITY_ATTACK:
                if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player)
                    Bukkit.broadcastMessage(faction.getType().getPrefix() +
                            e.getEntity().getName() +
                            GameSettings.LANG.textOf("death.defaultByPlayer") + Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf((Player) ((EntityDamageByEntityEvent) e).getDamager()).getType().getPrefix() +
                            ((EntityDamageByEntityEvent) e).getDamager().getName());
                else
                    Bukkit.broadcastMessage(faction.getType().getPrefix() + e.getEntity().getName() + GameSettings.LANG.textOf("death.default"));
                break;
            default:
                Bukkit.broadcastMessage(faction.getType().getPrefix() + e.getEntity().getName() + GameSettings.LANG.textOf("death.default"));
                break;
        }
    }
}
