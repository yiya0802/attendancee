package com.xiao.boot.bean.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 员工薪资(TSalary)实体类
 *
 * @author makejava
 * @since 2021-08-03 16:46:39
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Salary implements Serializable {
    private static final long serialVersionUID = -52673016308287489L;
    @TableId(value = "id",type= IdType.AUTO)

    private Integer id;

    private Integer userid;

    private String name;
    /**
     * 基本工资
     */
    private Integer bascimoney;
    /**
     * 迟到扣工资
     */
    private Integer lateMoney;
    /**
     * 旷工扣的钱 可以为空
     */
    private Integer absenteeismMoney;
    /**
     * 早退扣工资
     */
    private Integer earlyLeave;
    /**
     * 离开的总时间
     */
    private Integer month;
    /**
     * 奖金
     */
    private Integer bonus;
    /**
     * 岗位
     */
    private String post;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBascimoney() {
        return bascimoney;
    }

    public void setBascimoney(Integer bascimoney) {
        this.bascimoney = bascimoney;
    }

    public Integer getLateMoney() {
        return lateMoney;
    }

    public void setLateMoney(Integer lateMoney) {
        this.lateMoney = lateMoney;
    }

    public Integer getAbsenteeismMoney() {
        return absenteeismMoney;
    }

    public void setAbsenteeismMoney(Integer absenteeismMoney) {
        this.absenteeismMoney = absenteeismMoney;
    }

    public Integer getEarlyLeave() {
        return earlyLeave;
    }

    public void setEarlyLeave(Integer earlyLeave) {
        this.earlyLeave = earlyLeave;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

}
