package com.dhuangz.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义string类型校验
 * Created by zyf on 2017/9/15.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringAnnotation {

    /**错误码*/
    String code() default "";

    /**最小长度*/
    int min() default 0;

    /**最大长度*/
    int max() default 0;

    /**正则校验*/
    String regex() default "";

}
