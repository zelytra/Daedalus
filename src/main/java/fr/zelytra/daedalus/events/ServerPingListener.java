package fr.zelytra.daedalus.events;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPingListener implements Listener {
    @EventHandler
    public void onPing(ServerListPingEvent e) {
        if (Daedalus.getInstance().getGameManager().isStarted()) {
            e.setMotd("§6| §2Maze-Game §6ᒥ §8<< §6Daedalus §8>> §6ᒧ §2daedalus.§azelytra.fr     §6| §7Game Status §6: §fMaze Generation in progress...");
            return;
        }
        switch (Daedalus.getInstance().getGameManager().getState()) {
            case WAIT:
                e.setMotd("§6| §2Maze-Game §6ᒥ §8<< §6Daedalus §8>> §6ᒧ §2daedalus.§azelytra.fr     §6| §7Game Status §6: §fWaiting for players...");
                break;
            case RUNNING:
                e.setMotd("§6| §2Maze-Game §6ᒥ §8<< §6Daedalus §8>> §6ᒧ §2daedalus.§azelytra.fr     §6| §7Game Status §6: §fGame running ! §b§kON");
                break;
            case FINISHED:
                e.setMotd("§6| §2Maze-Game §6ᒥ §8<< §6Daedalus §8>> §6ᒧ §2daedalus.§azelytra.fr     §6| §7Game Status §6: §fWaiting for §cRestart §f!");
                break;
        }


    }
}
