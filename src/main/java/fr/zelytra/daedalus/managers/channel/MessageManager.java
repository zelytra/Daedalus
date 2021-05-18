package fr.zelytra.daedalus.managers.channel;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.team.Team;
import fr.zelytra.daedalus.managers.team.TeamsEnum;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class MessageManager {

    private final String message;
    private final ChannelEnum channel;
    private Player sender;
    private Team senderTeam;
    private TeamsEnum teamsEnum;

    public MessageManager(String message, ChannelEnum channel) {

        this.channel = channel;
        this.message = message;

    }

    public MessageManager(String message, ChannelEnum channel, TeamsEnum teamsEnum) {

        this.channel = channel;
        this.message = message;
        this.teamsEnum = teamsEnum;

    }

    public MessageManager(Player player, String message, ChannelEnum channel, Team team) {

        this.sender = player;
        this.channel = channel;
        this.message = message;
        this.senderTeam = team;

    }


    public void sendMessage() {
        switch (channel) {
            case GLOBAL:
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.sendMessage(message);
                }
                break;
            case TEAM:
                for (UUID uuid : senderTeam.getPlayerList()) {
                    Bukkit.getPlayer(uuid).sendMessage(message);
                }
                break;

            case SPECTATOR:
                for (UUID uuid : Daedalus.getInstance().getGameManager().getTeamManager().getSpectatorTeam().getPlayerList()) {
                    Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage(message);
                }
                break;
        }
    }

    public void playerSendMessage() {
        if (sender == null) {
            return;
        }
        switch (channel) {
            case GLOBAL:
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.sendMessage(getFormattedMessage());
                }
                break;
            case TEAM:
                for (UUID id : senderTeam.getPlayerList()) {
                    Objects.requireNonNull(Bukkit.getPlayer(id)).sendMessage(getFormattedMessage());
                }
                break;
            case SPECTATOR:
                for (UUID uuid : Daedalus.getInstance().getGameManager().getTeamManager().getSpectatorTeam().getPlayerList()) {
                    Objects.requireNonNull(Bukkit.getPlayer(uuid)).sendMessage(getFormattedMessage());
                }
                break;
        }
    }

    public String getFormattedMessage() {
        switch (channel) {

            case GLOBAL: {

                return ChatColor.of("#808080") + "[Global] " + senderTeam.getPrefix() + sender.getName() + "§7 > §f" + message.substring(1);
            }
            case TEAM: {

                return ChatColor.of("#808080") + "[Team] " + senderTeam.getPrefix() + sender.getName() + "§7 > §f" + message;
            }
            case SPECTATOR: {

                return ChatColor.of("#808080") + "[Spec] " + senderTeam.getPrefix() + "§7" + sender.getName() + "§7 > §f" + message;
            }
        }
        return "";
    }

}
