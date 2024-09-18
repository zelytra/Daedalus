package fr.zelytra.daedalus.managers.faction;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FactionRandomizer {

    private final ArrayList<Player> players;
    private final FactionManager factionManager;

    public FactionRandomizer(List<Player> players) {

        this.players = new ArrayList<>();
        this.players.addAll(players);
        this.factionManager = Daedalus.getInstance().getGameManager().getFactionManager();


    }

    public void rand() {
        Bukkit.getScheduler().runTaskAsynchronously(Daedalus.getInstance(), () -> {
            int totalPlayerToRand = players.size();
            int amountByFaction = totalPlayerToRand / 5;

            if (amountByFaction < 1) {

                for (Player p : Bukkit.getOnlinePlayers())
                    if (p.isOp()) {
                        p.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.notEnoughPlayer"));
                        return;
                    }

            } else {

                for (Player player : players)
                    Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(FactionsEnum.SPECTATOR).add(player);

                while (!players.isEmpty())
                    for (Faction faction : factionManager.getFactionList()) {

                        if (faction.getType() == FactionsEnum.SPECTATOR) continue;
                        if (players.isEmpty()) break;

                        Player drawPlayer = players.get(new Random().nextInt(players.size()));
                        faction.add(drawPlayer);
                        players.remove(drawPlayer);

                    }
            }
        });


    }
}
