package com.example.priorityqueue;

import com.example.priorityqueue.model.Task;


@org.springframework.stereotype.Service
public class Service {

    // intentional - 2^N
    public long calculate(int n){
        if(n < 2)
            return n;
        return calculate(n - 1) + calculate(n - 2);
    }

}
