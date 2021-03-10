package fr.zelytra.daedalus.utils;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Bukkit;

public class Message {
    // Color console
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static String getPlayerPrefixe(){
        return new String("§2[§aDaedalus§2]§r");
    }

    public static String getConsolePrefixe(){
        return new String(ANSI_GREEN+"[Daedalus]");
    }

    public static void startup() {
        System.out.println("");
        System.out.println(ANSI_CYAN + "               Daedalus   V0.1" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "          by Zelytra & JohnPoliakov" + ANSI_RESET);
        System.out.println("");

    }
}
