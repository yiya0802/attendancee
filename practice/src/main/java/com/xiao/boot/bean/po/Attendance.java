package com.xiao.boot.bean.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Attendance implements Serializable {
    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;
    private String attendanceTime;
    private Integer jobId;
    private String name;
    private String sex;
    private String birthday;
    private String departmentId;
    private Integer attendanceType;
}
