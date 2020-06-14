package com.crud.companyemployee.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Pointcut("within(@org.springframework.stereotype.Service *)"
            + " || within(@org.springframework.stereotype.Component *)"
            + " || within(@org.springframework.stereotype.RestControllerAdvice *)"
            + " || within(@org.springframework.stereotype.RestController *)")
    public void springBeanPointcut() {
        //Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
        final Signature signature = joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        final String methodName = signature.getName();
        log.info("Inside {} ->{}() method.", className, methodName);
        Object result = joinPoint.proceed();
        log.info("Outside {} ->{}() method.", className, methodName);
        return result;
    }
}
