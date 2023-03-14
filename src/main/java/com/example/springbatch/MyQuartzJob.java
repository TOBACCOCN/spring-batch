package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MyQuartzJob extends QuartzJobBean {

    private final JobLauncher jobLauncher;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("foo", "bar");
        jobParametersBuilder.addDate("date", new Date());

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        if (jobDataMap == null) {
            log.error(">>>>> NONE QUARTZ JOB_DATA_MAP FOUND WHERE BATCH_JOB NAME IS IN");
            return;
        }

        String targetBeanName = (String) jobDataMap.get(Constant.QUARTZ_JOB_DATA_TARGET_BEAN_NAME);
        Job job = SimpleApplicationContextAware.getApplicationContext().getBean(targetBeanName, Job.class);
        jobDataMap.entrySet().forEach(entry -> {
            addParameter(jobParametersBuilder, entry);
        });

        try {
            jobLauncher.run(job, jobParametersBuilder.toJobParameters());
        } catch (Exception e) {
            ErrorPrintUtil.printErrorMsg(log, e);
        }
    }

    private void addParameter(JobParametersBuilder jobParametersBuilder, Map.Entry<String, Object> entry) {
        Object value = entry.getValue();
        if (value == null) {
            return;
        }

        if (value instanceof Long || value instanceof Integer) {
            jobParametersBuilder.addLong(entry.getKey(), (Long) value);
        } else if (value instanceof String) {
            jobParametersBuilder.addString(entry.getKey(), (String) value);
        } else if (value instanceof Date) {
            jobParametersBuilder.addDate(entry.getKey(), (Date) value);
        }
    }
}
