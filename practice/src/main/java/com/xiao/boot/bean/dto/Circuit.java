package com.xiao.boot.bean.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @anthor :zyy
 * @description: 事物表
 * @param:
 * @return:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Circuit {
    private  String name ;
    private  Integer leavetype;
    String reason;
    Integer days;
    Date LeaveTime;
    Date BackTime;
    Double ammount;
}
