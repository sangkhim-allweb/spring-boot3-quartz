package com.sangkhim.spring_boot3_quartz.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sangkhim.spring_boot3_quartz.model.dto.JobDTO;
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
    JobDTO jobDTO = null;
    try {
      jobDTO =
          new ObjectMapper()
              .readValue(context.getMergedJobDataMap().get("jobDTO").toString(), JobDTO.class);
    } catch (JsonProcessingException e) {
      log.info(e.toString());
    }
    mailService.send(jobDTO.getFrom(), jobDTO.getTo(), jobDTO.getSubject(), jobDTO.getBody());
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
