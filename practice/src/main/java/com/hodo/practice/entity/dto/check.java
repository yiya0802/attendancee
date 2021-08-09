package com.hodo.practice.entity.dto;

import lombok.Data;

/**
 * @anthor :zyy
 * @description: check表 列表字段：姓名、类型、意见、审核状态
 * @param:
 * @return:
 */
@Data
public class check {
    private String name;

    private Integer type;

    private String opinion;

    private String status;

}
