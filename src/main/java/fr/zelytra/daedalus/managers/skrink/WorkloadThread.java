package fr.zelytra.daedalus.managers.skrink;

import com.google.common.collect.Queues;
import fr.zelytra.daedalus.Daedalus;
import java.util.ArrayDeque;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class WorkloadThread implements Runnable {
  private static final int MAX_MS_PER_TICK = 35;
  @Getter private int radius = 595;

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

    task =
        Bukkit.getScheduler()
            .runTaskTimer(
                Daedalus.getInstance(),
                () -> {
                  long stopTime = System.currentTimeMillis() + MAX_MS_PER_TICK;
                  int count = 0;
                  while (!workloadDeque.isEmpty()
                      && System.currentTimeMillis() <= stopTime
                      && count <= 150) {

                    WallBreaker wallBreaker = (WallBreaker) workloadDeque.poll();
                    wallBreaker.compute();
                    if (wallBreaker.isMarker()) radius -= 1;

                    count++;
                  }

                  if (workloadDeque.isEmpty()) cancelTask();
                },
                0,
                1);
  }

  private void cancelTask() {
    Bukkit.getScheduler().cancelTask(this.task.getTaskId());
  }
}
