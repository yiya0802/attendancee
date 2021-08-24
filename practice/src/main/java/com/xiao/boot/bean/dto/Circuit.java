package com.xiao.boot.bean.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    private  String name;
    private  Integer leavetype;
    String reason;
    Integer days;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    Date LeaveTime;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    Date BackTime;
    Double ammount;
}
