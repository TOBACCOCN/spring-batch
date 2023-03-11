package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/batch")
public class DefaultController {

    private final MyBatchJobService myBatchJobService;


    @RequestMapping(value = "/startJob", method = RequestMethod.POST)
    public Object starJob(String jobId) {
        return myBatchJobService.startJob(jobId);
    }

}
