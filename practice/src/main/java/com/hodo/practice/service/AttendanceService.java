package com.hodo.practice.service;

import com.hodo.practice.entity.po.TAttendance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * 打卡接口
 */
public interface AttendanceService {

    public String getTime();

    public Object getDepartIdByName(String name);

    int daka(TAttendance tAttendance);

    List<TAttendance> findRecords(String name);
}
