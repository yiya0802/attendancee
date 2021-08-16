package com.xiao.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.boot.bean.dto.AddStaff;
import com.xiao.boot.bean.po.Staff;
import com.xiao.boot.mapper.StaffMapper;
import com.xiao.boot.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffMapper staffMapper;
    // 登录
    @Override
    public Staff login(Integer jobId, String password) {
        if (StringUtils.isEmpty(password) || null==jobId)
        {
            return null;
        }
        // 按条件查询，如果staff属性为空，则不按该列查询
        Staff staff=(Staff) staffMapper.selectById(jobId);
        return staff;
    }

    @Override
    public List<Staff> findAllStaffByDepartmentId(String departmentId) {
        Staff staff = new Staff();
        // 按条件查询，如果staff属性为空，则不按该列查询
        QueryWrapper<Staff> wrapper = new QueryWrapper<>(staff);
        return staffMapper.selectList(wrapper);
    }

    @Override
    public Staff findStaffById(Integer jobId) {
        return staffMapper.selectById(jobId);
    }

    @Override
    public Integer resetCode(Integer id) {
        Staff staff = staffMapper.selectById(id);
        staff.setPassword("123456");
        return staffMapper.updateById(staff);
    }

    @Override
    public Integer addStaff(AddStaff staff) {
        return staffMapper.insert(staff);
    }

    @Override
    public List<Staff> findAllStaff() {
        return staffMapper.selectList(null);
    }

    @Override
    /**
     * 员工的姓名，电话、密码可以修改
     */
    public Integer updateStaff(Staff staff) {
        Staff staff1=staffMapper.selectById(staff.getJobId());
        QueryWrapper<Staff> updateWrapper=new QueryWrapper<>();
        updateWrapper.eq("name",staff.getName()).eq("mobile",staff.getMobile()).eq("password",staff.getPassword());
        return staffMapper.update(staff1,updateWrapper);
    }

    @Override
    public Integer deleteStaffById(Integer jobId) {
        return staffMapper.deleteById(jobId);
    }

    @Override
    public Staff findManagerByDep(String departmentId) {
        Staff staff = new Staff();
        QueryWrapper<Staff> wrapper = new QueryWrapper<>(staff);
        return staffMapper.selectOne(wrapper);
    }

    @Override
    public List<Staff> findStaffByDep(String departmentId) {
        Staff staff = new Staff();

        staff.setRole(3);
        QueryWrapper<Staff> wrapper = new QueryWrapper<>(staff);
        return staffMapper.selectList(wrapper);
    }

    @Override
    public String findDepartIdByName(String name) {
        QueryWrapper<Staff>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",name);
        Staff staff=staffMapper.selectOne(queryWrapper);
        return staff.getDepartmentId();
    }

    @Override
    public Staff findStaffByName(String name) {
        QueryWrapper<Staff>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return staffMapper.selectOne(queryWrapper);
    }


}
