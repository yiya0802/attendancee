package com.hodo.practice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @anthor :zyy
 * @description: 公共返回类
 * @param: ：code msg object
 * @return :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class RestBean {
    private long code;
    private  String msg;
    private Object object;

    public  static RestBean success(String str)
    {
        return  new RestBean(RestBeanEnum.SUCCESS.getCode(),RestBeanEnum.SUCCESS.getMsg(), null);
    }
    public  static RestBean success(Object object)
    {
        return  new RestBean(RestBeanEnum.SUCCESS.getCode(), RestBeanEnum.SUCCESS.getMsg(), object);
    }
    public  static RestBean error(RestBeanEnum restBeanEnum)
    {
        return  new RestBean(restBeanEnum.getCode(), restBeanEnum.getMsg(), null);
    }
    public  static RestBean error(RestBeanEnum restBeanEnum,Object object)
    {
        return  new RestBean(restBeanEnum.getCode(), restBeanEnum.getMsg(), object);
    }



}
