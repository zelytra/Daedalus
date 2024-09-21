package fr.zelytra.daedalus.commands.broadcast;

import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.utils.Message;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Broadcast implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg) {
		if (!(sender instanceof Player))
			return false;

		if (!sender.isOp()) {
			sender.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.permissionDenied"));
			return false;
		}

		if (arg.length >= 2) {
			if (arg[0].equalsIgnoreCase("all")) {
				String message = "§c";
				for (int x = 1; x < arg.length; x++) {
					message += arg[x] + " ";
				}

				BaseComponent text = new TextComponent(Message.broadcast() + message);
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.sendTitle("", message, 5, 50, 5);
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, text);
					p.playSound(p.getLocation(), Sound.EVENT_RAID_HORN, 1000, 1);
				}
				Bukkit.broadcastMessage(Message.broadcast() + message);

				return true;
			}
			if (arg[0].equalsIgnoreCase("msg")) {
				String message = "§c";
				for (int x = 1; x < arg.length; x++) {
					message += arg[x] + " ";
				}
				Bukkit.broadcastMessage(Message.broadcast() + message);
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.playSound(p.getLocation(), Sound.EVENT_RAID_HORN, 1000, 1);
				}
				return true;
			}
			if (arg[0].equalsIgnoreCase("title")) {
				String message = "§c";
				for (int x = 1; x < arg.length; x++) {
					message += arg[x] + " ";
				}
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.sendTitle(Message.broadcast(), message, 5, 50, 5);
					p.playSound(p.getLocation(), Sound.EVENT_RAID_HORN, 1000, 1);
				}
				return true;
			}
			if (arg[0].equalsIgnoreCase("hotbar")) {
				String message = "§c";
				for (int x = 1; x < arg.length; x++) {
					message += arg[x] + " ";
				}
				BaseComponent text = new TextComponent(Message.broadcast() + message);
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.spigot().sendMessage(ChatMessageType.ACTION_BAR, text);
					p.playSound(p.getLocation(), Sound.EVENT_RAID_HORN, 1000, 1);
				}
				return true;
			}
		}
		return false;
	}
}
