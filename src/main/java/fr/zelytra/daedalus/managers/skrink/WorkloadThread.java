package fr.zelytra.daedalus.managers.skrink;

import com.google.common.collect.Queues;
import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayDeque;

public class WorkloadThread implements Runnable {
    private static final int MAX_MS_PER_TICK = 35;

    private final ArrayDeque<Workload> workloadDeque;
    private BukkitTask task;

    public WorkloadThread() {
        workloadDeque = Queues.newArrayDeque();
    }

    public void addLoad(Workload workload) {
        workloadDeque.add(workload);
    }

    @Override
    public void run() {
        task = Bukkit.getScheduler().runTaskTimer(Daedalus.getInstance(), () -> {
            long stopTime = System.currentTimeMillis() + MAX_MS_PER_TICK;
            int count = 0;
            while (!workloadDeque.isEmpty() && System.currentTimeMillis() <= stopTime && (workloadDeque.size() <= 1000000 ? count < 100 : count < 250)) {
                workloadDeque.poll().compute();
                count++;
            }
            if (workloadDeque.isEmpty())
                cancelTask();
        }, 0, 1);

    }

    private void cancelTask() {
        Bukkit.getScheduler().cancelTask(this.task.getTaskId());
    }
}
