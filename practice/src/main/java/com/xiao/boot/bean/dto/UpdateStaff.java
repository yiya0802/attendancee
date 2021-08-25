package com.xiao.boot.bean.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @anthor :zyy
 * @description: 员工的姓名、地址、电话、密码可以修改
 * @param:
 * @return:
 */
@Data
@Getter
@Setter
public class UpdateStaff {
    Integer id;
    String address;
    String name;
    String mobile;
    String password;
}
