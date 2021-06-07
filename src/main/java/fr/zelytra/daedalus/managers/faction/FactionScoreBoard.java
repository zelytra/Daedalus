package fr.zelytra.daedalus.managers.faction;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.game.time.TimeManager;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class FactionScoreBoard {
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final Faction faction;
    private Team factionTeam;

    public FactionScoreBoard(Faction faction) {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = scoreboard.registerNewObjective(faction.getType().getName(), "dummy", "§6§lDAEDALUS");
        this.faction = faction;
        this.factionTeam = registerAllTeams();
        initialize();
    }


    public void update(TimeManager timeManager) {
        for (Player player : faction.getPlayerList()) {
            if (player.getScoreboard().getObjective(faction.getType().getName()).getDisplaySlot() != DisplaySlot.SIDEBAR)
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }


        scoreboard.getTeam("episode").setPrefix("Episode §6: §a" + timeManager.getEpisode());
        scoreboard.getTeam("timer").setPrefix("Timer §6: §a" + timeManager.getTimer());
        scoreboard.getTeam("border").setPrefix("Border §6: §a" + (Daedalus.getInstance().getStructureManager().getShrinkManager().getWorkloadThread() == null ? "§cNull" : Daedalus.getInstance().getStructureManager().getShrinkManager().getBorderRadius()));
        scoreboard.getTeam("stateMino").setPrefix("State §6: " + (isMinotaur() ? "§aAlive" : "§cDead"));
        scoreboard.getTeam("alive").setPrefix("Alive §6: §a" + faction.getAliveCount() + "§6/§a" + faction.getPlayerList().size());

        if (faction.getGodsEnum() != null)
            scoreboard.getTeam("divinity").setPrefix("§6• §b" + faction.getGodsEnum().getName());
        else
            scoreboard.getTeam("divinity").setPrefix("§6• §cNone");

    }

    private void initialize() {
        for (Player player : faction.getPlayerList())
            player.setScoreboard(scoreboard);


        addLine("", "a", 13);

        addLine("Episode §6: §a1", "episode", 12);
        addLine("Timer §6: §a00:00", "timer", 11);

        addLine("", "b", 10);

        addLine("Alive §6: §a1§6/§a10", "alive", 9);

        addLine("", "c", 8);

        addLine("Team Divinity", "text", 7);
        addLine("§6• §cNone", "divinity", 6);

        addLine("", "d", 5);

        addLine("§6• §lMINOTAUR§6 •", "textMino", 4);
        addLine("State §6: §aAlive", "stateMino", 3);

        addLine("", "e", 2);

        addLine("Border §6: §a600", "border", 1);


    }

    /**
     * @param content String content of the line
     * @param tag     Unique tag of the line
     * @param line    Line number
     */
    private void addLine(String content, String tag, int line) {
        Team team = scoreboard.registerNewTeam(tag);
        team.addEntry(getUID());
        team.setPrefix(content);
        objective.getScore(getUID()).setScore(line);

    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    private String getUID() {
        StringBuilder uid = new StringBuilder("§r");
        for (Team ignored : scoreboard.getTeams()) {
            uid.append("§r");
        }
        return uid.toString();
    }

    private Team registerAllTeams() {
        Team returnTeam = null;
        for (FactionsEnum factionsEnum : FactionsEnum.values()) {
            Team team = scoreboard.registerNewTeam(factionsEnum.getTag());
            team.setAllowFriendlyFire(GameSettings.FRIENDLY_FIRE);
            team.setColor(factionsEnum.getChatColor());
            team.setPrefix(factionsEnum.getPrefix());
            team.setSuffix(factionsEnum.getSuffix());
            team.setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
            team.setOption(org.bukkit.scoreboard.Team.Option.DEATH_MESSAGE_VISIBILITY, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
            team.setOption(org.bukkit.scoreboard.Team.Option.NAME_TAG_VISIBILITY, org.bukkit.scoreboard.Team.OptionStatus.ALWAYS);
            if (factionsEnum == faction.getType())
                returnTeam = team;
        }
        return returnTeam;
    }


    public static void addEntry(Player player, FactionsEnum factionsEnum) {
        for (Faction faction : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
            faction.getFactionScoreBoard().getScoreboard().getTeam(factionsEnum.getTag()).addEntry(player.getName());
        }
    }

    public static void removeEntry(Player player, FactionsEnum factionsEnum) {
        for (Faction faction : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
            faction.getFactionScoreBoard().getScoreboard().getTeam(factionsEnum.getTag()).removeEntry(player.getName());
        }
    }

    public static void setScoreboardsForPlayer() {
        for (Faction faction : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
            for (Player player : faction.getPlayerList())
                player.setScoreboard(faction.getFactionScoreBoard().scoreboard);
        }
    }

    private boolean isMinotaur() {
        for (Faction team : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
            if (team.getGodsEnum() == GodsEnum.MINOTAURE && team.getGod() != null)
                return true;
        }
        return false;
    }


}
