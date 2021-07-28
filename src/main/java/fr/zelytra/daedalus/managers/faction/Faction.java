package fr.zelytra.daedalus.managers.faction;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.guiBuilder.VisualItemStack;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Faction {

    private HashMap<String, PlayerStatus> factionMembers;
    private FactionsEnum factionsEnum;
    private FactionScoreBoard factionScoreBoard;
    private GodsEnum godsEnum;
    private String god;

    public Faction(FactionsEnum factionsEnum) {
        this.factionsEnum = factionsEnum;
        this.factionMembers = new HashMap<>();
        this.factionScoreBoard = new FactionScoreBoard(this);

    }


    public void add(Player player) {
        Faction otherFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
        if (otherFaction != null) {
            if (otherFaction.getType() != factionsEnum) {
                otherFaction.remove(player);
            } else {
                this.remove(player);
                Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(FactionsEnum.SPECTATOR).add(player);
                return;
            }

        }

        factionMembers.put(player.getName(), PlayerStatus.ALIVE);
        player.setPlayerListName(factionsEnum.getPrefix() + player.getName() + factionsEnum.getSuffix());
        FactionScoreBoard.addEntry(player, factionsEnum);
        FactionScoreBoard.setScoreboardsForPlayer();
        joinTeamFX(player, factionsEnum);
    }

    private void remove(Player player) {
        factionMembers.remove(player.getName());
        player.setPlayerListName(player.getName());
        FactionScoreBoard.removeEntry(player, factionsEnum);
        FactionScoreBoard.setScoreboardsForPlayer();
    }

    @Nullable
    public Player getGod() {
        if (god != null)
            return Bukkit.getPlayer(god);
        return null;
    }

    public FactionScoreBoard getFactionScoreBoard() {
        return factionScoreBoard;
    }

    public GodsEnum getGodsEnum() {
        return godsEnum;
    }

    public boolean has(Player player) {
        return factionMembers.containsKey(player.getName());
    }

    public boolean is(FactionsEnum factionsEnum) {
        return this.factionsEnum == factionsEnum;
    }

    public boolean isAlive(Player player) {
        if (factionMembers.containsKey(player.getName())) {
            return factionMembers.get(player.getName()) == PlayerStatus.ALIVE;
        }
        return false;
    }

    public ArrayList<Player> getPlayerList() {
        List<Player> playerList = new ArrayList<>();
        for (String name : factionMembers.keySet()) {
            if (Bukkit.getPlayer(name) != null)
                playerList.add(Bukkit.getPlayer(name));
        }
        return (ArrayList<Player>) playerList;
    }

    public int getPlayerAmount(){
        return factionMembers.size();
    }

    public int getAliveCount() {
        int count = 0;
        for (PlayerStatus status : factionMembers.values()) {
            if (status == PlayerStatus.ALIVE)
                count++;
        }
        return count;
    }


    public FactionsEnum getType() {
        return factionsEnum;
    }

    public void setPlayerStatus(Player player, PlayerStatus status) {
        if (factionMembers.containsKey(player.getName())) {
            factionMembers.put(player.getName(), status);
        }
    }

    public void setGod(Player player, GodsEnum godsEnum) {
        god = player.getName();
        this.godsEnum = godsEnum;
    }

    public void removeGod() {
        god = null;
    }

    private void joinTeamFX(Player player, FactionsEnum factionsEnum) {
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("team.joinPrefix") + factionsEnum.getPrefix() + factionsEnum.getName() + GameSettings.LANG.textOf("team.joinSuffix"));
        if (Daedalus.getInstance().getGameManager().isWaiting()) {
            if (player.isOp())
                player.getInventory().setItem(8, new VisualItemStack(factionsEnum.getBanner(), "§7Team selection", false).getItem());
            else
                player.getInventory().setItem(4, new VisualItemStack(factionsEnum.getBanner(), "§7Team selection", false).getItem());
        }
    }
}
