package com.example.springbatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleQuartzJob  {

    public String getTargetMethodName() {
        return "execute";
    }

    protected void execute() {
        log.info(">>>>> execute ...");
    }

}
