package com.tsaidenis.mainservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class Loging {

    @Around("@annotation(com.tsaidenis.mainservice.aspect.LogA)")
    public void beforeCreate(){
        log.info("before create создание");
    }
}
