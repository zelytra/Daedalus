package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.guiBuilder.VisualItemStack;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.faction.FactionsEnum;
import fr.zelytra.daedalus.managers.game.GameStatesEnum;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {

        final Player p = e.getPlayer();

        e.setJoinMessage(null);
        Utils.setTabFX(Daedalus.getInstance().getServer().getOnlinePlayers().size());
        preparePlayer(p, Daedalus.getInstance().getGameManager().getState());

    }


    private void preparePlayer(Player p, GameStatesEnum state) {

        Bukkit.broadcastMessage("§8[§a+§8] §f" + p.getName());
        Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(p);

        for (CustomMaterial material : CustomMaterial.values()) {
            NamespacedKey itemKey = new NamespacedKey(Daedalus.getInstance(), material.getName());
            p.discoverRecipe(itemKey);
        }

        switch (state) {
            case WAIT:
                p.setGameMode(GameMode.ADVENTURE);
                p.setFoodLevel(20);
                p.setSaturation(20);
                p.setMaxHealth(20);
                p.setLevel(0);
                p.getInventory().clear();
                p.teleport(new Location(Bukkit.getWorld(Daedalus.WORLD_NAME), 667.5, 168.5, 623.5, -90, 0));
                for (PotionEffect potionEffectType : p.getActivePotionEffects())
                    p.removePotionEffect(potionEffectType.getType());

                if (playerFaction == null)
                    Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(FactionsEnum.SPECTATOR).add(p);

                if (p.isOp()) {
                    if (playerFaction != null)
                        p.getInventory().setItem(8, new VisualItemStack(playerFaction.getType().getBanner(), "§7Team selection", false).getItem());
                    else
                        p.getInventory().setItem(8, new VisualItemStack(Material.WHITE_BANNER, "§7Team selection", false).getItem());

                    p.getInventory().setItem(0, new VisualItemStack(Material.COMPARATOR, "§7Game settings", false).getItem());
                    p.getInventory().setItem(4, new VisualItemStack(Material.BELL, "§6Start game", false, "§7Click here to start your game with the actual configuration").getItem());
                } else
                    p.getInventory().setItem(4, new VisualItemStack(Material.WHITE_BANNER, "§7Team selection", false).getItem());

                break;

            case RUNNING:
                if (playerFaction != null)
                    p.setScoreboard(playerFaction.getFactionScoreBoard().getScoreboard());
                else {
                    Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(FactionsEnum.SPECTATOR).add(p);
                    p.teleport(FactionsEnum.SPECTATOR.getSpawn());
                    p.setGameMode(GameMode.SPECTATOR);
                }
                break;

            case FINISHED:

                if (playerFaction == null) {
                    Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(FactionsEnum.SPECTATOR).add(p);
                    p.teleport(FactionsEnum.SPECTATOR.getSpawn());
                    p.setGameMode(GameMode.SPECTATOR);
                }
                break;
        }
    }
}