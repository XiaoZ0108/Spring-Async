package com.example.Async.Controller;

import com.example.Async.Service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @Autowired
    @GetMapping("mail")
    public String sendMail() throws InterruptedException{
        String [] email=new String[] {"e1@g.com","e2@g.com","e3@g.com","e4@g.com","e5@g.com"};
        for(String mail:email){
            asyncService.sendMail(mail);
        }
        return "ok";
    }

    @Autowired
    @GetMapping("mail2")
    public String sendMail2() throws InterruptedException{
        String [] email=new String[] {"e1@g.com","e2@g.com","e3@g.com","e4@g.com","e5@g.com"};
        CompletableFuture<String>[] futures = new CompletableFuture[email.length];



        for(int x=0;x<email.length;x++){

            futures[x]=asyncService.sendMail2(email[x]);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);
        allOf.join();

        StringBuilder result = new StringBuilder();
        for (CompletableFuture<String> future : futures) {
            try {
                result.append(future.get()).append("\n");  // Get the result of each future
            } catch (Exception e) {
                result.append("Error: ").append(e.getMessage()).append("\n");
            }
        }

        return result.toString();
    }

}
