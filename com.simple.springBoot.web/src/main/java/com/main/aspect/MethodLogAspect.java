package com.main.aspect;


import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Component
public class MethodLogAspect {

    @Pointcut("@annotation(com.main.aspect.MethodLog)")
    public void logThis(){};


    @Around(value= " logThis() && @annotation(methodLog) ")
    public Object logAround(ProceedingJoinPoint joinPoint,MethodLog methodLog) throws Throwable {
        Object[] args=joinPoint.getArgs();
        System.out.println(methodLog.description()+"方法入参:"+ JSONObject.toJSONString(args));
        long start =System.currentTimeMillis();
        Object reObj=joinPoint.proceed();
        long end =System.currentTimeMillis();
        System.out.println("方法执行时间:"+(end-start)+"ms");
        getControllerMethodDescription(joinPoint);
        return reObj;
    }

    private static String getControllerMethodDescription(ProceedingJoinPoint joinPoint)  throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(MethodLog.class).description();
                    break;
                }
            }
        }
        return description;
    }
}
