package com.hodo.practice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @anthor :zyy
 * @description:
 * @param:
 * @return :
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParamException extends RuntimeException {
    private Integer code;
    private String msg;
    public ParamException(String msg) {

    }
}
