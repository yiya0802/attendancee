package com.hodo.practice.utils;

import com.hodo.practice.exception.ParamException;

/**
 * @anthor :zyy
 * @description: 断言方法判断
 * @param: flag msg
 * @return : void
 */

public class AssertUtil {
    public  static  void isTrue(Boolean flag,String msg)
    {
        if(flag)
        {
            throw new ParamException(msg);
        }
    }

}
