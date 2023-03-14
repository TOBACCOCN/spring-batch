package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Date;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class MyQuartzJobConfiguration {

    private final SimpleQuartzJob simpleQuartzJob;

    @Bean
    public MethodInvokingJobDetailFactoryBean myMethodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetObject(simpleQuartzJob);
        bean.setTargetMethod(simpleQuartzJob.getTargetMethodName());
        bean.setConcurrent(false);
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean myCronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(Objects.requireNonNull(myMethodInvokingJobDetailFactoryBean().getObject()));
        bean.setStartTime(new Date());
        bean.setCronExpression("0/10 * * * * ?");
        return bean;
    }

    @Bean
    public SchedulerFactoryBean mySchedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(myCronTriggerFactoryBean().getObject());
        return bean;
    }

}
