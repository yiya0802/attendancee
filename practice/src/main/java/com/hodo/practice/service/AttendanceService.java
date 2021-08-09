package com.hodo.practice.service;

import com.hodo.practice.entity.po.TAttendance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * 打卡接口
 */
public interface AttendanceService {
    /**
     * 获取打卡的时间
     *
     * @return date
     */
    public String getTime();
    /**
     * 通过名字查询departid
     *
     * @param name
     * @return jobid
     */
    public Object getDepartIdByName(String name);
    /**
     * 打卡
     *
     * @param tAttendance
     * @return int
     */
    int daka(TAttendance tAttendance);
    /**
     * 通过名字找到打卡记录
     *
     * @param name
     * @return list
     */
    List<TAttendance> findRecords(String name);
    /**
     * 通过id找到打卡记录
     * @param id
     * @return list
     */

    List<TAttendance> findRecordsByID(Integer id);
}
