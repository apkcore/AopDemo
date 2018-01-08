package com.apkcore.aopdemo.save;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @autor apkcore
 * @time 2018/1/8 11:12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface SafeTrace {
}
