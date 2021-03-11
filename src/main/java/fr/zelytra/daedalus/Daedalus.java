package fr.zelytra.daedalus;

import org.bukkit.plugin.java.JavaPlugin;

public final class Daedalus extends JavaPlugin {

    public static Daedalus instance;

    public static Daedalus getInstance(){
        return instance;
    }

    @Override
    public void onLoad(){
        instance = this;
    }

    @Override
    public void onEnable() {
        System.out.println("Start");
    }

    @Override
    public void onDisable() {

    }
}
