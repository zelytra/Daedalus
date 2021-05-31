package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.guiBuilder.VisualItemStack;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.faction.FactionsEnum;
import fr.zelytra.daedalus.managers.game.GameStatesEnum;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {

        final Player p = e.getPlayer();

        e.setJoinMessage(null);
        Utils.setTabFX(p);
        preparePlayer(p, Daedalus.getInstance().getGameManager().getState());

    }


    private void preparePlayer(Player p, GameStatesEnum state) {

        Bukkit.broadcastMessage("§7[§a+§7] §f" + p.getName());
        Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(p);

        switch (state) {
            case WAIT:
                p.setGameMode(GameMode.ADVENTURE);
                p.setFoodLevel(20);
                p.setSaturation(20);
                p.setMaxHealth(20);
                p.setLevel(0);
                p.getInventory().clear();
                p.teleport(new Location(p.getWorld(), 669, 162, 675));
                p.removePotionEffect(PotionEffectType.NIGHT_VISION);

                if (playerFaction != null)
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