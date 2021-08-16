package com.xiao.boot.bean.dto;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.AllArgsConstructor;

/**
 * 请假表(Leavetable)表实体类
 *
 * @author makejava
 * @since 2021-08-16 09:06:54
 */
@SuppressWarnings("serial")
@AllArgsConstructor
public class Leavetable extends Model<Leavetable> {
    //请假号
    private Integer id;
    //请假类型
    private Integer leavetype;
    //用户id
    private Integer num;
    //请假姓名
    private String name;

    private String reason;

    private Date leavetime;

    private Date backtime;

    private Integer days;
    //金额（报销）
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

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
