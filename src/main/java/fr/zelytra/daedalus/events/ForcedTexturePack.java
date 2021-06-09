package fr.zelytra.daedalus.events;

import fr.zelytra.daedalus.utils.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class ForcedTexturePack implements Listener {
    private final String url = "https://raw.githubusercontent.com/zelytra/Daedalus/master/resources/Daedalus_0.3.zip";

    @EventHandler
    public void joinListener(PlayerJoinEvent e){
        e.getPlayer().setResourcePack(url);
    }

    @EventHandler
    public void resourcePackStatus(PlayerResourcePackStatusEvent e){
        switch (e.getStatus()){
            case ACCEPTED:
                e.getPlayer().sendMessage(Message.getPlayerPrefixe()+"§6Loading texture pack...");
                break;
            case DECLINED:
                e.getPlayer().kickPlayer("§cYou need to accept the texture pack before playing");
                break;
            case FAILED_DOWNLOAD:
                e.getPlayer().sendMessage(Message.getPlayerPrefixe()+"§6Failed to download trying again...");
                break;
            case SUCCESSFULLY_LOADED:
                e.getPlayer().sendMessage(Message.getPlayerPrefixe()+"§aTexture pack loaded !");
                break;
        }
    }
}
