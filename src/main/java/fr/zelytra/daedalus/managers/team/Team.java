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
    private HashMap<Player, PlayerStatus> playerStatusList;

    public Team(TeamsEnum team, Scoreboard scoreboard) {

        this.playerList = new ArrayList<>();
        this.team = team;
        this.scoreboard = scoreboard;
        this.playerStatusList = new HashMap<>();
        build();
    }

    private void build() {

        getTeamEnum().setTeam(scoreboard.registerNewTeam(getTeamEnum().getName()));
        getTeamEnum().getTeam().setDisplayName("");
        getTeamEnum().getTeam().setAllowFriendlyFire(GameSettings.FRIENDLY_FIRE);
        getTeamEnum().getTeam().setColor(getChatColor());
        getTeamEnum().getTeam().setPrefix(getPrefix());
        getTeamEnum().getTeam().setSuffix(getSuffix());
        getTeamEnum().getTeam().setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
        getTeamEnum().getTeam().setOption(org.bukkit.scoreboard.Team.Option.DEATH_MESSAGE_VISIBILITY, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
        getTeamEnum().getTeam().setOption(org.bukkit.scoreboard.Team.Option.NAME_TAG_VISIBILITY, org.bukkit.scoreboard.Team.OptionStatus.ALWAYS);

    }

    public ItemStack getBanner() {

        ItemStack banner;
        ItemMeta meta;
        ArrayList<String> lore = new ArrayList<>();

        switch (getTeamColor()) {

            case RED:
                banner = new ItemStack(Material.RED_BANNER);
                meta = banner.getItemMeta();
                assert meta != null;
                meta.setDisplayName("§cEquipe rouge");
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
                meta.setDisplayName("§9Equipe bleue");
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
                meta.setDisplayName("§aEquipe verte");
                if (!getPlayerList().isEmpty()) {
                    lore.add("");
                    for (UUID uuid : getPlayerList()) {

                        lore.add("§a - " + Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName());

                    }
                }

                meta.setLore(lore);
                banner.setItemMeta(meta);
                break;

            case GRAY:
                banner = new ItemStack(Material.GRAY_BANNER);
                meta = banner.getItemMeta();
                assert meta != null;
                meta.setDisplayName("§7Equipe minotaure");
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
                meta.setDisplayName("§eEquipe jaune");
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
            getTeamEnum().getTeam().addEntry(uuid.toString());
            p.setDisplayName(getPrefix() + p.getName() + getSuffix());
            p.setPlayerListName(getPrefix() + p.getName() + getSuffix());
            playerStatusList.put(p, PlayerStatus.ALIVE);
        }
    }

    public void removePlayer(UUID uuid) {

        Player p = Bukkit.getPlayer(uuid);
        if (p != null && hasPlayer(uuid)) {
            getPlayerList().remove(uuid);
            getTeamEnum().getTeam().removeEntry(uuid.toString());
            p.setDisplayName("§r" + p.getName() + "§r");
            p.setPlayerListName("§r" + p.getName() + "§r");
            playerStatusList.remove(p);
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

    public boolean isAlive(Player p){
        if(playerStatusList.containsKey(p)){
            if(playerStatusList.get(p)==PlayerStatus.ALIVE){
                return true;
            }
        }
        return false;
    }
    public boolean isDead(Player p){
        if(playerStatusList.containsKey(p)){
            if(playerStatusList.get(p)==PlayerStatus.DEAD){
                return true;
            }
        }
        return false;
    }

    public void setPlayerStatus(Player p,PlayerStatus status){
        if(playerStatusList.containsKey(p)){
            playerStatusList.put(p,status);
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
}
