package com.example;

import org.jobrunr.scheduling.JobRequestScheduler;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleApplication {

	@Bean
	ApplicationRunner runner(JobRequestScheduler scheduler) {
		return args -> {
			var jobId = scheduler.enqueue(new MyJobRequest("Ronald"));
			System.out.println("the job id is " + jobId.toString());
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SimpleApplication.class, args);
	}
}
