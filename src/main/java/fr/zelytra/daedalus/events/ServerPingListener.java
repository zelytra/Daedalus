package fr.zelytra.daedalus.events;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class ServerPingListener implements Listener {
    @EventHandler
    public void onPing(ServerListPingEvent e) {

        try {
            e.setServerIcon(Bukkit.loadServerIcon(getServerIcon()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }


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

    private File getServerIcon() {
        try {
            URL url = new URL("https://github.com/zelytra/Daedalus/blob/master/img/server-icon.png");
            BufferedImage img = ImageIO.read(url);
            return new File("server-icon.png");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
