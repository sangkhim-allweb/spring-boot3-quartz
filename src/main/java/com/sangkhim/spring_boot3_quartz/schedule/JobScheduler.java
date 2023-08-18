package com.sangkhim.spring_boot3_quartz.schedule;

import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import com.sangkhim.spring_boot3_quartz.job.Job;
import com.sangkhim.spring_boot3_quartz.model.dto.PostDTO;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component("jobScheduler")
public class JobScheduler {
  private final SchedulerFactoryBean schedulerFactoryBean;

  public String execute(PostDTO postDTO) {
    String identity = UUID.randomUUID().toString();
    try {
      Scheduler scheduler = schedulerFactoryBean.getScheduler();
      scheduler.start();

      JobDetail job =
          newJob(Job.class)
              .withIdentity(identity, "GROUP1")
              .usingJobData("email", "sangkhim@gmail.com")
              .build();

      Trigger trigger =
          newTrigger()
              .forJob(job)
              .withIdentity(identity, "GROUP1")
              .startAt(new Date())
              .withSchedule(calendarIntervalSchedule().withIntervalInSeconds(10))
              .endAt(new Date("2023/12/31"))
              .build();

      scheduler.scheduleJob(job, trigger);
    } catch (SchedulerException exception) {
      log.error("Error while scheduling mail job", exception);
    }

    return identity;
  }
}
