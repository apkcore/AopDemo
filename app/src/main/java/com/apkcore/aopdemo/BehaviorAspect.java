package com.apkcore.aopdemo;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by apkcore on 2018/1/8. 02:23
 * mail:apkcore@gmail.com
 */
@Aspect
public class BehaviorAspect {
    private static final String TAG = "AOP";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 切点
     */
    @Pointcut("execution(@com.apkcore.aopdemo.BehaviorTrace * *(..))")
    public void annoBehavior() {

    }

    @Around("annoBehavior()")
    public Object dealPoint(ProceedingJoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        BehaviorTrace trace = methodSignature.getMethod().getAnnotation(BehaviorTrace.class);
        String content = trace.value();
        int type = trace.type();
        long begin = System.currentTimeMillis();
        Log.d(TAG, content + " dealPoint begin: " + simpleDateFormat.format(new Date()));
        Object obj = point.getThis();
        Context context = getContext(obj);
        Log.d(TAG, context+" , "+point.getArgs());
        Object object = point.proceed();
        Log.d(TAG, content + " dealPoint end: " + (System.currentTimeMillis() - begin) + "ms");
        return object;
    }

    /**
     * 通过对象获取上下文
     *
     * @param object
     * @return
     */
    private Context getContext(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof android.app.Fragment) {
            android.app.Fragment fragment = (android.app.Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof View) {
            View view = (View) object;
            return view.getContext();
        }
        return null;
    }

}
