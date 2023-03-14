package com.example.springbatch;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName my_cron_job
 */
@TableName(value ="my_cron_job")
@Data
public class MyCronJob implements Serializable {
    /**
     * 
     */
    @TableId(value = "JOB_ID")
    private Long jobId;

    /**
     * 版本
     */
    @TableField(value = "VERSION")
    private Long version;

    /**
     * job 名称
     */
    @TableField(value = "JOB_NAME")
    private String jobName;

    /**
     * cron 表达式
     */
    @TableField(value = "CRON")
    private String cron;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}