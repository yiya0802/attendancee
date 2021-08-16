package com.xiao.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.boot.bean.dto.AddStaff;
import com.xiao.boot.bean.po.Staff;

public interface StaffMapper extends BaseMapper<Staff> {


    Staff updateById(Integer jobId);

    Integer insert(AddStaff staff);
}
