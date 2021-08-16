package fr.zelytra.daedalus.events.running.players;

import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerDropListener implements Listener {

    private final List<CustomMaterial> whitelist = new ArrayList<>();

    {
        whitelist.add(CustomMaterial.DIVINE_FRAGMENT);
        whitelist.add(CustomMaterial.DIVINE_HEART);
        whitelist.add(CustomMaterial.DIVINE_TRACKER);

        whitelist.add(CustomMaterial.APHRODITE_TOTEM);
        whitelist.add(CustomMaterial.ARES_TOTEM);
        whitelist.add(CustomMaterial.ARTEMIS_TOTEM);
        whitelist.add(CustomMaterial.ATHENA_TOTEM);
        whitelist.add(CustomMaterial.DEMETER_TOTEM);
        whitelist.add(CustomMaterial.DIONYSUS_TOTEM);
        whitelist.add(CustomMaterial.HADES_TOTEM);
        whitelist.add(CustomMaterial.HERMES_TOTEM);
        whitelist.add(CustomMaterial.MINOTAUR_TOTEM);
        whitelist.add(CustomMaterial.POSEIDON_TOTEM);
        whitelist.add(CustomMaterial.ZEUS_TOTEM);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {

        if (CustomItemStack.hasTag(e.getItemDrop().getItemStack()) && !whitelist.contains(CustomItemStack.getCustomMaterial(e.getItemDrop().getItemStack())))
            e.setCancelled(true);

    }
}
