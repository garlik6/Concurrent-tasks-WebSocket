package com.example.priorityqueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class PriorityQueueApplication {
    public static void main(String[] args) {
        SpringApplication.run(PriorityQueueApplication.class, args);
    }
    @Bean(name = "CustomAsyncExecutor")
    public Executor customThreadPoolTaskExecutor() {
        return getExecutor();
    }

    @Bean(name = "CustomAsyncExecutor1")
    public Executor customThreadPoolTaskExecutor1() {
        return getExecutor();
    }

    private Executor getExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(5);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("Async_Thread_");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }
}

