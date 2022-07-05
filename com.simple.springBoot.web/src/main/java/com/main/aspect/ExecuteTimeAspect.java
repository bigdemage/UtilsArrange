package com.main.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecuteTimeAspect {

    @Pointcut("@annotation(com.main.aspect.ExecuteTime)")
    public void time(){}



    @Around(value = "time() && @annotation(executeTime)")
    public Object excuteTime(ProceedingJoinPoint proceedingJoinPoint, ExecuteTime executeTime) throws Throwable {
        long start =System.currentTimeMillis();
        Object obj =proceedingJoinPoint.proceed();
        long end =System.currentTimeMillis();
        System.out.println("执行时间："+(end-start)+"ms");
        return obj;
    }



}
