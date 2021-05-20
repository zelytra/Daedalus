package fr.zelytra.daedalus.events.running.environnement.structure;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.structure.Structure;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StructureListener {


    public StructureListener() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Daedalus.getInstance(), () -> {
            for (Map.Entry<BoundingBox, Structure> entry : Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {
                List<Player> players = new ArrayList<>();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (entry.getKey().contains(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())) {
                        players.add(player);
                    }
                }
                if (!players.isEmpty()) {
                    Bukkit.getScheduler().runTask(Daedalus.getInstance(), () -> {
                        StructureEntranceEvent event = new StructureEntranceEvent(entry.getValue(), players, entry.getKey());
                        Bukkit.getPluginManager().callEvent(event);
                    });

                }
            }
        }, 0, 2 * 20);

    }
}
