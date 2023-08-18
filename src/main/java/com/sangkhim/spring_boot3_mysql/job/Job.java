package com.sangkhim.spring_boot3_mysql.job;

import com.sangkhim.spring_boot3_mysql.service.PostService;
import io.micrometer.common.lang.NonNullApi;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@NonNullApi
@Slf4j
@Setter
@DisallowConcurrentExecution
public class Job extends QuartzJobBean {

  private PostService userService;

  @Autowired
  public void autowire(final PostService userService) {
    this.userService = userService;
  }

  @Override
  public void executeInternal(final JobExecutionContext context) {
    log.info("Job execute {}", context.getJobDetail().getKey().getName());
    if (false) {
      this.unScheduleJob(context);
    }
  }

  private void unScheduleJob(JobExecutionContext context) {
    try {
      log.info("Job unscheduled {}", context.getTrigger().getKey());
      context.getScheduler().unscheduleJob(context.getTrigger().getKey());
    } catch (SchedulerException e) {
      log.error("Error while unscheduled job", e);
    }
  }
}
