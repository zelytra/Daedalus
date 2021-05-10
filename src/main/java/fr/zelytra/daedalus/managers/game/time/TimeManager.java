package fr.zelytra.daedalus.managers.game.time;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;

public class TimeManager {
    private int time = GameSettings.TIME_PER_EPISODE;
    private String timer = "20:00";
    private int episode = 1;
    private int runnable;
    private boolean pause = false;

    public TimeManager(){
    }

    public void start(){

        runnable = Bukkit.getScheduler().scheduleSyncRepeatingTask(Daedalus.getInstance(), ()-> {

            if(time == 0)
                next();

            switch (episode){


                case 2:{

                    //System.out.println("Minotaure");

                    break;
                }
                case 3:{

                    //System.out.println("Malédictions");

                    break;
                }

                case 6:{

                    //System.out.println("Go middle");

                    break;
                }
                default:{

                    break;
                }

            }
            if(!isPause())
                time--;
            updateTimer();
            Daedalus.getInstance().getGameManager().getScoreBoardManager().update();
        }, 0L, 20L);

    }

    public void stop(){
        Bukkit.getScheduler().cancelTask(runnable);
    }

    public void setPause(boolean pause){
        this.pause = pause;
    }

    public boolean isPause() {
        return pause;
    }

    private void next(){
        time = GameSettings.TIME_PER_EPISODE;
        episode++;
    }

    public int getEpisode() {
        return episode;
    }

    private void updateTimer(){
        timer = (new SimpleDateFormat("mm:ss")).format(time * 1000);
    }

    public String getTimer(){
        return  timer;
    }
}
 