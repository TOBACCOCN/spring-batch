package com.example.springbatch;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class MyJobExecutionListener implements JobExecutionListener {

    @Getter
    private Map<String, JobParameter> parameters;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info(">>>>> jobName: [{}], doing before...", jobExecution.getJobInstance().getJobName());
        parameters = jobExecution.getJobParameters().getParameters();
        jobExecution.getJobParameters().getParameters().entrySet().forEach(entry->log.info(">>>>> param: [{}] = [{}]", entry.getKey(), entry.getValue()));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info(">>>>> jobName: [{}], doing after...", jobExecution.getJobInstance().getJobName());
    }
}
