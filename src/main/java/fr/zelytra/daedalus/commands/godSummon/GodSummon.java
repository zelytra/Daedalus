package fr.zelytra.daedalus.commands.godSummon;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GodSummon implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {

		if (!(sender instanceof Player player))
			return false;

		if (!player.isOp()) {
			player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.permissionDenied"));
			return false;
		}

		if (!Daedalus.getInstance().getGameManager().isRunning()) {
			player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.pauseState"));
			return false;
		}

		if (args.length != 2) {
			player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.noArguments"));
			return false;
		}

		Player target = Bukkit.getPlayer(args[1]);

		if (target == null) {
			player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("command.playerOffline"));
			return false;
		}

		Faction playerFaction;
		try {
			playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(target);
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("ERROR team not found");
			return false;
		}

		if (playerFaction.getGod() != null) {
			player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("god.cannotSummonMore"));
			return false;
		}

		GodsEnum god;

		try {
			god = GodsEnum.valueOf(args[0].toUpperCase());
		} catch (Exception ignored) {
			player.sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("god.noGodFound"));
			return false;
		}

		GodSpawnEvent event = new GodSpawnEvent(god, playerFaction, target);
		Bukkit.getPluginManager().callEvent(event);

		return true;
	}
}
