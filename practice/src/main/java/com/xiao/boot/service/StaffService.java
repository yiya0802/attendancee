package com.xiao.boot.service;

import java.util.List;

import com.xiao.boot.bean.dto.AddStaff;
import com.xiao.boot.bean.po.R;
import com.xiao.boot.bean.po.Staff;

public interface StaffService {
    public Staff login(Integer jobId, String password);

    // 按部门查询所有员工
    public List<Staff> findAllStaffByDepartmentId(String departmentId);

    // 按工号查询员工
    Staff findStaffById(Integer jobId);

    // 密码重置
    public Integer resetCode(Integer id);

    // 添加员工
    public Integer addStaff(AddStaff staff);

    // 查找所有员工
    public List<Staff> findAllStaff();

    // 修改员工信息
    public Integer updateStaff(Staff staff);

    // 删除员工
    public Integer deleteStaffById(Integer jobId);

    // 安装部门查找部门经理是否存在
    public Staff findManagerByDep(String departmentId);

    public List<Staff> findStaffByDep(String departmentId);

    String findDepartIdByName(String name);

    Staff findStaffByName(String name);

    List<Integer> findAllStaffID();

    List<Staff> findStaffByStatus(Integer status);

     R findPageStaff();

    List<Staff> findStaffListByName(String name);

    R findPageStaff(Long current, Long size);

    R findPageStaffByStatus(Integer status, Long current, Long size);

    R findPageStaffByName(String name, Long current, Long size);

    Integer updateStaffStatus(Staff staff);

    R editStaff(Staff staff1, Integer salary);

    Integer updateStaff2(Staff staff);
}
