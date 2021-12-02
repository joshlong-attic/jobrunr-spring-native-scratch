package com.example.jobrunr;

import org.jobrunr.jobs.lambdas.JobRequest;

public class MyJobRequest implements JobRequest {

	private String name;

	public MyJobRequest(String name) {
		this.name = name;
	}

	public MyJobRequest() {
	}

	public String getName() {
		return name;
	}

	@Override
	public Class<MyJobRequestHandler> getJobRequestHandler() {
		return MyJobRequestHandler.class;
	}
}
