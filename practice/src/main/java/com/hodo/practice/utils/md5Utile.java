package com.hodo.practice.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @anthor :zyy
 * @description: md5两次加密
 * @param:
 * @return :
 */

public class md5Utile {
    public static String md5(String src)
    {
        return DigestUtils.md5Hex(src);
    }
    private static final String salt="1a2b3c4d";

    private static String inputPassToFromPass(String inputPass)
    {
        String str=salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return  md5(str);
    }
    private static String fromPassToDBPass(String formPass,String salt)
    {
        String str=salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return  md5(str);
    }
    public static String inputPassToDBPass(String inputPass,String salt)
    {
        String frompass=inputPassToFromPass(inputPass);
        String dbpass=fromPassToDBPass(frompass,salt);
        return  dbpass;
    }

    /**
     *
      * public static void main(String[] args) {
     *         System.out.println(inputPassToFromPass("123456"));
     *         System.out.println(fromPassToDBPass("ce21b747de5af71ab5c2e20ff0a60eea","1a2b3c4d"));
     *         System.out.println(inputPassToDBPass("123456","1a2b3c4d"));
     */






    }

