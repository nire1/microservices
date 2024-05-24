package com.tsaidenis.mainservice.aspect;


import lombok.extern.log4j.Log4j2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

@Log4j2
@Component
@Aspect
public class Log {

    @Around("@annotation(com.tsaidenis.mainservice.aspect.LogA)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Метод "+ joinPoint.getSignature().getName());
        Object proceed = joinPoint.proceed();
        log.info("метод завершен");
        return proceed;
    }
}
