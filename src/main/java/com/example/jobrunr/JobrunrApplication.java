package com.example.jobrunr;

import org.jobrunr.jobs.AbstractJob;
import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.JobDetails;
import org.jobrunr.jobs.RecurringJob;
import org.jobrunr.jobs.context.JobDashboardLogger;
import org.jobrunr.jobs.details.CachingJobDetailsGenerator;
import org.jobrunr.jobs.filters.ElectStateFilter;
import org.jobrunr.jobs.filters.JobFilter;
import org.jobrunr.jobs.states.*;
import org.jobrunr.scheduling.JobRequestScheduler;
import org.jobrunr.spring.annotations.Recurring;
import org.jobrunr.spring.autoconfigure.metrics.JobRunrMetricsAutoConfiguration;
import org.jobrunr.storage.sql.h2.H2StorageProvider;
import org.jobrunr.storage.sql.postgres.PostgresStorageProvider;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.nativex.hint.AccessBits;
import org.springframework.nativex.hint.ResourceHint;
import org.springframework.nativex.hint.TypeHint;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ResourceHint(
	patterns = {
		"/org/jobrunr/storage/sql/common/migrations/v000__create_migrations_table.sql",
		"/org/jobrunr/storage/sql/common/migrations/v001__create_job_table.sql",
		"/org/jobrunr/storage/sql/common/migrations/v002__create_recurring_job_table.sql",
		"/org/jobrunr/storage/sql/common/migrations/v003__create_background_job_server_table.sql",
		"/org/jobrunr/storage/sql/common/migrations/v004__create_job_stats_view.sql",
		"/org/jobrunr/storage/sql/common/migrations/v005__update_job_stats_view.sql",
		"/org/jobrunr/storage/sql/common/migrations/v006__alter_table_jobs_add_recurringjob.sql",
		"/org/jobrunr/storage/sql/common/migrations/v007__alter_table_backgroundjobserver_add_delete_config.sql",
		"/org/jobrunr/storage/sql/common/migrations/v008__alter_table_jobs_increase_jobAsJson_size.sql",
		"/org/jobrunr/storage/sql/common/migrations/v009__change_jobrunr_job_counters_to_jobrunr_metadata.sql",
		"/org/jobrunr/storage/sql/common/migrations/v010__change_job_stats.sql",
		"/org/jobrunr/storage/sql/common/migrations/v011__change_sqlserver_text_to_varchar.sql"
	}
)
@TypeHint(

	types = {
		AbstractJob.class,
		AbstractJobState.class,
		Boolean.class,
		Byte.class,
		CachingJobDetailsGenerator.class,
		Character.class,
		ConcurrentHashMap.class,
		DeletedState.class,
		Double.class,
		Duration.class,
		ElectStateFilter.class,
		EnqueuedState.class,
		Enum.class,
		FailedState.class,
		Float.class,
		H2StorageProvider.class,
		Instant.class,
		Integer.class,
		JobDashboardLogger.class,
		JobDashboardLogger.JobDashboardLogLine.class,
		JobDashboardLogger.JobDashboardLogLines.class,
		Job.class,
		JobDetails.class,
		JobFilter.class,
		JobState.class,
		Long.class,
		MyJobRequest.class,
		MyJobRequestHandler.class,
		PostgresStorageProvider.class,
		ProcessingState.class,
		Recurring.class,
		RecurringJob.class,
		ScheduledState.class,
		Short.class,
		StateName.class,
		String.class,
		SucceededState.class,
		UUID.class,
		boolean.class,
		byte.class,
		char.class,
		double.class,
		float.class,
		int.class,
		long.class,
		org.jobrunr.jobs.Job.class,
		org.jobrunr.jobs.annotations.Job.class,
		short.class
	},
	access = AccessBits.ALL
)
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

