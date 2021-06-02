package fr.zelytra.daedalus.managers.faction;

import fr.zelytra.daedalus.Daedalus;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

@AllArgsConstructor
public enum FactionsEnum {
    RED("Red", "Ared", ChatColor.RED, "§c", "§r", new Location(Bukkit.getWorld(Daedalus.WORLD_NAME), -485, 89, -477), Material.RED_BANNER),
    GREEN("Green", "Dgreen", ChatColor.GREEN, "§a", "§r", new Location(Bukkit.getWorld(Daedalus.WORLD_NAME), 476, 89, -477),Material.GREEN_BANNER),
    MINOTAUR("Minotaur", "Eminotaur", ChatColor.GOLD, "§6", "§r", new Location(Bukkit.getWorld(Daedalus.WORLD_NAME), -3, 90, 0),Material.BROWN_BANNER),
    BLUE("Blue", "Cblue", ChatColor.BLUE, "§9", "§r", new Location(Bukkit.getWorld(Daedalus.WORLD_NAME), -485, 89, 483),Material.BLUE_BANNER),
    YELLOW("Yellow", "Byellow", ChatColor.YELLOW, "§e", "§r", new Location(Bukkit.getWorld(Daedalus.WORLD_NAME), 475, 89, 483),Material.YELLOW_BANNER),

    SPECTATOR("Spectator", "Fspectator", ChatColor.WHITE, "§f", "§r", new Location(Bukkit.getWorld(Daedalus.WORLD_NAME), 0, 81, 0),Material.WHITE_BANNER);

    private String name;
    private String tag;
    private ChatColor chatColor;

    private String prefix;
    private String suffix;

    private Location spawn;
    private Material banner;

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public Location getSpawn() {
        return spawn;
    }

    public Material getBanner() {
        return banner;
    }
}
