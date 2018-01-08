package com.apkcore.aopdemo.hook;

import android.text.TextUtils;

import com.apkcore.aopdemo.reflect.Reflect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @autor apkcore
 * @time 2018/1/8 16:36
 * @des Hook埋点
 */
@Aspect
public class HookAspect {
    private static final String POINTCUT_METHOD = "execution(@com.apkcore.aopdemo.hook.HookTrace * *(..))";
    private static final String POINTCUT_CONSTRUCTOR = "execution(@com.apkcore.aopdemo.hook.HookTrace *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodOnHook() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorOnHook() {
    }

    @Around("methodOnHook() || constructorOnHook()")
    public Object doHook(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HookTrace hookTrace = method.getAnnotation(HookTrace.class);

        String beforeMethod = hookTrace.beforeMethod();
        String afterMethod = hookTrace.afterMethod();
        if (!TextUtils.isEmpty(beforeMethod)) {
            //反射调用
            Class cls = joinPoint.getTarget().getClass();
            //getDeclaredMethod*()获取的是类自身声明的所有方法，包含public、protected和private方法。
            //getMethod*()获取的是类的所有共有方法，这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法。
            Method m = cls.getDeclaredMethod(beforeMethod);
            m.invoke(joinPoint.getTarget());
        }
        Object object = joinPoint.proceed();
        if (!TextUtils.isEmpty(afterMethod)) {
//            Class cls = joinPoint.getTarget().getClass();
//            Method m = cls.getDeclaredMethod(beforeMethod);
//            m.invoke(joinPoint.getTarget());
            Reflect.on(joinPoint.getTarget()).call(afterMethod);
        }
        return object;
    }
}
