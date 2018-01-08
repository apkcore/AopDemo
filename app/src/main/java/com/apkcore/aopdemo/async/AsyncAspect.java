package com.apkcore.aopdemo.async;

import android.os.Looper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @autor apkcore
 * @time 2018/1/8 15:02
 * @des 在线程中执行
 */
@Aspect
public class AsyncAspect {

    @Pointcut("execution(@com.apkcore.aopdemo.async.AsyncTrace * *(..))")
    public void onAsyncMethod(){
    }

    @Around("onAsyncMethod()")
    public Object doAsyncMethod(final ProceedingJoinPoint joinPoint) throws  Throwable{
        final Object[] object = {null};
        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> e) throws Exception {
                Looper.prepare();
                try {
                    object[0] =  joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                Looper.loop();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        return object[0];
    }
}
