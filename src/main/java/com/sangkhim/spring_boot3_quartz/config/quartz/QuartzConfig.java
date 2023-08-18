package com.sangkhim.spring_boot3_quartz.config.quartz;

import com.sangkhim.spring_boot3_quartz.config.quartz.constant.QuartzScheduler;
import de.chandre.quartz.spring.AutowiringSpringBeanJobFactory;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@RequiredArgsConstructor
public class QuartzConfig {

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean(
      final @Autowired ApplicationContext applicationContext)
      throws SchedulerException, IOException {

    AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
    jobFactory.setApplicationContext(applicationContext);

    StdSchedulerFactory stdSchedulerFactory =
        new StdSchedulerFactory(QuartzScheduler.QUARTZ_PROPERTIES);

    SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
    scheduler.setSchedulerFactory(stdSchedulerFactory);
    scheduler.setQuartzProperties(this.quartzProperties());
    scheduler.setConfigLocation(new ClassPathResource(QuartzScheduler.QUARTZ_PROPERTIES));
    scheduler.setJobFactory(jobFactory);
    scheduler.setDataSource(this.quartzDataSource());
    scheduler.setWaitForJobsToCompleteOnShutdown(true);
    scheduler.setAutoStartup(true);

    return scheduler;
  }

  @Bean
  @Primary
  @QuartzDataSource
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource quartzDataSource() {
    return DataSourceBuilder.create().build();
  }

  public Properties quartzProperties() throws IOException {

    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    propertiesFactoryBean.setLocation(new ClassPathResource(QuartzScheduler.QUARTZ_PROPERTIES));
    propertiesFactoryBean.afterPropertiesSet();
    return propertiesFactoryBean.getObject();
  }
}
