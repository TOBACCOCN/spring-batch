package com.example.springbatch;

import com.example.springbatch.MyBatchJob;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author TOBACCO
* @description 针对表【my_batch_job】的数据库操作Service
* @createDate 2023-03-11 13:08:12
*/
public interface MyBatchJobService extends IService<MyBatchJob> {

    Result startJob(String jobId);
}
