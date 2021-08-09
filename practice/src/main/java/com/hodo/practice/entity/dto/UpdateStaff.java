package com.hodo.practice.entity.dto;

import lombok.Data;

/**
 * @anthor :zyy
 * @description:姓名、地址、电话、密码可以修改
 * @param:
 * @return:
 */
@Data
public class UpdateStaff {
    String name;
    String address;
    String mobile;
    String password;
}
