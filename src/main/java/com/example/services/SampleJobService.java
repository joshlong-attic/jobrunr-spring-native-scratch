package com.example.services;

import org.jobrunr.spring.annotations.Recurring;
import org.springframework.stereotype.Service;

@Service
public class SampleJobService {

    @Recurring(id = "my-recurring-job", cron = "*/5 * * * *")
    public void doSomeWork() {
        System.out.println("doing some work");
    }
}
