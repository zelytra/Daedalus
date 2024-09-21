package fr.zelytra.daedalus.managers.channel;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.faction.FactionsEnum;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import java.util.Objects;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageManager {

	private final String message;
	private final ChannelEnum channel;
	private Player sender;
	private Faction senderTeam;

	public MessageManager(String message, ChannelEnum channel) {

		this.channel = channel;
		this.message = message;
	}

	public MessageManager(Player player, String message, ChannelEnum channel, Faction team) {

		this.sender = player;
		this.channel = channel;
		this.message = message;
		this.senderTeam = team;
	}

	public void sendMessage() {
		switch (channel) {
			case GLOBAL :
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.sendMessage(message);
				}
				Bukkit.getConsoleSender().sendMessage(message);
				break;
			case TEAM :
				for (Player player : senderTeam.getPlayerList()) {
					player.sendMessage(message);
				}
				Bukkit.getConsoleSender().sendMessage(message);
				break;

			case SPECTATOR :
				for (Player player : Daedalus.getInstance().getGameManager().getFactionManager()
						.getFactionOf(FactionsEnum.SPECTATOR).getPlayerList()) {
					Objects.requireNonNull(player).sendMessage(message);
				}
				Bukkit.getConsoleSender().sendMessage(message);
				break;
		}
	}

	public void playerSendMessage() {
		if (sender == null) {
			return;
		}
		switch (channel) {
			case GLOBAL :
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.sendMessage(getFormattedMessage());
				}
				Bukkit.getConsoleSender().sendMessage(getFormattedMessage());
				break;
			case TEAM :
				for (Player player : senderTeam.getPlayerList())
					Objects.requireNonNull(player).sendMessage(getFormattedMessage());

				for (Player player : Daedalus.getInstance().getGameManager().getFactionManager()
						.getFactionOf(FactionsEnum.SPECTATOR).getPlayerList())
					if (player.isOp())
						Objects.requireNonNull(player).sendMessage(getFormattedMessage());

				Bukkit.getConsoleSender().sendMessage(getFormattedMessage());
				break;

			case SPECTATOR :
				for (Faction team : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
					for (Player player : team.getPlayerList()) {
						if (team.getType() == FactionsEnum.SPECTATOR) {
							Objects.requireNonNull(player).sendMessage(getFormattedMessage());
						} else if (!team.isAlive(player)) {
							Objects.requireNonNull(player).sendMessage(getFormattedMessage());
						} else
							continue;
					}
				}
				Bukkit.getConsoleSender().sendMessage(getFormattedMessage());
				break;
		}
	}

	public String getFormattedMessage() {
		switch (channel) {
			case GLOBAL : {
				return ChatColor.of("#808080") + GameSettings.LANG.textOf("chat.global")
						+ senderTeam.getType().getPrefix() + sender.getName() + "§7 > §f" + message.substring(1);
			}
			case TEAM : {
				return ChatColor.of("#808080") + GameSettings.LANG.textOf("chat.team")
						+ senderTeam.getType().getPrefix() + sender.getName() + "§7 > §f" + message;
			}
			case SPECTATOR : {
				if (!senderTeam.isAlive(sender))
					return ChatColor.of("#808080") + GameSettings.LANG.textOf("chat.spec")
							+ senderTeam.getType().getPrefix() + senderTeam.getType().getPrefix() + sender.getName()
							+ "§7 > §f" + message;
				else
					return ChatColor.of("#808080") + GameSettings.LANG.textOf("chat.spec")
							+ senderTeam.getType().getPrefix() + "§7" + sender.getName() + "§7 > §f" + message;
			}
		}
		return "";
	}
}
