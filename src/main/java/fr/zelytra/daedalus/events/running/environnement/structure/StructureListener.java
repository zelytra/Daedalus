package fr.zelytra.daedalus.events.running.environnement.structure;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.guardian.Guardian;
import fr.zelytra.daedalus.managers.structure.Structure;
import fr.zelytra.daedalus.managers.structure.StructureType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

public class StructureListener {

  public StructureListener() {
    Bukkit.getScheduler()
        .scheduleAsyncRepeatingTask(
            Daedalus.getInstance(),
            () -> {
              if (Daedalus.getInstance().getGameManager() != null
                  && Daedalus.getInstance().getGameManager().isRunning()) {

                for (Map.Entry<BoundingBox, Structure> entry :
                    Daedalus.getInstance()
                        .getStructureManager()
                        .getStructuresPosition()
                        .entrySet()) {
                  if (entry.getValue().getType() != StructureType.TEMPLE
                      || entry.getValue().hasFirstEntrance()) {
                    continue;
                  }
                  List<Player> players = new ArrayList<>();
                  for (Player player : Bukkit.getOnlinePlayers()) {
                    if (entry
                        .getKey()
                        .contains(
                            player.getLocation().getX(),
                            player.getLocation().getY(),
                            player.getLocation().getZ())) {
                      players.add(player);
                    }
                  }
                  if (!players.isEmpty()) {
                    entry.getValue().setFirstEntrance(true);
                    Bukkit.getScheduler()
                        .runTask(
                            Daedalus.getInstance(),
                            () -> new Guardian(entry.getValue().getBossSpawnLocation()));
                    return;
                  }
                }
              }
            },
            0,
            2 * 20);
  }
}
