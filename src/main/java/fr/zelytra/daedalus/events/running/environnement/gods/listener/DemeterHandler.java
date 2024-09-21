package fr.zelytra.daedalus.events.running.environnement.gods.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.game.time.event.EpisodeChangeEvent;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Demeter;
import fr.zelytra.daedalus.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DemeterHandler implements Listener {

  @EventHandler
  public void playerInteract(GodSpawnEvent e) {

    if (e.getGod() == GodsEnum.DEMETER) {

      e.getFaction().setGod(e.getPlayer(), GodsEnum.DEMETER);
      new Demeter(e.getFaction());
      vfx(e.getPlayer());
    }
  }

  @EventHandler
  public void episodeChange(EpisodeChangeEvent e) {
    for (Faction faction :
        Daedalus.getInstance().getGameManager().getFactionManager().getFactionList())
      if (faction.getGodsEnum() != null && faction.getGodsEnum() == GodsEnum.DEMETER)
        for (Player player : faction.getPlayerList()) player.setLevel(player.getLevel() + 7);
  }

  private void vfx(Player player) {
    Bukkit.broadcastMessage(GameSettings.LANG.textOf("godSpawn.demeter"));
    Utils.runTotemDisplay(player);
    for (Player p : Bukkit.getOnlinePlayers()) {
      p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 10, 0.1f);
    }
  }
}
