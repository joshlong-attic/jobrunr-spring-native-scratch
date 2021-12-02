package com.example.hints;

import org.jobrunr.jobs.AbstractJob;
import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.JobDetails;
import org.jobrunr.jobs.RecurringJob;
import org.jobrunr.jobs.context.JobDashboardLogger;
import org.jobrunr.jobs.details.CachingJobDetailsGenerator;
import org.jobrunr.jobs.filters.ElectStateFilter;
import org.jobrunr.jobs.filters.JobFilter;
import org.jobrunr.jobs.states.*;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.spring.annotations.Recurring;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.nativex.type.NativeConfiguration;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.nativex.hint.TypeAccess.*;

@NativeHint(trigger = JobScheduler.class, types = {

		@TypeHint(

				types = { boolean.class, byte.class, char.class, double.class, float.class, int.class, long.class,
						short.class, Float.class, Short.class, String.class, Long.class, Integer.class, Boolean.class,
						Byte.class, Character.class, Double.class,

						///
						AbstractJob.class, AbstractJobState.class,

						CachingJobDetailsGenerator.class, ConcurrentHashMap.class, DeletedState.class, Duration.class,
						ElectStateFilter.class, EnqueuedState.class, Enum.class, FailedState.class, Instant.class,
						JobDashboardLogger.class, JobDashboardLogger.JobDashboardLogLine.class,
						JobDashboardLogger.JobDashboardLogLines.class, Job.class, JobDetails.class, JobFilter.class,
						JobState.class, ProcessingState.class,
						org.jobrunr.jobs.details.CachingJobDetailsGenerator.class, Recurring.class, RecurringJob.class,
						ScheduledState.class, StateName.class,

						SucceededState.class, UUID.class,

						org.jobrunr.jobs.Job.class, org.jobrunr.jobs.annotations.Job.class, },
				access = { DECLARED_CLASSES, DECLARED_CONSTRUCTORS, DECLARED_FIELDS, DECLARED_METHODS }) })
public class JobrunrNativeConfiguration implements NativeConfiguration {

}
