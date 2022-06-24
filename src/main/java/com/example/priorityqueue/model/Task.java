package com.example.priorityqueue.model;

import com.example.priorityqueue.model.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task implements  Serializable, Runnable {
    private int duration;
    private boolean isResponseRequired;
    private int priority;
    private String name;


    @Override
    public void run() {
        System.out.println("Task:" + name +
                " Priority:" + priority);
        try {
            Thread.sleep(1000L * duration); // to simulate actual execution time
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
