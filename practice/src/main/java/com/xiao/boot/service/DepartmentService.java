package com.xiao.boot.service;

import java.util.List;

import com.xiao.boot.bean.po.Department;

public interface DepartmentService {

    public Integer addDepartment(Department department);

    public List<Department> findAllDepartment();

    public Department findDepartmentById(String departmentId);

    public Integer updateDepartment(Department department);

    public Integer deleteDepartmentById(String departmentId);

    public Department findDepartmentByName(String name);

    List<Department> findSalaryByPost(String post);
}
