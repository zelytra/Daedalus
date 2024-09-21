package fr.zelytra.daedalus.events.running.environnement.items.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.events.running.environnement.items.events.CustomItemUseEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.items.CustomMaterial;
import fr.zelytra.daedalus.managers.structure.Structure;
import fr.zelytra.daedalus.managers.structure.StructureType;
import java.util.LinkedHashMap;
import java.util.Map;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.BoundingBox;

public class DivineTracker implements Listener {
  private final NamespacedKey templeKey = new NamespacedKey(Daedalus.getInstance(), "temple");

  @EventHandler(priority = EventPriority.LOWEST)
  public void interactEvent(CustomItemUseEvent e) {

    if (e.getMaterial() != CustomMaterial.DIVINE_TRACKER) return;

    Player player = e.getPlayer();
    LinkedHashMap<BoundingBox, Structure> temples = new LinkedHashMap<>();

    if (Daedalus.getInstance().getStructureManager().getStructuresPosition() == null
        || Daedalus.getInstance().getStructureManager().getStructuresPosition().isEmpty()) {
      BaseComponent txt =
          new TextComponent(GameSettings.LANG.textOf("event.divineTrackerNoStruct"));
      player.spigot().sendMessage(ChatMessageType.ACTION_BAR, txt);
      return;
    }

    for (Map.Entry<BoundingBox, Structure> entry :
        Daedalus.getInstance().getStructureManager().getStructuresPosition().entrySet()) {
      if (entry.getValue().getType() == StructureType.TEMPLE) {
        temples.put(entry.getKey(), entry.getValue());
      }
    }

    ItemStack item = e.getItem();

    if (player.isSneaking()) {

      if (!item.getItemMeta()
          .getPersistentDataContainer()
          .has(templeKey, PersistentDataType.INTEGER)) {

        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(templeKey, PersistentDataType.INTEGER, 0);
        item.setItemMeta(meta);

        if (isSummon(temples.get((temples.keySet().toArray())[0])) == null)
          printStructureDistance(
              player,
              (BoundingBox) (temples.keySet().toArray())[0],
              temples.get((temples.keySet().toArray())[0]));
        else
          printGodDistance(
              player,
              isSummon(temples.get((temples.keySet().toArray())[0])),
              temples.get((temples.keySet().toArray())[0]).getGod());

      } else {

        int value =
            item.getItemMeta()
                    .getPersistentDataContainer()
                    .get(templeKey, PersistentDataType.INTEGER)
                + 1;

        if (value >= temples.size()) {
          ItemMeta meta = item.getItemMeta();
          meta.getPersistentDataContainer().set(templeKey, PersistentDataType.INTEGER, 0);
          item.setItemMeta(meta);

          if (isSummon(temples.get((temples.keySet().toArray())[0])) == null)
            printStructureDistance(
                player,
                (BoundingBox) (temples.keySet().toArray())[0],
                temples.get((temples.keySet().toArray())[0]));
          else
            printGodDistance(
                player,
                isSummon(temples.get((temples.keySet().toArray())[0])),
                temples.get((temples.keySet().toArray())[0]).getGod());

        } else {
          if (isSummon(temples.get((temples.keySet().toArray())[value])) == null)
            printStructureDistance(
                player,
                (BoundingBox) (temples.keySet().toArray())[value],
                temples.get((temples.keySet().toArray())[value]));
          else
            printGodDistance(
                player,
                isSummon(temples.get((temples.keySet().toArray())[value])),
                temples.get((temples.keySet().toArray())[value]).getGod());

          ItemMeta meta = item.getItemMeta();
          meta.getPersistentDataContainer().set(templeKey, PersistentDataType.INTEGER, value);
          item.setItemMeta(meta);
        }
      }
    } else {
      if (!item.getItemMeta()
          .getPersistentDataContainer()
          .has(templeKey, PersistentDataType.INTEGER)) {

        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(templeKey, PersistentDataType.INTEGER, 0);
        item.setItemMeta(meta);

        if (isSummon(temples.get((temples.keySet().toArray())[0])) == null)
          printStructureDistance(
              player,
              (BoundingBox) (temples.keySet().toArray())[0],
              temples.get((temples.keySet().toArray())[0]));
        else
          printGodDistance(
              player,
              isSummon(temples.get((temples.keySet().toArray())[0])),
              temples.get((temples.keySet().toArray())[0]).getGod());

      } else {

        if (isSummon(
                temples.get(
                    (temples.keySet().toArray())
                        [
                        item.getItemMeta()
                            .getPersistentDataContainer()
                            .get(templeKey, PersistentDataType.INTEGER)]))
            == null)
          printStructureDistance(
              player,
              (BoundingBox)
                  (temples.keySet().toArray())
                      [
                      item.getItemMeta()
                          .getPersistentDataContainer()
                          .get(templeKey, PersistentDataType.INTEGER)],
              temples.get(
                  (temples.keySet().toArray())
                      [
                      item.getItemMeta()
                          .getPersistentDataContainer()
                          .get(templeKey, PersistentDataType.INTEGER)]));
        else
          printGodDistance(
              player,
              isSummon(
                  temples.get(
                      (temples.keySet().toArray())
                          [
                          item.getItemMeta()
                              .getPersistentDataContainer()
                              .get(templeKey, PersistentDataType.INTEGER)])),
              temples
                  .get(
                      (temples.keySet().toArray())
                          [
                          item.getItemMeta()
                              .getPersistentDataContainer()
                              .get(templeKey, PersistentDataType.INTEGER)])
                  .getGod());
      }
      e.getPlayer().getInventory().getItemInMainHand();

      if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.COMPASS) {
        e.setCancelled(true);
      }
    }
  }

  private void printStructureDistance(Player player, BoundingBox box, Structure structure) {
    int deltaX = (int) (box.getCenter().getX() - player.getLocation().getX());
    int deltaZ = (int) (box.getCenter().getZ() - player.getLocation().getZ());
    int distance = (int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));

    player.setCompassTarget(
        new Location(player.getWorld(), box.getCenterX(), box.getMaxY(), box.getCenterZ()));

    BaseComponent txt =
        new TextComponent(
            "§1"
                + distance
                + GameSettings.LANG.textOf("event.divineTrackerTrack")
                + structure.getGod().getName()
                + " temple");
    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, txt);
  }

  private void printGodDistance(Player player, Player target, GodsEnum god) {
    int deltaX = (int) (target.getLocation().getX() - player.getLocation().getX());
    int deltaZ = (int) (target.getLocation().getZ() - player.getLocation().getZ());
    int distance = (int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));

    player.setCompassTarget(target.getLocation());

    BaseComponent txt =
        new TextComponent(
            "§1" + distance + GameSettings.LANG.textOf("event.divineTrackerTrack") + god.getName());
    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, txt);
  }

  private Player isSummon(Structure structure) {
    for (Faction team :
        Daedalus.getInstance().getGameManager().getFactionManager().getFactionList()) {

      if (team.getGodsEnum() != null && team.getGodsEnum() == structure.getGod()) {

        if (team.getGod() != null) return team.getGod();
        else for (Player p : team.getPlayerList()) if (team.isAlive(p)) return p;
      }
    }
    return null;
  }
}
