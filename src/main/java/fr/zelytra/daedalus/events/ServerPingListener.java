package fr.zelytra.daedalus.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPingListener implements Listener {
    @EventHandler
    public void onPing(ServerListPingEvent e){
        e.setMotd("§8[§6Maze-Minigame§8]   §8<< §7< §6Daedalus §7> §8>>   §8[§61.16.4/5§8]§r\n             §8<< §7<- §6daedalus.zelytra.fr §7-> §8>>");
    }
}
