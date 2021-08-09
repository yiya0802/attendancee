package com.hodo.practice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private  Integer type;
    String reason;
    Integer days;
    Date LeaveTime;
    Date BackTime;
}
