package fr.zelytra.daedalus.events;

import fr.zelytra.daedalus.Daedalus;
import fr.zelytra.daedalus.managers.game.settings.GameSettings;
import fr.zelytra.daedalus.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class ForcedTexturePack implements Listener {
    private final String url = "https://raw.githubusercontent.com/zelytra/Daedalus/master/resources/Daedalus_1.5.zip";

    @EventHandler
    public void joinListener(PlayerJoinEvent e) {

        Bukkit.getScheduler().runTaskLater(Daedalus.getInstance(), () -> {
            e.getPlayer().setResourcePack(url);
        }, 20);

    }

    @EventHandler
    public void resourcePackStatus(PlayerResourcePackStatusEvent e) {
        switch (e.getStatus()) {
            case ACCEPTED:
                e.getPlayer().sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("player.loadTexturePack"));
                break;
            case DECLINED:
                e.getPlayer().kickPlayer(GameSettings.LANG.textOf("player.declinedTexturePack"));
                break;
            case FAILED_DOWNLOAD:
                e.getPlayer().sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("player.failedTexturePack"));
                break;
            case SUCCESSFULLY_LOADED:
                e.getPlayer().sendMessage(Message.getPlayerPrefixe() + GameSettings.LANG.textOf("player.successTexturePack"));
                break;
        }
    }
}
