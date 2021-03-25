package fr.zelytra.daedalus.events.waiting.players;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.ItemBuilder;
import fr.zelytra.daedalus.managers.game.GameStatesEnum;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {

        final Player p = e.getPlayer();

        e.setJoinMessage(null);
        preparePlayer(p, Daedalus.getInstance().getGameManager().getState());

    }


    private void preparePlayer(Player p, GameStatesEnum state) {

        Bukkit.broadcastMessage("§7[§a+§7] §f" + p.getName());

        if (state.equals(GameStatesEnum.WAIT)) {
            p.setGameMode(GameMode.ADVENTURE);
            p.setFoodLevel(20);
            p.setHealth(p.getMaxHealth());

            if (Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()) != null) {

                setupTeam(p);

                switch (Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()).getTeamColor()) {

                    case RED: {
                        p.getInventory().setItem(0, new ItemBuilder(Material.RED_BANNER, "§7Team selection").getItemStack());
                        break;
                    }

                    case BLUE: {
                        p.getInventory().setItem(0, new ItemBuilder(Material.BLUE_BANNER, "§7Team selection").getItemStack());
                        break;
                    }

                    case GREEN: {
                        p.getInventory().setItem(0, new ItemBuilder(Material.GREEN_BANNER, "§7Team selection").getItemStack());
                        break;
                    }

                    case YELLOW: {
                        p.getInventory().setItem(0, new ItemBuilder(Material.YELLOW_BANNER, "§7Team selection").getItemStack());
                        break;
                    }

                    case GRAY: {
                        p.getInventory().setItem(0, new ItemBuilder(Material.GRAY_BANNER, "§7Team selection").getItemStack());
                        break;
                    }

                }

            } else
                p.getInventory().setItem(0, new ItemBuilder(Material.WHITE_BANNER, "§7Team selection").getItemStack());

            p.getInventory().setItem(8, new ItemBuilder(Material.COMPARATOR, "§7Game settings").getSettings());

            // TP TO SPAWN
        } else if (state.equals(GameStatesEnum.RUNNING)) {

        } else {
            p.setGameMode(GameMode.SPECTATOR);
        }

        p.setPlayerListHeader("\n§7[§6Daedalus§7]\n");
        p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16);
        if (Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()) != null) {

            setupTeam(p);

        }


    }

    private void setupTeam(Player p) {
        p.setDisplayName(Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()).getPrefix() + p.getName() + Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()).getSuffix());
        p.setPlayerListName(Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()).getPrefix() + p.getName() + Daedalus.getInstance().getGameManager().getTeamManager().getTeamOfPlayer(p.getUniqueId()).getSuffix());
    }
}