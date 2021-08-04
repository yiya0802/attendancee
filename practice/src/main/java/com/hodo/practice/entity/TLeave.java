package com.hodo.practice.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 请假表(TLeave)实体类
 *
 * @author makejava
 * @since 2021-08-03 16:46:38
 */
public class TLeave implements Serializable {
    private static final long serialVersionUID = -43356433397152031L;
    /**
     * 请假号
     */
    private Integer id;
    /**
     * 请假类型
     */
    private Integer leavetype;
    /**
     * 用户id
     */
    private Integer num;
    /**
     * 请假姓名
     */
    private String name;

    private String reason;

    private Date leavetime;

    private Date backtime;

    private Integer days;
    /**
     * 金额（报销）
     */
    private Double ammount;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(Integer leavetype) {
        this.leavetype = leavetype;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(Date leavetime) {
        this.leavetime = leavetime;
    }

    public Date getBacktime() {
        return backtime;
    }

    public void setBacktime(Date backtime) {
        this.backtime = backtime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getAmmount() {
        return ammount;
    }

    public void setAmmount(Double ammount) {
        this.ammount = ammount;
    }

}
