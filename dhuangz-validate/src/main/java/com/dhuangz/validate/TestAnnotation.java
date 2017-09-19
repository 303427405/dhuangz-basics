package com.dhuangz.validate;

import com.dhuangz.core.exceptions.DHZExceptionFactory;
import com.dhuangz.validate.factory.StringValidate;
import com.dhuangz.validate.pojo.vo.UserVO;

/**
 * Created by zyf on 2017/9/15.
 */
public class TestAnnotation {

    public static void main(String [] args){
        /**抛出异常 ， 未做异常处理*/
        UserVO userVO = new UserVO("aqq","ccqqqqqqqqqqqqqqqqqqqqqqqc");
        StringValidate.validateString(userVO);

    }
}
