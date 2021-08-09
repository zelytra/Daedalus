package fr.zelytra.daedalus.events;

import com.destroystokyo.paper.event.server.ServerExceptionEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ErrorListener implements Listener {

    @EventHandler
    public void onError(ServerExceptionEvent e) {
        System.out.println(e.getException().getMessage());
        System.out.println("");
        System.out.println(e.getException().getCause());
        System.out.println("");
        System.out.println(e.getException().getStackTrace());
        System.out.println("");
        System.out.println(e.getException().getLocalizedMessage());
        for (StackTraceElement stackTraceElement : e.getException().getStackTrace()) {
            System.out.println(stackTraceElement.getClassName());
            if (stackTraceElement.getClassName().contains("fr.zelytra.daedalus")) {
                System.out.println("error found");
                return;
            }
        }

    }

}
