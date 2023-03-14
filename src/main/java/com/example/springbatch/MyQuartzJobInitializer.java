package com.example.springbatch;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyQuartzJobInitializer {

    private  final MyCronJobMapper myCronJobMapper;
    private final Scheduler scheduler;

    @PostConstruct
    public void initialize() {
        try {
            List<MyCronJob> myCronJobs = myCronJobMapper.selectList(new QueryWrapper<>());
            if (myCronJobs == null) {
                return;
            }
            myCronJobs.forEach(e -> {
                try {
                    JobDataMap jobDataMap = new JobDataMap();
                    jobDataMap.put(Constant.QUARTZ_JOB_DATA_TARGET_BEAN_NAME, e.getJobName());
                    JobDetail jobDetail = JobBuilder.newJob(MyQuartzJob.class).setJobData(jobDataMap).build();
                    CronTrigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(e.getCron())).build();
                    scheduler.start();
                    scheduler.scheduleJob(jobDetail, trigger);
                } catch (Exception ex) {
                    ErrorPrintUtil.printErrorMsg(log, ex);
                }

            });
        } catch (Exception e) {
            ErrorPrintUtil.printErrorMsg(log, e);
        }
    }

}
