package com.example.priorityqueue.taskExecutor;
import com.example.priorityqueue.model.Task;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

@Service
public class TaskScheduler {
    private final ExecutorService priorityJobPoolExecutor;
    private final PriorityBlockingQueue<Task> priorityQueue;

    public TaskScheduler() {
        priorityJobPoolExecutor = Executors.newFixedThreadPool(2);
        priorityQueue = new PriorityBlockingQueue<Task>(
               10, Comparator.comparing(Task::getPriority));
        ExecutorService priorityJobScheduler = Executors.newSingleThreadExecutor();
        priorityJobScheduler.execute(() -> {
            while (true) {
                try {
                    priorityJobPoolExecutor.execute(priorityQueue.take());
                } catch (InterruptedException e) {
                    // exception needs special handling
                    break;
                }
            }
        });
    }

    public void scheduleTask(Task task) {
        priorityQueue.add(task);
    }
}
