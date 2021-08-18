package com.xiao.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.boot.bean.po.Attendance;
import com.xiao.boot.mapper.AttendanceMapper;
import com.xiao.boot.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {
@Autowired
    AttendanceMapper attendanceMapper;
    @Override
    public Integer daKa(Attendance attendance) {
        return attendanceMapper.insert(attendance);
    }

    @Override
    public List<Attendance> findDakaRecord(Integer jobId) {
        QueryWrapper<Attendance> wrapper = new QueryWrapper<>();
        wrapper.eq("id",jobId);
        // condition:true 使用该条件 isAsc 升序
        wrapper.orderBy(true,false,"attendance_time");
        return attendanceMapper.selectList(wrapper);
    }

    @Override
    public List<Attendance> findDepDakaRecord(String departmentId) {
        QueryWrapper<Attendance> wrapper = new QueryWrapper<>();
        wrapper.eq("department_id",departmentId);
        // condition:true 使用该条件 isAsc 升序
        wrapper.orderBy(true,false,"attendance_time");
        return attendanceMapper.selectList(wrapper);
    }

    @Override
    public List<Attendance> findDakaRecordByName(String departmentId, String name) {
        QueryWrapper<Attendance> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name).eq("department_id",departmentId);
        return attendanceMapper.selectList(wrapper);
    }

    @Override
    public List<Attendance> findDakaRecordByType(Integer type) {
        QueryWrapper<Attendance> querywrapper=new QueryWrapper<>();
        querywrapper.eq("attendance_type",type);
        return attendanceMapper.selectList(querywrapper);
    }
}
