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

import java.time.LocalTime;

import java.security.Principal;
import java.util.concurrent.CompletableFuture;

@Controller
public class ResponseController {
    @Autowired
    Service service;

    @MessageMapping("/hello/false")
    @SendToUser("/queue/results")
    @Async("CustomAsyncExecutor")
    public CompletableFuture<Response> response(@Payload Task task, Principal user) throws InterruptedException {

        LocalTime now = LocalTime.now();
        long n = service.calculate(task.getDuration());
        return CompletableFuture.completedFuture(
                new Response("<b>TASK COMPLETED: </b>" + HtmlUtils.htmlEscape(task.getName()) +
                        ", <b>RESULT</b>: " + n + ", <b>HIGH PRIORITY?</b>:" + task.isPriority())
        );
    }
}