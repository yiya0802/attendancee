package com.hodo.practice.utils;

/**
 * @anthor :zyy
 * @description: 判断字符串是否为空
 * @param:
 * @return :
 */

public class StringUtils {
    public  static boolean isEmpity(String str)
    {
        if (str==null||"".equals(str.trim()))
        {
            return true;
        }
        else
        {
            return  false;
        }

    }
    public  static boolean isNotEmpity(String str)
    {
        if (str==null||"".equals(str.trim()))
        {
            return false;
        }
        else
        {
            return  true;
        }

    }


}
