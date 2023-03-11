package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 定时任务类
 *
 * @author zhangyonghong
 * @date 2019.6.1
 */
@Slf4j
// @Component
@RequiredArgsConstructor
public class ScheduleTask {

    private final JobLauncher jobLauncher;
    private final Job myJob;

    @Scheduled(cron = "0/10 * * * * ?")
    public void process() {
        log.info("ScheduleTask processing ...");
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("foo", "bar");
        jobParametersBuilder.addDate("date", new Date());
        try {
            jobLauncher.run(myJob, jobParametersBuilder.toJobParameters());
        } catch (Exception e) {
            ErrorPrintUtil.printErrorMsg(log, e);
        }
    }

}
