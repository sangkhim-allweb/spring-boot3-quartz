package com.sangkhim.spring_boot3_quartz.job;

import com.sangkhim.spring_boot3_quartz.service.MailService;
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

  private MailService mailService;

  @Autowired
  public void autowire(final MailService mailService) {
    this.mailService = mailService;
  }

  @Override
  public void executeInternal(final JobExecutionContext context) {
    log.info("Job execute {}", context.getJobDetail().getKey().getName());
    mailService.send(
        context.getJobDetail().getJobDataMap().get("from").toString(),
        context.getJobDetail().getJobDataMap().get("to").toString(),
        context.getJobDetail().getJobDataMap().get("subject").toString(),
        context.getJobDetail().getJobDataMap().get("body").toString());
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
