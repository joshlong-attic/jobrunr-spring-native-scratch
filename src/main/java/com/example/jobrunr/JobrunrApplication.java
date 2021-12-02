package com.example.jobrunr;

import org.jobrunr.scheduling.JobRequestScheduler;
import org.jobrunr.spring.autoconfigure.metrics.JobRunrMetricsAutoConfiguration;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = JobRunrMetricsAutoConfiguration.class)
public class JobrunrApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(JobrunrApplication.class, args);
		Thread.currentThread().join();
	}

	@Bean
	ApplicationRunner runner(JobRequestScheduler scheduler) {
		return args -> {
			var jobId = scheduler.enqueue(new MyJobRequest("Ronald"));
			System.out.println("the job id is " + jobId.toString());
		};
	}

}
