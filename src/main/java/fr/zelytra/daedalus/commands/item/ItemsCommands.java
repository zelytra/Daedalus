package fr.zelytra.daedalus.commands.item;

import fr.zelytra.daedalus.commands.commandsHandler.HelpCommands;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.utils.Message;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ItemsCommands implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		if (!(sender instanceof Player player)) {
			return false;
		}

		if (!player.isOp()) {
			player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.permissionDenied"));
			return false;
		}

		if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
			HelpCommands help = new HelpCommands("dgive");
			help.addCommand("[customMaterial]", "{amount}");
			help.addCommand("[customMaterial]", "[amount] {player}");
			help.printPlayer(player);
			return true;

		} else if (args.length == 1) {
			if (CustomMaterial.getByName(args[0]) != null) {
				player.getInventory().addItem(new CustomItemStack(CustomMaterial.getByName(args[0]), 1).getItem());
				player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.giveItemPrefix") + 1
						+ " §6§l" + args[0] + GameSettings.LANG.textOf("command.giveItemSuffix") + player.getName());
				playItemSound(player);
				return true;
			} else {
				player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.itemExist"));
				return false;
			}

		} else if (args.length == 2 && Utils.isNumeric(args[1])) {
			if (CustomMaterial.getByName(args[0]) != null) {
				CustomItemStack cItem = new CustomItemStack(CustomMaterial.getByName(args[0]),
						Integer.parseInt(args[1]));
				player.getInventory().addItem(cItem.getItem());
				player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.giveItemPrefix")
						+ Integer.parseInt(args[1]) + " §6§l" + args[0]
						+ GameSettings.LANG.textOf("command.giveItemSuffix") + player.getName());
				playItemSound(player);
				return true;
			} else {
				player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.itemExist"));
				return false;
			}
		} else if (args.length == 3 && Utils.isNumeric(args[1])) {
			if (Bukkit.getPlayer(args[2]) == null) {
				player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.playerOffline"));
				return false;
			}
			Player target = Bukkit.getPlayer(args[2]);
			if (CustomMaterial.getByName(args[0]) != null) {
				CustomItemStack cItem = new CustomItemStack(CustomMaterial.getByName(args[0]),
						Integer.parseInt(args[1]));
				target.getInventory().addItem(cItem.getItem());
				playItemSound(target);
				player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.giveItemPrefix")
						+ Integer.parseInt(args[1]) + " §6§l" + args[0]
						+ GameSettings.LANG.textOf("command.giveItemSuffix") + target.getName());
				return true;
			} else {
				player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.itemExist"));
				return false;
			}
		} else {
			player.sendMessage(Message.getHelp("dgive"));
			return false;
		}
	}

	private void playItemSound(Player player) {
		player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
	}
}
