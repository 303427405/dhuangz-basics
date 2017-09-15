package com.dhuangz.validate;

import com.dhuangz.validate.factory.StringValidate;
import com.dhuangz.validate.pojo.vo.UserVO;

/**
 * Created by zyf on 2017/9/15.
 */
public class TestAnnotation {

    public static void main(String [] args){
        UserVO userVO = new UserVO("asd","ccc");
        StringValidate.validateString(userVO);

    }
}
