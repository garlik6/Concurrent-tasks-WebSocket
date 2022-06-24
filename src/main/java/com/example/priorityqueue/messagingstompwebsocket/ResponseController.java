package com.example.priorityqueue.messagingstompwebsocket;


import com.example.priorityqueue.model.Response;
import com.example.priorityqueue.model.Task;
import com.example.priorityqueue.taskExecutor.TaskScheduler;
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
    TaskScheduler taskScheduler = new TaskScheduler();
    @MessageMapping("/hello")
    @SendToUser("/queue/results")
    @Async
    public CompletableFuture<Response> response(@Payload Task task, Principal user){
        taskScheduler.scheduleTask(task);
        return CompletableFuture.completedFuture(new Response("TASK COMPLETED: " + HtmlUtils.htmlEscape(task.getName()) +
                ", DURATION: " + HtmlUtils.htmlEscape(Integer.toString(task.getDuration())) +
                ", PRIORITY: " + HtmlUtils.htmlEscape(Integer.toString(task.getPriority()))
        ));
    }
}