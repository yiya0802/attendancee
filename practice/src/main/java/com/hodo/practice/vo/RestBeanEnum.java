package com.hodo.practice.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
/**
 * 公共返回枚举类类型
 *
 */
public enum RestBeanEnum {
    SUCCESS(200,"success"),
    ERROR(500,"ERROR"),
    LOGINSUCCESS(100,"LOGIN SUCCESS"),
    LOGINERROR(1000,"LOGIN ERROR");




    private final Integer code;
    private final String  msg;

}
