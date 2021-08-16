package com.xiao.boot.bean.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department implements Serializable {
    @TableId(value = "departmentId")
    private String departmentId;
    private String name;
    private String post;
    private  Integer salary;

}
