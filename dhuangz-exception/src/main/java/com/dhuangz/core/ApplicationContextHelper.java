package com.dhuangz.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by zyf on 2017-9-19.
 */
public class ApplicationContextHelper implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        context = applicationContext;
    }

    public static ApplicationContext getContext(){
        return context;
    }

}
