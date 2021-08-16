package com.xiao.boot.service;

import java.util.List;

import com.xiao.boot.bean.po.Department;
import com.xiao.boot.bean.po.Salary;

public interface SalaryService {
    List<Salary> findSalaryByName(String name);

    int addPostSalary(Department department1);

    int deletePostSalary(String post);
}
