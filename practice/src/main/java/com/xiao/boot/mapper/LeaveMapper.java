package com.xiao.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.boot.bean.dto.Circuit;
import com.xiao.boot.bean.dto.Leavetable;

public interface LeaveMapper extends BaseMapper<Leavetable> {
    int insert(Circuit c);
}
