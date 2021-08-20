package com.xiao.boot.bean.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
//@TableName("staff") // 默认和类名一样
public class Staff implements Serializable {
    @TableId(value = "job_id",type=IdType.AUTO)
    private Integer jobId;
    private String name;
    private String sex;
    private String birthday;
    private String mobile;
    private String post;
    private Integer role;
    private String password;
    private String departmentId;
    Integer status;

}
