package fr.zelytra.daedalus.utils;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Bukkit;

public class Message {
    // Color console
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";


    public static String getPlayerPrefixe(){
        return new String("§2[§aDaedalus§2]§r ");
    }

    public static String getConsolePrefixe(){
        return new String(ANSI_GREEN+"[Daedalus] ");
    }

}
