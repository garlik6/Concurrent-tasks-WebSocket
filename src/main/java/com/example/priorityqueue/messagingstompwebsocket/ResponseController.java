package com.example.priorityqueue.messagingstompwebsocket;


import com.example.priorityqueue.PriorityQueueApplication;
import com.example.priorityqueue.model.Response;
import com.example.priorityqueue.model.Task;
import com.example.priorityqueue.taskExecutor.TaskScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;
import java.util.concurrent.CompletableFuture;

@Controller
public class ResponseController {
    @Autowired
    private TaskScheduler taskScheduler;

    @MessageMapping("/hello")
    @SendToUser("/queue/results")
    @Async("CustomAsyncExecutor")
    public CompletableFuture<Response> response(@Payload Task task, Principal user) throws InterruptedException {
        taskScheduler.scheduleTask(task);
        Thread.sleep(task.getDuration() * 1000L);
        return CompletableFuture.completedFuture(new Response("TASK COMPLETED: " + HtmlUtils.htmlEscape(task.getName()) +
                ", DURATION: " + HtmlUtils.htmlEscape(Integer.toString(task.getDuration())) +
                ", PRIORITY: " + HtmlUtils.htmlEscape(task.getPriority().toString())
        ));
    }
}