package fr.zelytra.daedalus.events;

import fr.zelytra.daedalus.Daedalus;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPingListener implements Listener {
	@EventHandler
	public void onPing(ServerListPingEvent e) {

		try {
			e.setServerIcon(Bukkit.loadServerIcon(getServerIcon()));
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		if (Daedalus.getInstance().getGameManager().isStarted()) {
			e.setMotd(
					"§6| §2Maze-Game §6ᒥ §8<< §6Daedalus §8>> §6ᒧ §§amc-daedalus.com        §6| §7Game Status §6: §fMaze Generation in progress...");
			return;
		}
		switch (Daedalus.getInstance().getGameManager().getState()) {
			case WAIT :
				e.setMotd(
						"§6| §2Maze-Game §6ᒥ §8<< §6Daedalus §8>> §6ᒧ §amc-daedalus.com        §6| §7Game Status §6: §fWaiting for players...");
				break;
			case RUNNING :
				e.setMotd(
						"§6| §2Maze-Game §6ᒥ §8<< §6Daedalus §8>> §6ᒧ §amc-daedalus.com        §6| §7Game Status §6: §fGame running ! §b§kON");
				break;
			case FINISHED :
				e.setMotd(
						"§6| §2Maze-Game §6ᒥ §8<< §6Daedalus §8>> §6ᒧ §amc-daedalus.com        §6| §7Game Status §6: §fWaiting for §cRestart §f!");
				break;
		}
	}

	private File getServerIcon() {
		try {
			URL url = new URL("https://raw.githubusercontent.com/zelytra/Daedalus/master/img/server-icon.png");
			BufferedImage img = ImageIO.read(url);
			File file = new File("server-icon.png");
			if (!file.exists())
				file.createNewFile();
			ImageIO.write(img, "png", file);
			return file;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
