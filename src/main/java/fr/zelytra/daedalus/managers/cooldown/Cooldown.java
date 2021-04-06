package fr.zelytra.daedalus.managers.cooldown;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Cooldown {
    public static HashMap<Cooldown, Player> cooldownsList = new HashMap<>();

    private final Player player;
    private long checkTime;

    private String tag;

    public Cooldown(Player p, long timeSeconds, String tag) {
        this.player = p;
        this.checkTime = System.currentTimeMillis() + timeSeconds * 1000;
        this.tag = tag;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getTag() {
        return tag;
    }

    public long getRemainingTimeSeconds() {
        return (this.checkTime - System.currentTimeMillis()) / 1000;
    }

    public void addTimeToWait(long timeSeconds) {
        this.checkTime += timeSeconds * 1000;
    }

    public boolean isFinished() {
        return this.checkTime - System.currentTimeMillis() <= 0;
    }

    public String toString() {
        //Milliseconds display
        if (this.checkTime - System.currentTimeMillis() <= 1000) {
            return "§e" + (this.checkTime - System.currentTimeMillis()) + "§6ms";
        }
        //Seconds display
        else if (this.checkTime - System.currentTimeMillis() <= 60000) {
            int timeInSec = (int) ((this.checkTime - System.currentTimeMillis()) / 1000) % 60;
            return "§e" + timeInSec + "§6s";
        }
        //Minutes display
        else if (this.checkTime - System.currentTimeMillis() <= 3600000) {
            int remainingSec = (int) ((this.checkTime - System.currentTimeMillis()) / 1000);
            int timeInSec = (remainingSec % 60);
            int timeInMin = (remainingSec % 3600) / 60;
            return "§e" + timeInMin + "§6m" + "§e" + timeInSec + "§6s";
        }
        //Hours display
        else if (this.checkTime - System.currentTimeMillis() <= 86400000) {
            int remainingSec = (int) ((this.checkTime - System.currentTimeMillis()) / 1000);
            int timeInSec = (remainingSec % 60);
            int timeInMin = (remainingSec % 3600) / 60;
            int TimeInHour = (remainingSec % 86400) / 3600;
            return "§e" + TimeInHour + "§6h" + "§e" + timeInMin + "§6m" + "§e" + timeInSec + "§6s";
        } else {
            return "§cNA";
        }

    }

}
