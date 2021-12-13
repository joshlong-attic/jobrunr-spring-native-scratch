package com.example;

import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.stereotype.Component;

@Component
public class MyJobRequestHandler implements JobRequestHandler<MyJobRequest> {

	@Override
	public void run(MyJobRequest jobRequest) {
		System.out.println("Hello from " + jobRequest.getName());
	}

}
