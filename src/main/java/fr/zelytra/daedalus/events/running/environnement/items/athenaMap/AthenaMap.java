package fr.zelytra.daedalus.events.running.environnement.items.athenaMap;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.items.CustomItemStack;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

public class AthenaMap implements Listener {
    private static MapView mapView = Bukkit.createMap(Bukkit.getWorld(Daedalus.WORLD_NAME));
    private int itemCooldown = 15;
    static {
        mapView.setScale(MapView.Scale.CLOSEST);
        mapView.setTrackingPosition(true);
        mapView.addRenderer(new MapRender());
    }


    @EventHandler
    public void onRefreshMap(PlayerInteractEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if ((e.getHand() == EquipmentSlot.HAND && CustomItemStack.hasCustomItemInMainHand(CustomMaterial.ATHENA_MAP.getName(), e.getPlayer())) || (e.getHand() == EquipmentSlot.OFF_HAND && CustomItemStack.hasCustomItemInOffHand(CustomMaterial.ATHENA_MAP.getName(), e.getPlayer()))) {
                    Player player = e.getPlayer();

                    if(!Cooldown.cooldownCheck(player,CustomMaterial.ATHENA_MAP.getName())){
                        return;
                    }
                    new Cooldown(player, itemCooldown, CustomMaterial.ATHENA_MAP.getName());

                    MapMeta mapMeta = (MapMeta) e.getItem().getItemMeta();
                    mapView.setCenterX(player.getLocation().getBlockX());
                    mapView.setCenterZ(player.getLocation().getBlockZ());
                    mapMeta.setMapView(mapView);
                    e.getItem().setItemMeta(mapMeta);


                }
            }
        }
    }
}
