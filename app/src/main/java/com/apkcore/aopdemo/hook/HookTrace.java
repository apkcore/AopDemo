package com.apkcore.aopdemo.hook;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @autor apkcore
 * @time 2018/1/8 16:33
 */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface HookTrace {
    String beforeMethod() default "";

    String afterMethod() default "";
}
