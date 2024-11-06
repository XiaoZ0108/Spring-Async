package com.example.Async.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncService {

    @Async("myThreadPool")
    public void sendMail(String name) throws InterruptedException{
        Thread.sleep(new Random().nextInt(3000));
        log.info("Email sent to {}",name);
    }

    @Async("myThreadPool")
    public CompletableFuture<String> sendMail2(String name) throws InterruptedException{
        Thread.sleep(new Random().nextInt(3000));
        log.info("Email sent to {}",name);
        return CompletableFuture.completedFuture("Email sent to "+name);

    }
}
