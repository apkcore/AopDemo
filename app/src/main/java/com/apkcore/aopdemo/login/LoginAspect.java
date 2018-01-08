package com.apkcore.aopdemo.login;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @autor apkcore
 * @time 2018/1/8 16:22
 * @des 拦截登录
 */
@Aspect
public class LoginAspect {

    @Pointcut("execution(@com.apkcore.aopdemo.login.LoginTrace * *(..))")
    public void onLoginMethod(){
    }

    public Object doLoginMethod(ProceedingJoinPoint joinPoint)throws  Throwable{
//        if (!已经登录){
//            跳转登录页面
//            return null;
//        }

        return joinPoint.proceed();
    }
}
