package com.example.priorityqueue.messagingstompwebsocket;

import com.example.priorityqueue.Service;
import com.example.priorityqueue.model.Response;
import com.example.priorityqueue.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
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
    Service service;

    @MessageMapping("/hello")
    @SendToUser("/queue/results")
    @Async("CustomAsyncExecutor")
    public CompletableFuture<Response> response(@Payload Task task, Principal user) throws InterruptedException {
        long n = service.calculate(task.getDuration());
        return CompletableFuture.completedFuture(
                new Response("TASK COMPLETED: " + HtmlUtils.htmlEscape(task.getName()) +
                        ", RESULT: " + n)
        );
    }
}