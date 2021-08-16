package com.xiao.boot.service;

import java.util.List;

import com.xiao.boot.bean.po.Attendance;

public interface AttendanceService {
    public Integer daKa(Attendance attendance);

    public List<Attendance> findDakaRecord(Integer jobId);

    public List<Attendance> findDepDakaRecord(String departmentId);

    public List<Attendance> findDakaRecordByName(String departmentId, String name);
}
