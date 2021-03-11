package fr.zelytra.daedalus.managers.team;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

public enum TeamsEnum {

    RED("Rouge", DyeColor.RED, ChatColor.RED),
    BLUE("Bleue", DyeColor.BLUE, ChatColor.BLUE),
    YELLOW("Jaune", DyeColor.YELLOW, ChatColor.YELLOW),
    GREEN("Verte", DyeColor.GREEN, ChatColor.GREEN),
    MINOS("Minautore", DyeColor.GRAY, ChatColor.GRAY);

    private final String name;
    private final DyeColor teamColor;
    private final ChatColor chatColor;

    TeamsEnum(String name, DyeColor teamColor, ChatColor chatColor){
        this.name = name;
        this.teamColor = teamColor;
        this.chatColor = chatColor;
    }

    public String getName() {
        return name;
    }

    public DyeColor getTeamColor() {
        return teamColor;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }
}
