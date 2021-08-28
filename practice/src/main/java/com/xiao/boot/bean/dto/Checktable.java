package com.xiao.boot.bean.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 审核表(Checktable)实体类
 *
 * @author makejava
 * @since 2021-08-16 09:43:58
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Checktable implements Serializable {
    private static final long serialVersionUID = 320441511304797276L;

    private String name;

    private String opinion;

    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }



}
