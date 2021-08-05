package com.hodo.practice.entity.po;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 缺勤表(TAttendance)实体类
 *
 * @author makejava
 * @since 2021-08-03 16:46:37
 */
@AllArgsConstructor
@NoArgsConstructor
public class TAttendance implements Serializable {
    private static final long serialVersionUID = -22793331338775663L;
    /**
     * 缺勤编号
     */
    private Integer attennum;
    /**
     * 正常、迟到、早退
     */
    private Integer state;
    /**
     * 姓名
     */
    private String name;
    /**
     * 部门
     */
    private String departmentid;
    /**
     * 缺勤类型（离职，请假，外出）
     */
    private Integer attendancetype;
    /**
     * 员工id
     */
    private Integer id;
    /**
     * 打卡时间
     */
    private String attendanceTime;


    public Integer getAttennum() {
        return attennum;
    }

    public void setAttennum(Integer attennum) {
        this.attennum = attennum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public Integer getAttendancetype() {
        return attendancetype;
    }

    public void setAttendancetype(Integer attendancetype) {
        this.attendancetype = attendancetype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(String attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

}
