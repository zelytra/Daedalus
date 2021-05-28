package fr.zelytra.daedalus.managers.skrink;

import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Bukkit;

public abstract class ShrinkManager {
    private static Daedalus daedalus = Daedalus.getInstance();
    public static WorkloadThread workloadThread;

    public static void startShrinking() {

        if (!daedalus.getGameManager().isRunning()) {
            return;
        }

        if (daedalus.getStructureManager().getMaze().getMaze() == null) {
            return;
        }

        Bukkit.getScheduler().runTask(daedalus, () -> {
            System.out.println("Start task");
            workloadThread.run();
        });


    }


}

