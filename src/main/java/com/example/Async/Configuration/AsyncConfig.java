package com.example.Async.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean("myThreadPool")
    public Executor myThreadPool(){

        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();


        //core pool size is set to 5. This defines the minimum number of threads that will be kept in the pool
        executor.setCorePoolSize(5);

        //maximum number of tasks that can wait in the queue to be executed when all core threads are busy
        //If the queue reaches its capacity limit (150 in this case), the pool can grow up to its maximum size (controlled by setMaxPoolSize),
        // and if both are full, it will reject new tasks.
        executor.setQueueCapacity(150);

        //maximum pool size is set to 10, which is the upper limit of threads that the executor can create to handle tasks.
        //If all threads (up to 10) are busy and the queue is full, any additional tasks will be rejected according to the rejection policy
        executor.setMaxPoolSize(10);

        //created by this executor will have names that start with this prefix
        //which is helpful for debugging and tracking thread activity in logs.
        executor.setThreadNamePrefix("my-thread-pool");

        // Keep extra threads alive for 60 seconds
        executor.setKeepAliveSeconds(60);

        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.initialize();

        return executor;

    }
}
