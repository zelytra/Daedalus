package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DionysosBeer implements Listener {

    @EventHandler
    public void onRightClick(CustomItemUseEvent e) {

        if (e.getMaterial() != CustomMaterial.DIONYSOS_BEER) return;
    }
}
