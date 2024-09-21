package fr.zelytra.daedalus.managers.game;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.faction.FactionManager;
import fr.zelytra.daedalus.managers.faction.FactionsEnum;
import fr.zelytra.daedalus.managers.game.settings.DayCycleEnum;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.game.time.TimeManager;
import fr.zelytra.daedalus.managers.maze.MazeHandler;
import fr.zelytra.daedalus.utils.Message;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GameManager {

  @Getter private final FactionManager factionManager;
  @Getter private final TimeManager timeManager;
  @Setter @Getter private GameStatesEnum state;
  @Setter @Getter private boolean started = false;
  private int preStartRunnable;

  public GameManager() {

    this.factionManager = new FactionManager();
    this.timeManager = new TimeManager();
    this.state = GameStatesEnum.WAIT;
  }

  public boolean isWaiting() {
    return state == GameStatesEnum.WAIT;
  }

  public boolean isRunning() {
    return state == GameStatesEnum.RUNNING;
  }

  public boolean isFinished() {
    return state == GameStatesEnum.FINISHED;
  }

  public void preStart() {

    if (isStarted()) {
      for (Player player : Bukkit.getOnlinePlayers())
        player.sendMessage(
            Message.getPlayerPrefixe() + GameSettings.LANG.textOf("maze.cancelGeneration"));
      return;
    }

    Bukkit.broadcastMessage("");
    Bukkit.broadcastMessage(
        Message.getPlayerPrefixe() + GameSettings.LANG.textOf("maze.gameAboutToStart"));
    Bukkit.broadcastMessage("");
    for (Player player : Bukkit.getOnlinePlayers())
      if (player.isOp())
        player.sendMessage(
            Message.getPlayerPrefixe() + GameSettings.LANG.textOf("maze.cancelGeneration"));

    AtomicInteger countdown = new AtomicInteger(10);
    started = true;
    preStartRunnable =
        Bukkit.getScheduler()
            .scheduleSyncRepeatingTask(
                Daedalus.getInstance(),
                () -> {
                  if (!started) {
                    logPlayers(GameSettings.LANG.textOf("maze.startCancel"));
                    Bukkit.getScheduler().cancelTask(preStartRunnable);
                    return;
                  }
                  Daedalus.getInstance().getGameManager().setState(GameStatesEnum.WAIT);

                  if (countdown.get() == 10) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                      p.sendTitle("§c10", "", 10, 20, 10);
                      p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 0.5f, 1f);
                    }
                  }

                  if (countdown.get() <= 5 && countdown.get() > 0) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                      p.sendTitle("§4" + countdown.get(), "", 10, 20, 10);
                      p.playSound(p.getLocation(), Sound.BLOCK_LEVER_CLICK, 0.5f, 1f);
                    }
                  }

                  if (countdown.get() == 0) {
                    Bukkit.getScheduler().cancelTask(preStartRunnable);
                    start();
                  }
                  logPlayers(
                      GameSettings.LANG.textOf("command.gameStartingIn")
                          + countdown.get()
                          + "s§a]");
                  countdown.getAndDecrement();
                },
                0L,
                20L);
  }

  // FONCTION DE DEBUT DE PARTIE
  public void start() {

    for (Player player : Bukkit.getOnlinePlayers()) {
      player.getInventory().clear();
      player.getActivePotionEffects().clear();
      player.setMaxHealth(20.0);
    }
    Bukkit.getScheduler()
        .runTaskAsynchronously(
            Daedalus.getInstance(),
            () -> {
              // Maze generation
              Bukkit.broadcastMessage(
                  Message.getPlayerPrefixe() + GameSettings.LANG.textOf("maze.startGeneration"));
              Location origin = new Location(Bukkit.getWorld(Daedalus.WORLD_NAME), 0, 0, 0);
              origin.setY(
                  Bukkit.getWorld(Daedalus.WORLD_NAME)
                          .getHighestBlockYAt((int) origin.getX(), (int) origin.getZ())
                      + 1);
              MazeHandler maze =
                  new MazeHandler(
                      origin,
                      300,
                      true,
                      Daedalus.getInstance().getStructureManager().getGeneratedList());
              maze.generateScaleMaze();

              Bukkit.getScheduler()
                  .runTask(
                      Daedalus.getInstance(),
                      () -> {
                        if (GameSettings.DAY_CYCLE == DayCycleEnum.DEFAULT)
                          Bukkit.getWorld(Daedalus.WORLD_NAME)
                              .setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);

                        // Player Manager
                        for (Player p : Bukkit.getOnlinePlayers()) {
                          Faction playerFaction = factionManager.getFactionOf(p);
                          if (playerFaction.getType() == FactionsEnum.SPECTATOR) {
                            p.setGameMode(GameMode.SPECTATOR);
                            p.teleport(FactionsEnum.SPECTATOR.getSpawn());
                          } else {
                            p.setGameMode(GameMode.SURVIVAL);
                            p.teleport(playerFaction.getType().getSpawn());
                          }
                        }

                        // GameManager start
                        getTimeManager().start();
                        started = false;
                        Bukkit.broadcastMessage(GameSettings.LANG.textOf("event.gameStart"));
                        setState(GameStatesEnum.RUNNING);
                      });
            });
  }

  public TimeManager getTimer() {
    return this.timeManager;
  }

  // FONCTION DE FIN DE PARTIE
  public void stop() {
    getTimeManager().stop();
    this.state = GameStatesEnum.FINISHED;
    for (Player player : Bukkit.getOnlinePlayers()) {
      player.getInventory().clear();
      player.getInventory().addItem(new ItemStack(Material.TNT));
      player.getInventory().addItem(new ItemStack(Material.FLINT_AND_STEEL));
      player.setGameMode(GameMode.CREATIVE);
      player.teleport(FactionsEnum.SPECTATOR.getSpawn());
    }
  }

  private void logPlayers(String msg) {
    for (Player player : Bukkit.getOnlinePlayers()) {
      BaseComponent txt = new TextComponent(msg);
      player.spigot().sendMessage(ChatMessageType.ACTION_BAR, txt);
    }
  }
}
