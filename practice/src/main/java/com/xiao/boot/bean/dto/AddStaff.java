package com.xiao.boot.bean.dto;
import lombok.Data;

/**
 * @anthor :zyy
 * @description:
 * @Date 2021/8/12 10:18
 * @param:
 * @return :
 */
@Data
public class AddStaff {

    private Integer jobId;
    private String name;
    private String sex;
    private String mobile;
    private String post;
    private Integer role;
    private String password;
    private Integer status=1;
}
