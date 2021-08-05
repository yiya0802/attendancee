package com.hodo.practice.entity.po;

import java.io.Serializable;

/**
 * 部门岗位表(TDepartment)实体类
 *
 * @author makejava
 * @since 2021-08-03 16:46:38
 */
public class TDepartment implements Serializable {
    private static final long serialVersionUID = -15101714971439603L;

    private Integer departmentid;

    private String name;


    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
