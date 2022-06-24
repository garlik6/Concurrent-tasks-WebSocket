package com.example.priorityqueue.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Job implements Runnable {
    private String jobName;
    private JobPriority jobPriority;

    private int duration;


    @Override
    public void run() {
        System.out.println("Job:" + jobName +
                " Priority:" + jobPriority);
        try {
            Thread.sleep(1000L * duration); // to simulate actual execution time
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // standard setters and getters
}