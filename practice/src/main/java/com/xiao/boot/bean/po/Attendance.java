package com.xiao.boot.bean.po;

import java.io.Serializable;
import java.util.Date;

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

    private Integer attendanceStatus;

    private Integer attendanceType;
    private Date attendanceDate;
}
