package fr.zelytra.daedalus.managers.team;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.scoreboard.Team;

public enum TeamsEnum {

    RED("Red", DyeColor.RED, ChatColor.RED, "§c", "§r"),
    BLUE("Blue", DyeColor.BLUE, ChatColor.BLUE, "§9", "§r"),
    YELLOW("Yellow", DyeColor.YELLOW, ChatColor.YELLOW, "§e", "§r"),
    GREEN("Green", DyeColor.GREEN, ChatColor.GREEN, "§a", "§r"),
    MINOS("Minos", DyeColor.GRAY, ChatColor.GRAY, "§7", "§r");

    private final String name;
    private final DyeColor teamColor;
    private final ChatColor chatColor;
    private final String prefix;
    private final String suffix;
    private Team team;

    TeamsEnum(String name, DyeColor teamColor, ChatColor chatColor, String prefix, String suffix) {
        this.name = name;
        this.teamColor = teamColor;
        this.chatColor = chatColor;
        this.prefix = prefix;
        this.suffix = suffix;
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

    public Team getTeam() {
        return team;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
