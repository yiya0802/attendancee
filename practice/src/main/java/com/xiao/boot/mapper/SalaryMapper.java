package com.xiao.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiao.boot.bean.po.Department;
import com.xiao.boot.bean.po.Salary;

public interface SalaryMapper extends BaseMapper<Salary> {
    Integer insert(Department department1);
}
