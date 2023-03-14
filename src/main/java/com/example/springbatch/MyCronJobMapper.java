package com.example.springbatch;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author TOBACCO
* @description 针对表【my_cron_job】的数据库操作Mapper
* @createDate 2023-03-11 13:08:12
* @Entity com.example.springbatch.MyCronJob
*/
@Mapper
public interface MyCronJobMapper extends BaseMapper<MyCronJob> {

}




