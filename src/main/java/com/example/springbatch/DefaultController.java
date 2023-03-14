package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/batch")
public class DefaultController {

    private final MyCronJobService myCronJobService;

    @RequestMapping(value = "/startJob", method = RequestMethod.POST)
    @ResponseBody
    public Object starJob(String jobId) {
        log.info(">>>>> startJob, jobId: [{}]", jobId);
        return myCronJobService.startJob(jobId);
    }

}
