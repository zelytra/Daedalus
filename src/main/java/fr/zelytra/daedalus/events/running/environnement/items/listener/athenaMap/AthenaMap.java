package fr.zelytra.daedalus.events.running.environnement.items.listener.athenaMap;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.cooldown.Cooldown;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
  public void onRefreshMap(CustomItemUseEvent e) {
    Player player = e.getPlayer();
    if (e.getMaterial() != CustomMaterial.ATHENA_MAP) return;
    if (!Cooldown.cooldownCheck(player, CustomMaterial.ATHENA_MAP.getName())) {
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
