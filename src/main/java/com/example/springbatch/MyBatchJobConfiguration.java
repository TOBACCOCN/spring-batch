package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.Arrays;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MyBatchJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobExecutionListener jobExecutionListener;
    private final ChunkListener chunkListener;

    @Bean(Constant.BATCH_JOB_NAME_MY_BATCH_JOB)
    public Job myBatchJob() {
        // return jobBuilderFactory.get(Constant.BATCH_JOB_NAME_MY_BATCH_JOB).start(myFirstStep()).next(mySecondStep()).next(myThirdStep()).build();
        // return jobBuilderFactory.get(Constant.BATCH_JOB_NAME_MY_BATCH_JOB).start(myFirstStep()).on("COMPLETED").to(myFlow()).end().build();
        // split 并行执行 Step 或 Flow
        return jobBuilderFactory.get(Constant.BATCH_JOB_NAME_MY_BATCH_JOB).start(myFirstStep()).split(new SimpleAsyncTaskExecutor()).add(myFlow())
                .end().listener(jobExecutionListener).build();
    }

    @Bean(Constant.BATCH_JOB_NAME_MY_BATCH_JOB + "1")
    public Job myBatchJob1() {
        return jobBuilderFactory.get(Constant.BATCH_JOB_NAME_MY_BATCH_JOB + "1").start(myFirstStep()).split(new SimpleAsyncTaskExecutor()).add(myFlow())
                .end().listener(jobExecutionListener).build();
    }

    @Bean
    public Step myFirstStep() {
        // return stepBuilderFactory.get("myFirstStep").tasklet((stepContribution, chunkContext) -> {
        //     log.info(">>>> myFirstStep executing");
        //     return RepeatStatus.FINISHED;
        // }).build();
        return stepBuilderFactory.get("myFirstStep").<String, Long>chunk(2).faultTolerant().listener(chunkListener)
                .reader(myReader()).processor(myProcessor()).writer(myWriter()).build();
    }

    private ItemProcessor<? super String, Long> myProcessor() {
        return (ItemProcessor<String, Long>) s -> s == null ? 0L : s.length();
    }

    @Bean
    public ItemWriter<? super Long> myWriter() {
        return (ItemWriter<Long>) list -> {
            list.forEach(e->log.info(">>>>> LEN: [{}]", e));
        };
    }

    @Bean
    public ItemReader<String> myReader() {
        return new ListItemReader<>(Arrays.asList("java","spring","mybatis"));
    }

    // 1. Flow 是多个 Step 的集合
    // 2.可以被多个 Job 复用
    // 3.使用 FlowBuilder来创建
    @Bean
    public Flow myFlow() {
        // return flowBuilder.from(mySecondStep()).on("COMPLETED").to(myThirdStep()).from(myThirdStep()).end();
        return new FlowBuilder<Flow>("myFlow").from(mySecondStep()).on("COMPLETED").to(myThirdStep()).build();
    }

    @Bean
    public Step mySecondStep() {
        return stepBuilderFactory.get("mySecondStep").tasklet((stepContribution, chunkContext) -> {
            chunkContext.getStepContext().getJobParameters();   // get JobParameters
            log.info(">>>> mySecondStep executing");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step myThirdStep() {
        return stepBuilderFactory.get("myThirdStep").tasklet((stepContribution, chunkContext) -> {
            log.info(">>>> myThirdStep executing");
            return RepeatStatus.FINISHED;
        }).build();
    }

}
