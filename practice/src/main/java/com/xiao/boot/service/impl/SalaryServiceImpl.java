package com.xiao.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.boot.bean.po.Department;
import com.xiao.boot.bean.po.Salary;
import com.xiao.boot.mapper.DepartmentMapper;
import com.xiao.boot.mapper.SalaryMapper;
import com.xiao.boot.service.SalaryService;

/**
 * @anthor :zyy
 * @description:
 * @Date 2021/8/12 16:02
 * @param:
 * @return :
 */
@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    SalaryMapper salaryMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Salary> findSalaryByName(String name) {
        QueryWrapper<Salary>query=new QueryWrapper<>();
        query.eq("Name",name);
        List<Salary> salary=salaryMapper.selectList(query);
        return salary;
    }

    @Override
    public int addPostSalary(Department department1) {
        return  departmentMapper.insert(department1);
    }

    @Override
    public int deletePostSalary(String post) {
        QueryWrapper<Department> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("post",post);
        return departmentMapper.delete(queryWrapper);
    }

    @Override
    public Salary findSalaryById(Integer jobId) {
        return salaryMapper.selectById(jobId);
    }

    @Override
    public Integer updateSalary(Salary salary) {
        return salaryMapper.updateById(salary);
    }
}
