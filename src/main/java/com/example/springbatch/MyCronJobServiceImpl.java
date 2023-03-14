package com.example.springbatch;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

/**
 * @author TOBACCO
 * @description 针对表【my_cron_job】的数据库操作Service实现
 * @createDate 2023-03-11 13:08:12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MyCronJobServiceImpl extends ServiceImpl<MyCronJobMapper, MyCronJob>
        implements MyCronJobService {

    private final MyCronJobMapper myCronJobMapper;
    private final Scheduler scheduler;

    @Override
    public Result startJob(String jobId) {
        try {
            MyCronJob myCronJob = myCronJobMapper.selectById(jobId);
            if (myCronJob == null) {
                return Result.getInstance(ResultEnum.NO_JOB_ID_FOUND);
            }
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put(Constant.QUARTZ_JOB_DATA_TARGET_BEAN_NAME, myCronJob.getJobName());
            JobDetail jobDetail = JobBuilder.newJob(Job.class).setJobData(jobDataMap).build();
            CronTrigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(myCronJob.getCron())).build();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
            return Result.getInstance(ResultEnum.SUCCESS);
        } catch (Exception e) {
            ErrorPrintUtil.printErrorMsg(log, e);
            return Result.getInstance(ResultEnum.FAILED);
        }
    }
}




