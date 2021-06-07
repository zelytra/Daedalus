package fr.zelytra.daedalus.managers.game.time;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.structure.Structure;
import fr.zelytra.daedalus.managers.structure.StructureEnum;
import fr.zelytra.daedalus.managers.structure.doors.Doors;
import fr.zelytra.daedalus.managers.structure.doors.DoorsDirection;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.text.SimpleDateFormat;
import java.util.Map;

public class TimeManager {
    private int time = 0;
    private String timer = "00:00";
    private int episode = 1;
    private int runnable;
    private boolean pause = false;

    public TimeManager() {
    }

    public void start() {

        runnable = Bukkit.getScheduler().scheduleSyncRepeatingTask(Daedalus.getInstance(), () -> {

            if (time == GameSettings.TIME_PER_EPISODE)
                next();

            if (!isPause())
                time++;
            updateTimer();

            for (Faction faction : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
                faction.getFactionScoreBoard().update(this);
            }

        }, 0L, 20L);

    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(runnable);
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isPause() {
        return pause;
    }

    private void next() {
        time = 0;
        episode++;
        episodeChangeEvent();
    }

    private void episodeChangeEvent() {
        switch (episode) {

            case 2: {

                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(Message.getPlayerPrefixe() + "§8Minotaure §6has been released in the Maze... May Divinities be with you !");
                Bukkit.broadcastMessage("");

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(player.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1, 0.5F);
                }

                for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {
                    if (entry.getValue().getName() == StructureEnum.MINOTAURE.getName()) {
                        Doors doors = new Doors(entry.getKey());
                        doors.open(DoorsDirection.ALL);
                    }
                }

                break;
            }
            case 3: {

                System.out.println("Malédictions");


                break;
            }

            case 6: {

                Daedalus.getInstance().getStructureManager().getShrinkManager().startShrinking();


                break;
            }
            default: {

                break;
            }

        }
    }

    public int getEpisode() {
        return episode;
    }

    private void updateTimer() {
        timer = (new SimpleDateFormat("mm:ss")).format(time * 1000);
    }

    public String getTimer() {
        return timer;
    }
}
 