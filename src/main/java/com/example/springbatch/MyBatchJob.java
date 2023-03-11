package com.example.springbatch;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName my_batch_job
 */
@TableName(value ="my_batch_job")
@Data
public class MyBatchJob implements Serializable {
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
     * class 名称
     */
    @TableField(value = "CLASS_NAME")
    private String className;

    /**
     * cron 表达式
     */
    @TableField(value = "CRON")
    private String cron;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}