package fr.zelytra.daedalus.events.running.environnement.gods.listener;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.commands.revive.HadesRevive;
import fr.zelytra.daedalus.events.running.environnement.gods.events.GodSpawnEvent;
import fr.zelytra.daedalus.events.running.players.DeathHandler.events.DefinitiveDeathEvent;
import fr.zelytra.daedalus.managers.faction.Faction;
import fr.zelytra.daedalus.managers.gods.GodsEnum;
import fr.zelytra.daedalus.managers.gods.list.Hades;
import fr.zelytra.daedalus.utils.Message;
import fr.zelytra.daedalus.utils.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HadesHandler implements Listener {

    @EventHandler
    public void playerInteract(GodSpawnEvent e) {

        if (e.getGod() == GodsEnum.HADES) {

            e.getFaction().setGod(e.getPlayer(), GodsEnum.HADES);
            new Hades(e.getFaction());
            vfx(e.getPlayer());

        }

    }

    @EventHandler
    public void playerInteract(PlayerDeathEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            if (e.getEntity().getKiller() != null) {
                try {
                    Player killer = e.getEntity().getKiller();
                    Faction playerFaction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(killer);
                    if (playerFaction.getGod() == null) {
                        return;
                    }
                    if (playerFaction.getGodsEnum() == GodsEnum.HADES) {
                        killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 1, false, false, true));
                    }
                } catch (Exception exception) {
                    System.out.println("ERROR team not found");
                }


            }
        }
    }

    @EventHandler
    public void onDefinitiveDeathEvent(DefinitiveDeathEvent e) {
        if (Daedalus.getInstance().getGameManager().isRunning()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                Faction faction = Daedalus.getInstance().getGameManager().getFactionManager().getFactionOf(p);
                if (faction.getGodsEnum() == GodsEnum.HADES && faction.getGod() != null && !HadesRevive.hadesHasRevive) {
                    TextComponent processMessage = Component.text()
                            .content(Message.getPlayerPrefixe())
                            .append(Component.text().content("§8" + e.getPlayer().getName() + "§6 can be revive..."))
                            .append(Component.text().content("[REVIVE]").color(NamedTextColor.GREEN).clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/hadesrevive " + e.getPlayer().getName())))
                            .build();

                    faction.getGod().sendMessage(processMessage);
                }
            }
        }

    }

    private void vfx(Player player) {
        Bukkit.broadcastMessage("§4§l☠ Hades as appear in the maze ☠");
        Utils.runTotemDisplay(player);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 10, 0.1f);
        }
    }

}
