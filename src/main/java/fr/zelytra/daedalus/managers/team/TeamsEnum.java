package fr.zelytra.daedalus.managers.team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.scoreboard.Team;

public enum TeamsEnum {

    RED("aRed", DyeColor.RED, ChatColor.RED, "§c", "§r",new Location(Bukkit.getWorld("world"),-485,89,-477)),
    BLUE("bBlue", DyeColor.BLUE, ChatColor.BLUE, "§9", "§r",new Location(Bukkit.getWorld("world"),-485,89,483)),
    YELLOW("cYellow", DyeColor.YELLOW, ChatColor.YELLOW, "§e", "§r",new Location(Bukkit.getWorld("world"),475,89,483)),
    GREEN("dGreen", DyeColor.GREEN, ChatColor.GREEN, "§a", "§r",new Location(Bukkit.getWorld("world"),476,89,-477)),
    SPECTATOR("eSpectator", DyeColor.WHITE, ChatColor.WHITE, "§f", "§r",new Location(Bukkit.getWorld("world"),0,81,0)),
    MINOS("fMinos", DyeColor.ORANGE, ChatColor.GOLD, "§6", "§r",new Location(Bukkit.getWorld("world"),-3,90,0));

    private final String name;
    private final DyeColor teamColor;
    private final ChatColor chatColor;
    private final String prefix;
    private final String suffix;
    private Team team;
    private final Location spawn;

    TeamsEnum(String name, DyeColor teamColor, ChatColor chatColor, String prefix, String suffix, Location spawn) {
        this.name = name;
        this.teamColor = teamColor;
        this.chatColor = chatColor;
        this.prefix = prefix;
        this.suffix = suffix;
        this.spawn = spawn;
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

    public Location getSpawn() {
        return spawn;
    }
}
