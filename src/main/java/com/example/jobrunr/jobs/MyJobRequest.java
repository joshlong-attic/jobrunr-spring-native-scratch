package com.example.jobrunr.jobs;


import org.jobrunr.jobs.lambdas.JobRequest;

public class MyJobRequest implements JobRequest {

	private String name;

	public MyJobRequest(String name) {
		this.name = name;
	}

	MyJobRequest() {
	}

	@Override
	public Class<MyJobRequestHandler> getJobRequestHandler() {
		return MyJobRequestHandler.class;
	}

	public String getName() {
		return name;
	}
}