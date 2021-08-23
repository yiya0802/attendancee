package com.xiao.boot.bean.dto;

import com.xiao.boot.bean.po.Staff;
import lombok.Data;

/**
 * @anthor :zyy
 * @description:
 * @Date 2021/8/23 9:46
 * @param:
 * @return :
 */
@Data
public class PageStaff extends Staff {
    private Integer salary;
}
