package com.example.jobrunr;

import com.example.jobrunr.jobs.MyJobRequest;
import com.example.jobrunr.jobs.MyJobRequestHandler;
import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.RecurringJob;
import org.jobrunr.scheduling.JobRequestScheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.AccessBits;
import org.springframework.nativex.hint.ResourceHint;
import org.springframework.nativex.hint.TypeHint;

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
@TypeHint(types = {
	org.jobrunr.jobs.states.EnqueuedState.class ,
	org.jobrunr.jobs.states.AbstractJobState.class ,
	org.jobrunr.jobs.states.DeletedState.class ,
	org.jobrunr.jobs.states.FailedState.class ,
	org.jobrunr.jobs.states.ScheduledState.class ,
	org.jobrunr.jobs.states.SucceededState.class ,
	org.jobrunr.jobs.states.ProcessingState.class ,
	org.jobrunr.jobs.states.StateName.class ,
	org.jobrunr.jobs.states.JobState.class ,
	org.jobrunr.jobs.details.CachingJobDetailsGenerator.class,
	org.jobrunr.storage.sql.h2.H2StorageProvider.class, Job.class, RecurringJob.class,
	org.jobrunr.storage.sql.postgres.PostgresStorageProvider.class, MyJobRequest.class, MyJobRequestHandler.class}, access = AccessBits.ALL)
@SpringBootApplication
public class JobrunrApplication {

	public static void main(String[] args) throws InterruptedException {
		var applicationContext = SpringApplication.run(JobrunrApplication.class, args);
		var jobRequestScheduler = applicationContext.getBean(JobRequestScheduler.class);
		var jobId = jobRequestScheduler.enqueue(new MyJobRequest("Ronald"));
		System.out.println("the job id is " + jobId.toString());
		Thread.currentThread().join();
	}


}

