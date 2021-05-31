package fr.zelytra.daedalus.managers.faction;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.builders.guiBuilder.VisualItemStack;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Faction {

    private HashMap<String, PlayerStatus> factionMembers;
    private FactionsEnum factionsEnum;
    private FactionScoreBoard factionScoreBoard;
    private Team minecraftTeam;
    private GodsEnum godsEnum;
    private String god;

    public Faction(FactionsEnum factionsEnum, Scoreboard commonScoreboard) {
        this.factionsEnum = factionsEnum;
        this.factionMembers = new HashMap<>();
        this.factionScoreBoard = new FactionScoreBoard(this);
        this.minecraftTeam = commonScoreboard.registerNewTeam(this.factionsEnum.getTag());
        build();
    }

    private void build() {
        //TODO Dynamic friendly fire setting
        minecraftTeam.setAllowFriendlyFire(GameSettings.FRIENDLY_FIRE);
        minecraftTeam.setColor(factionsEnum.getChatColor());
        minecraftTeam.setPrefix(factionsEnum.getPrefix());
        minecraftTeam.setSuffix(factionsEnum.getSuffix());
        minecraftTeam.setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
        minecraftTeam.setOption(org.bukkit.scoreboard.Team.Option.DEATH_MESSAGE_VISIBILITY, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
        minecraftTeam.setOption(org.bukkit.scoreboard.Team.Option.NAME_TAG_VISIBILITY, org.bukkit.scoreboard.Team.OptionStatus.ALWAYS);

    }

    public void add(Player player) {
        Faction otherFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player);
        if (otherFaction != null){
            if(otherFaction.getType() != factionsEnum){
                otherFaction.remove(player);
            }else {
                this.remove(player);
                Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(FactionsEnum.SPECTATOR).add(player);
                return;
            }

        }



        factionMembers.put(player.getName(), PlayerStatus.ALIVE);
        player.setPlayerListName(factionsEnum.getPrefix() + player.getName() + factionsEnum.getSuffix());
        minecraftTeam.addEntry(player.getName());
        setScoreboardForPlayers();
        joinTeamFX(player, factionsEnum);
    }

    private void remove(Player player) {
        factionMembers.remove(player.getName());
        player.setPlayerListName(player.getName());
        minecraftTeam.removeEntry(player.getName());
        setScoreboardForPlayers();
    }

    public Player getGod() {
        return Bukkit.getPlayer(god);
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

    private void setScoreboardForPlayers() {
        for (Player player : Bukkit.getOnlinePlayers())
            player.setScoreboard(minecraftTeam.getScoreboard());

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
        player.sendMessage(Message.getPlayerPrefixe() + "§8You joined " + factionsEnum.getPrefix() + factionsEnum.getName() + " §8team");
        player.getInventory().setItem(8, new VisualItemStack(factionsEnum.getBanner(), "§7Team selection", false).getItem());
    }
}
