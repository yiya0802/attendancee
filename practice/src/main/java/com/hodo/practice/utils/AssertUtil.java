package com.hodo.practice.utils;

import com.hodo.practice.entity.R;

/**
 * @anthor :zyy
 * @description: 断言方法判断
 * @param: flag msg
 * @return : void
 */

public class AssertUtil {
    public  static R<Object> isTrue(Boolean flag, String msg)
    {
        if(!flag)
        {
            return R.failed(msg);
        }
        return R.ok(msg);
    }

}
