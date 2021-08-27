package com.xiao.boot.service;

import java.util.Date;
import java.util.List;

import com.xiao.boot.bean.po.Attendance;

public interface AttendanceService {
    /**
     *
     * @description: 用户打卡
     * @param: attendance
     * @return: Integer
     * @date: 2021/8/20
     */
    public Integer daKa(Attendance attendance);
    /**
     *
     * @description: 通过id查找打卡记录
     * @param: jobId
     * @return: List<Attendance>
     * @date: 2021/8/20
     */
    public List<Attendance> findDakaRecord(Integer jobId);
    /**
     *
     * @description: 通过部门id寻找打卡记录
     * @param: departmentId
     * @return: List
     * @date: 2021/8/20
     */


    public List<Attendance> findDepDakaRecord(String departmentId);
/**
 *
 * @description: 通过姓名寻找打卡记录
 * @param: departmentId name
 * @return: List
 * @date: 2021/8/20
 */

    public List<Attendance> findDakaRecordByName(String departmentId, String name);
 /**
  *
  * @description: 通过类型寻找打卡记录
  * @param: type
  * @return: List
  * @date: 2021/8/20
  */

    List<Attendance> findDakaRecordByType(Integer type);

    Integer findDakaRecordById(Integer jobId, Date nowDate);

    Integer updateAttendance(Attendance updateAttendance);
}
