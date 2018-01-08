package com.apkcore.aopdemo.async;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @autor apkcore
 * @time 2018/1/8 15:01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface AsyncTrace {
}
