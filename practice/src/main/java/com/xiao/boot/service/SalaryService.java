package com.xiao.boot.service;

import java.util.List;

import com.xiao.boot.bean.dto.AddStaff;
import com.xiao.boot.bean.po.Department;
import com.xiao.boot.bean.po.R;
import com.xiao.boot.bean.po.Salary;

public interface SalaryService {
    List<Salary> findSalaryByName(String name);

    int addPostSalary(Department department1);

    int deletePostSalary(String post);

    Salary findSalaryById(Integer jobId);

    Integer updateSalary(Salary salary);

    Integer addStaffSalary(AddStaff staff, Integer salary);

    R findPageSalary(Long current, Long size);
}
