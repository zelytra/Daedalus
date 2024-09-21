package fr.zelytra.daedalus.commands.location;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.channel.ChannelEnum;
import fr.zelytra.daedalus.managers.channel.MessageManager;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.structure.Structure;
import fr.zelytra.daedalus.utils.Message;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;

public class ShareLocation implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		if (!Daedalus.getInstance().getGameManager().isRunning()) {
			sender.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.noMaze"));
			return false;
		}
		Player player = (Player) sender;
		Location ploc = player.getLocation();
		if (!Daedalus.getInstance().getStructureManager().getStructuresPosition().isEmpty()) {
			for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager()
					.getStructuresPosition().entrySet()) {
				if (entry.getKey().contains(ploc.getX(), ploc.getY(), ploc.getZ())) {
					MessageManager message = new MessageManager(player,
							printLocation(player) + " §8|§6 " + entry.getValue().getType(), ChannelEnum.TEAM,
							Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player));
					message.sendMessage();
					return true;
				}
			}
			MessageManager message = new MessageManager(player, printLocation(player), ChannelEnum.TEAM,
					Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player));
			message.sendMessage();
			return true;
		} else {
			MessageManager message = new MessageManager(player, printLocation(player), ChannelEnum.TEAM,
					Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(player));
			message.sendMessage();
			return true;
		}
	}

	private String printLocation(Player player) {
		return Message.getPlayerPrefixe() + "§8" + player.getName() + ":§6 x:§f " + player.getLocation().getBlockX()
				+ " §8|§6 z:§f " + player.getLocation().getBlockZ();
	}
}
