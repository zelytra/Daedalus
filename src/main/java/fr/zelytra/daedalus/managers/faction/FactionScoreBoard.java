package fr.zelytra.daedalus.managers.faction;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.players.DeathHandler.listener.DeathListener;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.game.time.TimeManager;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class FactionScoreBoard {
  @Getter private final Scoreboard scoreboard;
  private final Objective objective;
  private final Faction faction;

  public FactionScoreBoard(Faction faction) {
    this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    this.objective =
        scoreboard.registerNewObjective(faction.getType().getName(), "dummy", "§6§lDAEDALUS");
    this.faction = faction;
    registerAllTeams();
    initialize();
  }

  public void update(TimeManager timeManager) {
    for (Player player : faction.getPlayerList()) {
      if (player.getScoreboard().getObjective(faction.getType().getName()).getDisplaySlot()
          != DisplaySlot.SIDEBAR) objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    scoreboard
        .getTeam("episode")
        .setPrefix(GameSettings.LANG.textOf("scoreboard.episode") + timeManager.getEpisode());
    scoreboard
        .getTeam("timer")
        .setPrefix(GameSettings.LANG.textOf("scoreboard.timer") + timeManager.getTimer());
    scoreboard
        .getTeam("border")
        .setPrefix(
            GameSettings.LANG.textOf("scoreboard.border")
                + (Daedalus.getInstance()
                            .getStructureManager()
                            .getShrinkManager()
                            .getWorkloadThread()
                        == null
                    ? "§cNull"
                    : Daedalus.getInstance()
                        .getStructureManager()
                        .getShrinkManager()
                        .getBorderRadius()));
    scoreboard
        .getTeam("stateMino")
        .setPrefix(
            GameSettings.LANG.textOf("scoreboard.stateMino")
                + (DeathListener.hasMinoSpawn
                    ? (isMinotaur()
                        ? GameSettings.LANG.textOf("scoreboard.stateMinoAlive")
                        : GameSettings.LANG.textOf("scoreboard.stateMinoDead"))
                    : "§6§k4269"));
    scoreboard
        .getTeam("alive")
        .setPrefix(
            GameSettings.LANG.textOf("scoreboard.alive")
                + getAlivePlayer()
                + "§6/§a"
                + getTotalPlayer());
    scoreboard.getTeam("text").setPrefix(GameSettings.LANG.textOf("scoreboard.teamDivinity"));
    if (faction.getGodsEnum() != null)
      scoreboard.getTeam("divinity").setPrefix("§6• §b" + faction.getGodsEnum().getName());
    else
      scoreboard.getTeam("divinity").setPrefix(GameSettings.LANG.textOf("scoreboard.noDivinity"));
  }

  private void initialize() {
    for (Player player : faction.getPlayerList()) player.setScoreboard(scoreboard);

    addLine("", "a", 13);

    addLine("", "episode", 12);
    addLine("", "timer", 11);

    addLine("", "b", 10);

    addLine("", "alive", 9);

    addLine("", "c", 8);

    addLine("", "text", 7);
    addLine("", "divinity", 6);

    addLine("", "d", 5);

    addLine("§6• §lMINOTAUR§6 •", "textMino", 4);
    addLine("", "stateMino", 3);

    addLine("", "e", 2);

    addLine("", "border", 1);
  }

  /**
   * @param content String content of the line
   * @param tag Unique tag of the line
   * @param line Line number
   */
  private void addLine(String content, String tag, int line) {
    Team team = scoreboard.registerNewTeam(tag);
    team.addEntry(getUID());
    team.setPrefix(content);
    objective.getScore(getUID()).setScore(line);
  }

  private String getUID() {
    StringBuilder uid = new StringBuilder("§r");
    for (Team ignored : scoreboard.getTeams()) {
      uid.append("§r");
    }
    return uid.toString();
  }

  private void registerAllTeams() {
    for (FactionsEnum factionsEnum : FactionsEnum.values()) {
      Team team = scoreboard.registerNewTeam(factionsEnum.getTag());
      team.setAllowFriendlyFire(GameSettings.FRIENDLY_FIRE);
      team.setColor(factionsEnum.getChatColor());
      team.setPrefix(factionsEnum.getPrefix());
      team.setSuffix(factionsEnum.getSuffix());
      team.setOption(
          org.bukkit.scoreboard.Team.Option.COLLISION_RULE,
          org.bukkit.scoreboard.Team.OptionStatus.NEVER);
      team.setOption(
          org.bukkit.scoreboard.Team.Option.DEATH_MESSAGE_VISIBILITY,
          org.bukkit.scoreboard.Team.OptionStatus.NEVER);
      team.setOption(
          org.bukkit.scoreboard.Team.Option.NAME_TAG_VISIBILITY,
          org.bukkit.scoreboard.Team.OptionStatus.ALWAYS);
    }
  }

  public static void addEntry(Player player, FactionsEnum factionsEnum) {
    for (Faction faction :
        Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
      faction
          .getFactionScoreBoard()
          .getScoreboard()
          .getTeam(factionsEnum.getTag())
          .addEntry(player.getName());
    }
  }

  public static void removeEntry(Player player, FactionsEnum factionsEnum) {
    for (Faction faction :
        Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
      faction
          .getFactionScoreBoard()
          .getScoreboard()
          .getTeam(factionsEnum.getTag())
          .removeEntry(player.getName());
    }
  }

  public static void setScoreboardsForPlayer() {
    for (Faction faction :
        Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
      for (Player player : faction.getPlayerList())
        player.setScoreboard(faction.getFactionScoreBoard().scoreboard);
    }
  }

  private boolean isMinotaur() {
    for (Faction team :
        Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
      if (team.getGodsEnum() == GodsEnum.MINOTAURE && team.getGod() != null) return true;
    }
    return false;
  }

  private int getTotalPlayer() {
    int count = 0;

    for (Faction faction :
        Daedalus.getInstance().getGameManager().getFactionManager().getFactionList())
      if (faction.getType() != FactionsEnum.SPECTATOR) count += faction.getPlayerAmount();

    return count;
  }

  private int getAlivePlayer() {
    int count = 0;
    for (Faction faction :
        Daedalus.getInstance().getGameManager().getFactionManager().getFactionList())
      if (faction.getType() != FactionsEnum.SPECTATOR) count += faction.getAliveCount();

    return count;
  }
}
