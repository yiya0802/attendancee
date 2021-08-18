package com.xiao.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiao.boot.bean.po.Department;
import com.xiao.boot.bean.po.Salary;
import com.xiao.boot.bean.po.Staff;

public interface SalaryMapper extends BaseMapper<Salary> {
    Integer insert(Department department1);
}
