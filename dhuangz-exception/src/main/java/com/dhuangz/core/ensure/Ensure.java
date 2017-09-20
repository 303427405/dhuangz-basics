package com.dhuangz.core.ensure;

import com.dhuangz.core.exceptions.EnsureParamObjectExtension;

import java.util.Collection;

/**
 *  异常处理
 */
public class Ensure {

    /**
     * 异常捕获类型 object
     * @param tObject
     * @return
     */
    public static EnsureParamObjectExtension that(Object tObject){
        return new EnsureParamObjectExtension(tObject);
    }


    /**
     * 异常捕获类型 string
     * @param tObject
     * @return
     */

}
