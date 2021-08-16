package com.xiao.boot.bean.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 审核表(Checktable)实体类
 *
 * @author makejava
 * @since 2021-08-16 09:43:58
 */
@AllArgsConstructor
@NoArgsConstructor
public class Checktable implements Serializable {
    private static final long serialVersionUID = 320441511304797276L;

    private String name;

    private Integer id;

    private Integer type;

    private String opinion;

    private String status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
