package fr.zelytra.daedalus.events;

import com.destroystokyo.paper.event.server.ServerExceptionEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ErrorListener implements Listener {

    @EventHandler
    public void onError(ServerExceptionEvent e) {

        if (e.getException().getMessage().contains("Daedalus")) {
            Bukkit.getConsoleSender().sendMessage("§c--------- ! ---------");
            Bukkit.getConsoleSender().sendMessage("§cAn error occurred. Please report this error message to the Daedalus support team");
            Bukkit.getConsoleSender().sendMessage("§cYou can contact us via Discord (http://discord.mc-daedalus.com) or directly on our Github page : http://github.mc-daedalus.com");
            Bukkit.getConsoleSender().sendMessage("§c--------- ! ---------");

        }

    }

}
