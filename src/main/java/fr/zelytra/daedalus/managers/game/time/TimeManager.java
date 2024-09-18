package fr.zelytra.daedalus.managers.game.time;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.game.time.event.EpisodeChangeEvent;
import fr.zelytra.daedalus.managers.structure.Structure;
import fr.zelytra.daedalus.managers.structure.StructureEnum;
import fr.zelytra.daedalus.managers.structure.doors.Doors;
import fr.zelytra.daedalus.managers.structure.doors.DoorsDirection;
import fr.zelytra.daedalus.utils.Message;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;

import java.text.SimpleDateFormat;
import java.util.Map;

public class TimeManager {
    private int time = 0;
    @Getter
    private String timer = "00:00";
    public static int episode = 1;
    private int runnable;
    @Getter
    @Setter
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

    private void next() {
        time = 0;
        episode++;
        episodeChangeEvent();
    }

    private void episodeChangeEvent() {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("event.episode") + episode);

        EpisodeChangeEvent event = new EpisodeChangeEvent(episode);
        Bukkit.getPluginManager().callEvent(event);

        switch (episode) {

            case 2:


                Bukkit.broadcastMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("event.minoRelease"));

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

            case 4:
                Bukkit.broadcastMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("event.lifeSharing"));
                break;

            case 6:
                Bukkit.broadcastMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("event.mazeFalling"));
                Daedalus.getInstance().getStructureManager().getShrinkManager().startShrinking();


                break;

            case 8:
                Bukkit.broadcastMessage(GameSettings.LANG.textOf("event.playerShine"));
                for (Faction faction : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
                    for (Player player : faction.getPlayerList())
                        if (faction.isAlive(player))
                            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 9999999, 0, true, false, true));

                }
                break;

            default:

                break;


        }
        Bukkit.broadcastMessage("");
    }

    public int getEpisode() {
        return episode;
    }

    private void updateTimer() {
        timer = (new SimpleDateFormat("mm:ss")).format(time * 1000);
    }
}
 