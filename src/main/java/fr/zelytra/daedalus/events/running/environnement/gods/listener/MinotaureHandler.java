package fr.zelytra.daedalus.events.running.environnement.gods.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Minotaure;
import fr.zelytra.daedalus.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MinotaureHandler implements Listener {
	private static ArrayList<Player> growlList = new ArrayList<>();

	@EventHandler
	public void playerInteract(GodSpawnEvent e) {

		if (e.getGod() == GodsEnum.MINOTAURE) {

			e.getFaction().setGod(e.getPlayer(), GodsEnum.MINOTAURE);
			new Minotaure(e.getFaction());
			growlTask();
			vfx(e.getPlayer());
		}
	}

	public void growlTask() {
		Bukkit.getScheduler().runTaskTimer(Daedalus.getInstance(), () -> {
			for (Faction team : Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {
				if (team.getGodsEnum() != GodsEnum.MINOTAURE) {
					continue;
				}
				for (Player player : team.getPlayerList()) {
					if (player == null || !team.isAlive(player))
						continue;

					List<Entity> entities = player.getNearbyEntities(50, 50, 50);
					for (Entity e : entities) {
						if (e instanceof Player && ((Player) e).getGameMode() == GameMode.SURVIVAL) {
							Player target = ((Player) e).getPlayer();
							Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager()
									.getFactionOf(target);
							if (growlList.contains(player)) {
								return;
							}

							if (playerFaction.getGodsEnum() == null) {
								growlHandler(player);
								return;
							}
							if (playerFaction.getGodsEnum() != null
									&& playerFaction.getGodsEnum() != GodsEnum.MINOTAURE) {
								if (playerFaction.getGod() != null
										&& playerFaction.getGod().getUniqueId() != target.getUniqueId()) {
									growlHandler(player);
									return;
								}
							}
						}
					}
					if (growlList.contains(player)) {
						growlList.remove(player);
					}
				}
			}
		}, 0, 50);
	}

	private void growlHandler(Player player) {
		BaseComponent txt = new TextComponent(GameSettings.LANG.textOf("event.minoSmell"));
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, txt);
		player.playSound(player.getLocation(), Sound.ENTITY_ZOGLIN_ANGRY, 2, 0.6f);
		growlList.add(player);
	}

	private void vfx(Player player) {
		Bukkit.broadcastMessage(GameSettings.LANG.textOf("godSpawn.minotaur"));
		Utils.runTotemDisplay(player);
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 10, 0.1f);
		}
	}
}
