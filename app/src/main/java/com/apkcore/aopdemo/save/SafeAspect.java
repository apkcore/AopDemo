package com.apkcore.aopdemo.save;

import android.annotation.SuppressLint;
import android.util.Log;

import com.apkcore.aopdemo.BehaviorTrace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @autor apkcore
 * @time 2018/1/8 11:11
 * @des 空指针拦截
 */

@Aspect
public class SafeAspect {
    private static final String TAG = "AOP";

    @Pointcut("execution(@com.apkcore.aopdemo.save.SafeTrace * *(..))")
    public void onSafe() {
    }

    @Around("onSafe()")
    public Object doSafeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return safeMethod(joinPoint);
    }

//    @Pointcut("@within(com.apkcore.aopdemo.save.SafeTrace)||@annotation(com.apkcore.aopdemo.save.SafeTrace)")
//    public void onSafe() {
//
//    }
//
//    @Around("execution(!synthetic * *(..)) && onSafe()")
//    public Object doSafeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        return safeMethod(joinPoint);
//}

    private Object safeMethod(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            StringWriter stringWriter = new StringWriter();
            throwable.printStackTrace(new PrintWriter(stringWriter));
            Log.d(TAG, "safeMethod: " + stringWriter.toString());
        }
        return result;
    }

}
