package com.example.springbatch;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleApplicationContextAware implements ApplicationContextAware {

    @Setter
    private static ApplicationContext myApplicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setMyApplicationContext(applicationContext);
        log.debug(">>>>> APPLICATION_CONTEXT_INJECTION_SUCCESS");
    }

    public static ApplicationContext getApplicationContext() {
        return myApplicationContext;
    }
}
