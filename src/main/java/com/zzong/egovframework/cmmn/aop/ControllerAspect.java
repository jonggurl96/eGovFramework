package com.zzong.egovframework.cmmn.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

@Slf4j
public class ControllerAspect {
    
    public void beforeTarget(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        log.debug("{}.{}() execution...", clazz, methodName);
    }
    
    public Object aroundTarget(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        log.debug("    Parameters: {}", thisJoinPoint.getArgs());
        
        long beforeTimeMillis = System.currentTimeMillis();
        Object retVal = thisJoinPoint.proceed();
        long proceedSessionMillis = System.currentTimeMillis() - beforeTimeMillis;
        
        log.debug("Process took a time {}ms", proceedSessionMillis);
        return retVal;
    }
    
    public void afterTarget(JoinPoint joinPoint) {
        log.debug("After {}", joinPoint.getSignature().getName());
    }
    
    public void afterReturningTarget(JoinPoint joinPoint, Object retVal) {
        log.debug("{} returns {}",joinPoint.getSignature().getName() , retVal);
    }
    
    public void afterThrowingTarget(JoinPoint joinPoint, Throwable exception) {
        log.error("Class {} throws exception with message {}",
                joinPoint.getTarget().getClass().getSimpleName(), exception.getMessage());
    }
}
