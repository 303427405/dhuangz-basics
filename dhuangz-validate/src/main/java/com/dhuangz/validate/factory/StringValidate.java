package com.dhuangz.validate.factory;

import com.dhuangz.core.exceptions.DHZExceptionFactory;
import com.dhuangz.validate.RegexValidator;
import com.dhuangz.validate.annotation.StringAnnotation;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * string校验
 * Created by zyf on 2017/9/15.
 */
public class StringValidate {

    /**校验活动配置*/
    public static void validateString( Object object ){
        Class cls = object.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                StringAnnotation annotation = field.getAnnotation(StringAnnotation.class);
                if(annotation == null){
                    /**参与签名的属性需要添加@XmlElement*/
                    continue;
                }
                field.setAccessible(true);
                Object value = field.get(object);

                if(annotation.max() > 0 && value.toString().length() > annotation.max()){
                    throw DHZExceptionFactory.create(annotation.code());
                }

                /**正则校验*/
                if(StringUtils.isNotBlank(annotation.regex())){
                    boolean isTrue = RegexValidator.find(value.toString(),annotation.regex());
                }
            } catch (IllegalAccessException e) {
                System.out.print(e);
            }
        }
    }
}
