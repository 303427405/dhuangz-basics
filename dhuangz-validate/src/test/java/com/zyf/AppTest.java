package com.zyf;

import com.dhuangz.core.ensure.Ensure;
import com.dhuangz.validate.factory.StringValidate;
import com.dhuangz.validate.pojo.vo.UserVO;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    public static void main(String [] args){
        /**字段校验
        UserVO userVO = new UserVO("sdfsdfsdfsfsd","sdfsdfsdfsf");
        StringValidate.validateString(userVO);*/
        /**对象校验*/
        UserVO userVO1 = null;
        Ensure.that(userVO1).isNotNull("DHUANGZ_VALIDATE_ERROR_00002");

    }
}
