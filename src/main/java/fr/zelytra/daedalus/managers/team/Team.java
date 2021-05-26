package fr.zelytra.daedalus.managers.team;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Team {

    private final ArrayList<UUID> playerList;
    private final TeamsEnum team;
    private final Scoreboard scoreboard;
    private Player god = null;
    private GodsEnum godsEnum = null;
    private HashMap<UUID, PlayerStatus> playerStatusList;

    public Team(TeamsEnum team, Scoreboard scoreboard) {

        this.playerList = new ArrayList<>();
        this.team = team;
        this.scoreboard = scoreboard;
        this.playerStatusList = new HashMap<>();
        build();
    }

    private void build() {

        this.team.setTeam(scoreboard.registerNewTeam(getTeamEnum().getName()));
        this.team.getTeam().setAllowFriendlyFire(GameSettings.FRIENDLY_FIRE);
        this.team.getTeam().setColor(this.getChatColor());
        this.team.getTeam().setPrefix(this.getPrefix());
        this.team.getTeam().setSuffix(this.getSuffix());
        this.team.getTeam().setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
        this.team.getTeam().setOption(org.bukkit.scoreboard.Team.Option.DEATH_MESSAGE_VISIBILITY, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
        this.team.getTeam().setOption(org.bukkit.scoreboard.Team.Option.NAME_TAG_VISIBILITY, org.bukkit.scoreboard.Team.OptionStatus.ALWAYS);

    }

    public ItemStack getBanner() {

        ItemStack banner;
        ItemMeta meta;
        ArrayList<String> lore = new ArrayList<>();

        switch (getTeamEnum()) {

            case RED:
                banner = new ItemStack(Material.RED_BANNER);
                meta = banner.getItemMeta();
                assert meta != null;
                meta.setDisplayName("§cRed team");
                if (!getPlayerList().isEmpty()) {
                    lore.add("");
                    for (UUID uuid : getPlayerList()) {

                        lore.add("§c - " + Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName());

                    }
                }

                meta.setLore(lore);
                banner.setItemMeta(meta);
                break;

            case BLUE:

                banner = new ItemStack(Material.BLUE_BANNER);
                meta = banner.getItemMeta();
                assert meta != null;
                meta.setDisplayName("§9Blue team");
                if (!getPlayerList().isEmpty()) {
                    lore.add("");
                    for (UUID uuid : getPlayerList()) {

                        lore.add("§9 - " + Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName());

                    }
                }

                meta.setLore(lore);
                banner.setItemMeta(meta);
                break;

            case GREEN:
                banner = new ItemStack(Material.GREEN_BANNER);
                meta = banner.getItemMeta();
                assert meta != null;
                meta.setDisplayName("§aGreen team");
                if (!getPlayerList().isEmpty()) {
                    lore.add("");
                    for (UUID uuid : getPlayerList()) {

                        lore.add("§a - " + Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName());

                    }
                }

                meta.setLore(lore);
                banner.setItemMeta(meta);
                break;

            case MINOS:
                banner = new ItemStack(Material.GRAY_BANNER);
                meta = banner.getItemMeta();
                assert meta != null;
                meta.setDisplayName("§7Minotaure team");
                if (!getPlayerList().isEmpty()) {
                    lore.add("");
                    for (UUID uuid : getPlayerList()) {

                        lore.add("§7 - " + Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName());

                    }
                }

                meta.setLore(lore);
                banner.setItemMeta(meta);
                break;

            case YELLOW:
                banner = new ItemStack(Material.YELLOW_BANNER);
                meta = banner.getItemMeta();
                assert meta != null;
                meta.setDisplayName("§eYellow team");
                if (!getPlayerList().isEmpty()) {
                    lore.add("");
                    for (UUID uuid : getPlayerList()) {

                        lore.add("§e - " + Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName());

                    }
                }

                meta.setLore(lore);
                banner.setItemMeta(meta);
                break;
            default:
                return null;
        }


        return banner;
    }

    public void addPlayer(UUID uuid) {

        Player p = Bukkit.getPlayer(uuid);
        if (p != null && !hasPlayer(uuid)) {
            getPlayerList().add(uuid);
            team.getTeam().addEntry(p.getName());
            p.setPlayerListName(getPrefix() + p.getName() + getSuffix());
            setScoreboardForPlayers();
            playerStatusList.put(p.getUniqueId(), PlayerStatus.ALIVE);
        }
    }

    public void removePlayer(UUID uuid) {

        Player p = Bukkit.getPlayer(uuid);
        if (p != null && hasPlayer(uuid)) {
            getPlayerList().remove(uuid);
            team.getTeam().removeEntry(p.getName());
            p.setPlayerListName("§r" + p.getName() + "§r");
            playerStatusList.remove(p.getUniqueId());
        }
    }

    private boolean hasPlayer(UUID uuid) {
        return getPlayerList().contains(uuid);
    }

    public ArrayList<UUID> getPlayerList() {
        return playerList;
    }

    public TeamsEnum getTeamEnum() {
        return team;
    }

    public DyeColor getTeamColor() {
        return getTeamEnum().getTeamColor();
    }

    public ChatColor getChatColor() {
        return getTeamEnum().getChatColor();
    }

    public String getPrefix() {
        return getTeamEnum().getPrefix();
    }

    public String getSuffix() {
        return getTeamEnum().getSuffix();
    }

    public boolean isAlive(Player p) {
        if (playerStatusList.containsKey(p.getUniqueId())) {
            return playerStatusList.get(p.getUniqueId()) == PlayerStatus.ALIVE;
        }
        return false;
    }

    public boolean isDead(Player p) {
        if (playerStatusList.containsKey(p.getUniqueId())) {
            return playerStatusList.get(p.getUniqueId()) == PlayerStatus.DEAD;
        }
        return false;
    }

    public void setPlayerStatus(Player p, PlayerStatus status) {
        if (playerStatusList.containsKey(p.getUniqueId())) {
            playerStatusList.put(p.getUniqueId(), status);
        }
    }

    public void destroy() {
        for (String uuid : getTeamEnum().getTeam().getEntries()) {
            removePlayer(UUID.fromString(uuid));
        }
        getTeamEnum().getTeam().unregister();
    }

    public void setGod(Player player, GodsEnum godsEnum) {
        this.god = player;
        this.godsEnum = godsEnum;
    }

    public DyeColor getColor() {
        return this.team.getTeamColor();
    }

    public Player getGod() {
        return god;
    }

    public void removeGod() {
        if (this.god != null) {
            this.god = null;
        }
    }

    public GodsEnum getGodEnum() {
        return this.godsEnum;
    }

    private void setScoreboardForPlayers() {

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(scoreboard);
        }
    }
}


