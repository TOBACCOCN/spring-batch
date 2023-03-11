package com.example.springbatch;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

/**
 * @author TOBACCO
 * @description 针对表【my_batch_job】的数据库操作Service实现
 * @createDate 2023-03-11 13:08:12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MyBatchJobServiceImpl extends ServiceImpl<MyBatchJobMapper, MyBatchJob>
        implements MyBatchJobService {

    private final MyBatchJobMapper myBatchJobMapper;
    private final Scheduler scheduler;

    @Override
    public Result startJob(String jobId) {
        try {
            MyBatchJob myBatchJob = myBatchJobMapper.selectById(jobId);
            if (myBatchJob == null) {
                return Result.getInstance(ResultEnum.NO_JOB_ID_FOUND);
            }
            Class<?> clazz = Class.forName(myBatchJob.getClassName());
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) clazz).build();
            CronTrigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(myBatchJob.getCron())).build();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
            return Result.getInstance(ResultEnum.SUCCESS);
        } catch (Exception e) {
            ErrorPrintUtil.printErrorMsg(log, e);
            return Result.getInstance(ResultEnum.FAILED);
        }
    }
}




