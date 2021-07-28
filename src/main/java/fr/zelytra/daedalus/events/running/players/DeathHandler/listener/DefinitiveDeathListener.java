package fr.zelytra.daedalus.events.running.players.DeathHandler.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.players.DeathHandler.events.DefinitiveDeathEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.faction.PlayerStatus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class DefinitiveDeathListener implements Listener {
    private final List<CustomMaterial> whitelist = new ArrayList<>();

    {
        whitelist.add(CustomMaterial.DIVINE_FRAGMENT);
        whitelist.add(CustomMaterial.DIVINE_HEART);
        whitelist.add(CustomMaterial.DIVINE_TRACKER);
    }

    @EventHandler
    public void onDefinitiveDeath(DefinitiveDeathEvent e) {
        Player player = e.getPlayer();

        player.setGameMode(GameMode.SPECTATOR);
        for (ItemStack content : player.getInventory().getContents()) {
            if ((!CustomItemStack.hasTag(content) || whitelist.contains(CustomItemStack.getCustomMaterial(content))) && content != null) {
                player.getWorld().dropItem(player.getLocation(), content);
            }
        }

        player.getInventory().clear();

        for (PotionEffect effect : player.getActivePotionEffects())
            player.removePotionEffect(effect.getType());

        player.setMaxHealth(20.0);
        deathFX(e.getEvent());

        Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
        playerFaction.setPlayerStatus(player, PlayerStatus.DEAD);
        if (playerFaction.getGod() != null && player.getUniqueId() == playerFaction.getGod().getUniqueId())
            playerFaction.removeGod();

    }

    private void deathFX(EntityDamageEvent e) {
        Player player = (Player) e.getEntity();
        player.getWorld().strikeLightningEffect(player.getLocation());
        Faction faction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);

        switch (e.getCause()) {
            case ENTITY_ATTACK:
                if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player)
                    Bukkit.broadcastMessage(faction.getType().getPrefix() + Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf((Player) ((EntityDamageByEntityEvent) e).getDamager()).getType().getPrefix() + ((EntityDamageByEntityEvent) e).getDamager().getName() + GameSettings.LANG.textOf("death.definitiveByPlayerPrefix") + e.getEntity().getName() + GameSettings.LANG.textOf("death.definitiveByPlayerPrefix=\" §8sent \"\n" +
                            "death.definitiveByPlayerSuffix"));
                else
                    Bukkit.broadcastMessage(faction.getType().getPrefix() + e.getEntity().getName() + GameSettings.LANG.textOf("death.definitive"));
                break;
            default:
                Bukkit.broadcastMessage(faction.getType().getPrefix() + e.getEntity().getName() + GameSettings.LANG.textOf("death.definitive"));
                break;
        }
    }


}
