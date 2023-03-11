package com.example.springbatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
public class SpringBatchStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchStarter.class, args);
    }

}
