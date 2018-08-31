package com.main.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.main.aspect.Log)")
    public void description(){}


    /**
      java.lang.Object[] getArgs()：获取连接点方法运行时的入参列表；
      Signature getSignature() ：获取连接点的方法签名对象；
      java.lang.Object getTarget() ：获取连接点所在的目标对象；
      java.lang.Object getThis() ：获取代理对象本身；
     2)ProceedingJoinPoint
     ProceedingJoinPoint继承JoinPoint子接口，它新增了两个用于执行连接点方法的方法：
      java.lang.Object proceed() throws java.lang.Throwable：通过反射执行目标对象的连接点处的方法；
      java.lang.Object proceed(java.lang.Object[] args) throws java.lang.Throwable：通过反射执行目标对象连接点处的方法，不过使用新的入参替换原来的入参。
     * @param joinPoint
     * @param log
     */

    @Before("description()&&@annotation(log)")
    public void doBefore(JoinPoint joinPoint,Log log){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =servletRequestAttributes.getRequest();



        System.out.println("--------------------------");
        System.out.println(joinPoint.getArgs());
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getTarget());
        System.out.println(joinPoint.getThis());
        System.out.println("--------------------------");
    }

    @AfterReturning(returning = "obj",pointcut = "description() && @annotation(log)")
    public void doAfter(JoinPoint joinPoint,Object obj,Log log){

        System.out.println("结束后："+obj);

    }
}
