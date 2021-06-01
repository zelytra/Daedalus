package fr.zelytra.daedalus.managers.skrink;

import com.google.common.collect.Queues;
import fr.zelytra.daedalus.Daedalus;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayDeque;

public class WorkloadThread implements Runnable {
    private static final int MAX_MS_PER_TICK = 35;
    private int totalCount = 0;
    private int initialSize = 1;

    private final ArrayDeque<Workload> workloadDeque;
    private BukkitTask task;

    public WorkloadThread() {
        workloadDeque = Queues.newArrayDeque();
    }

    public void addLoad(Workload workload) {
        workloadDeque.add(workload);
        initialSize = workloadDeque.size();
    }

    @Override
    public void run() {
        initialSize = workloadDeque.size();
        task = Bukkit.getScheduler().runTaskTimer(Daedalus.getInstance(), () -> {
            long stopTime = System.currentTimeMillis() + MAX_MS_PER_TICK;
            int count = 0;
            while (!workloadDeque.isEmpty() || System.currentTimeMillis() <= stopTime || count <= 100) {
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

    public int getTotalCount() {
        return workloadDeque.size();
    }

    public int getInitialSize() {
        return initialSize;
    }
}
