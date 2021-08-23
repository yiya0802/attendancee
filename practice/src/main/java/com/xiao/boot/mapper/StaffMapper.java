package com.xiao.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.boot.bean.po.Staff;
import org.apache.ibatis.annotations.Param;

public interface StaffMapper extends BaseMapper<Staff> {

    Integer insertAddStaff(@Param("staff2") Staff staff2);
}
