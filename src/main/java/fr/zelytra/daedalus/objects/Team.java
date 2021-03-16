package fr.zelytra.daedalus.objects;

import fr.zelytra.daedalus.managers.team.TeamsEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Team {

    private ArrayList<UUID> playerList;
    private final TeamsEnum team;
    private Scoreboard scoreboard;

    public Team(TeamsEnum team, Scoreboard scoreboard){

        this.playerList = new ArrayList<>();
        this.team = team;
        this.scoreboard = scoreboard;
        build();
    }

    private void build(){

        getTeamEnum().setTeam(scoreboard.registerNewTeam(getTeamEnum().getName()));
        getTeamEnum().getTeam().setDisplayName("");
        getTeamEnum().getTeam().setAllowFriendlyFire(false);
        getTeamEnum().getTeam().setColor(getChatColor());
        getTeamEnum().getTeam().setPrefix(getPrefix());
        getTeamEnum().getTeam().setSuffix(getSuffix());
        getTeamEnum().getTeam().setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
        getTeamEnum().getTeam().setOption(org.bukkit.scoreboard.Team.Option.DEATH_MESSAGE_VISIBILITY, org.bukkit.scoreboard.Team.OptionStatus.NEVER);
        getTeamEnum().getTeam().setOption(org.bukkit.scoreboard.Team.Option.NAME_TAG_VISIBILITY, org.bukkit.scoreboard.Team.OptionStatus.ALWAYS);

    }

    public ItemStack getBanner(){

        ItemStack banner;
        ItemMeta meta;
        ArrayList<String> lore = new ArrayList<>();

        switch (getTeamColor()){

            case RED:
                banner = new ItemStack(Material.RED_BANNER);
                meta = banner.getItemMeta();
                assert meta != null;
                meta.setDisplayName("§cEquipe rouge");
                if(!getPlayerList().isEmpty()){
                    lore.add("");
                    for(UUID uuid : getPlayerList()){

                        lore.add("§c - "+ Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName());

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
                if(!getPlayerList().isEmpty()){
                    lore.add("");
                    for(UUID uuid : getPlayerList()){

                        lore.add("§9 - "+ Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName());

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
                if(!getPlayerList().isEmpty()){
                    lore.add("");
                    for(UUID uuid : getPlayerList()){

                        lore.add("§a - "+ Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName());

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
                if(!getPlayerList().isEmpty()){
                    lore.add("");
                    for(UUID uuid : getPlayerList()){

                        lore.add("§7 - "+ Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName());

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
                if(!getPlayerList().isEmpty()){
                    lore.add("");
                    for(UUID uuid : getPlayerList()){

                        lore.add("§e - "+ Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName());

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

    public void addPlayer(UUID uuid){

        Player p = Bukkit.getPlayer(uuid);
        if(p != null && !hasPlayer(uuid)) {
            getPlayerList().add(uuid);
            getTeamEnum().getTeam().addEntry(uuid.toString());
            p.setDisplayName(getPrefix()+p.getName()+getSuffix());
            p.setPlayerListName(getPrefix()+p.getName()+getSuffix());
        }
    }

    public void removePlayer(UUID uuid){

        Player p = Bukkit.getPlayer(uuid);
        if(p != null && hasPlayer(uuid)) {
            getPlayerList().remove(uuid);
            getTeamEnum().getTeam().removeEntry(uuid.toString());
            p.setDisplayName("§r"+p.getName()+"§r");
            p.setPlayerListName("§r"+p.getName()+"§r");
        }
    }

    private boolean hasPlayer(UUID uuid){
        return getPlayerList().contains(uuid);
    }

    public ArrayList<UUID> getPlayerList() {
        return playerList;
    }

    public TeamsEnum getTeamEnum() {
        return team;
    }

    public DyeColor getTeamColor(){
        return getTeamEnum().getTeamColor();
    }

    public ChatColor getChatColor(){
        return getTeamEnum().getChatColor();
    }

    public String getPrefix(){
        return getTeamEnum().getPrefix();
    }

    public String getSuffix(){
        return getTeamEnum().getSuffix();
    }

    public void destroy(){
        for(String uuid : getTeamEnum().getTeam().getEntries()){
            removePlayer(UUID.fromString(uuid));
        }
        getTeamEnum().getTeam().unregister();
    }
}
