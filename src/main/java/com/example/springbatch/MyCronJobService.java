package com.example.springbatch;

import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author TOBACCO
* @description 针对表【my_cron_job】的数据库操作Service
* @createDate 2023-03-11 13:08:12
*/
public interface MyCronJobService extends IService<MyCronJob> {

    Result startJob(String jobId);
}
