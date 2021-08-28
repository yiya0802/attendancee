package com.xiao.boot.service.impl;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiao.boot.bean.dto.Leavetable;
import com.xiao.boot.bean.dto.PageStaff;
import com.xiao.boot.bean.po.R;
import com.xiao.boot.bean.po.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.boot.bean.po.Attendance;
import com.xiao.boot.mapper.AttendanceMapper;
import com.xiao.boot.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService
{
    @Autowired
    AttendanceMapper attendanceMapper;
    private long currents=1;

    @Override
    /**
     *
     * @description: 用户打卡
     * @param: attendance
     * @return: Integer
     * @date: 2021/8/20
     */
    public Integer daKa(Attendance attendance)
    {
        return attendanceMapper.insert(attendance);
    }
    
    /**
     *
     * @description: 通过id查找打卡记录
     * @param: jobId
     * @return: List<Attendance>
     * @date: 2021/8/20
     */
    @Override
    public List<Attendance> findDakaRecord(Integer jobId)
    {
        QueryWrapper<Attendance> wrapper = new QueryWrapper<>();
        wrapper.eq("id", jobId);
        // condition:true 使用该条件 isAsc 升序
        wrapper.orderBy(true, false, "attendance_time");
        return attendanceMapper.selectList(wrapper);
    }
    
    /**
     *
     * @description: 通过姓名寻找打卡记录
     * @param: departmentId name
     * @return: List
     * @date: 2021/8/20
     */
    @Override
    public List<Attendance> findDepDakaRecord(String departmentId)
    {
        QueryWrapper<Attendance> wrapper = new QueryWrapper<>();
        wrapper.eq("department_id", departmentId);
        // condition:true 使用该条件 isAsc 升序
        wrapper.orderBy(true, false, "attendance_time");
        return attendanceMapper.selectList(wrapper);
    }
    
    /**
     *
     * @description: 通过姓名寻找打卡记录
     * @param: departmentId name
     * @return: List
     * @date: 2021/8/20
     */
    @Override
    public List<Attendance> findDakaRecordByName(String departmentId, String name)
    {
        QueryWrapper<Attendance> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name).eq("department_id", departmentId);
        return attendanceMapper.selectList(wrapper);
    }
    
    /**
     *
     * @description: 通过类型寻找打卡记录
     * @param: type
     * @return: List
     * @date: 2021/8/20
     */
    @Override
    public List<Attendance> findDakaRecordByType(Integer type)
    {
        QueryWrapper<Attendance> querywrapper = new QueryWrapper<>();
        querywrapper.eq("attendance_type", type);
        return attendanceMapper.selectList(querywrapper);
    }

    @Override
    /**
     *
     * @description: 通过id找到今天的早上的打卡记录
     * @param: [jobId]
     * @return: com.xiao.boot.bean.po.Attendance
     * @date: 2021/8/26
     */

    public Integer findDakaRecordById(Integer jobId, Date nowDate) {
        QueryWrapper<Attendance>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("job_id",jobId).eq("attendance_date",nowDate);
        return attendanceMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer updateAttendance(Attendance updateAttendance) {

        return attendanceMapper.updateById(updateAttendance);
    }

    @Override
    public List<Attendance> findDakaRecordByIdAndDate(Integer jobId, Date nowDate) {
        QueryWrapper<Attendance>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("job_id",jobId).eq("attendance_date",nowDate);
        return attendanceMapper.selectList(queryWrapper);
    }

    @Override
    public R findPageDakaRecords(Long current, Long size) {
        if (current==null || size==null)
        {
            return R.failed("current 和 size不能为空");
        }
        List<Attendance> list=attendanceMapper.selectList(null);
        Long Assize = Math.min(list.size(), size);
        if (current <= (list.size() / size)+1)
        {
            currents = current;
        }
        Page<Attendance> page=attendanceMapper.selectPage(new Page<>(currents, Assize),null);
        if (size > list.size())
        {
            return R.ok(page, "输入的大小太大，显示所有信息");
        }
        if (current > (list.size() / size)+1)
        {
            return R.failed("输入的页码太大，重新输入");
        }
        return page.getTotal()==0?R.failed("无信息"):R.ok(page,"输出信息");
    }

    @Override
    public R findPageDakaRecordsByType(Long current, Long size, Integer attendanceType) {
        QueryWrapper<Attendance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attendance_type", attendanceType);
        List<Attendance> list = attendanceMapper.selectList(queryWrapper);
        Long Assize = Math.min(list.size(), size);
        if (current <= (list.size() / size)+1)
        {
            currents = current;
        }
        Page<Attendance> page = attendanceMapper.selectPage(new Page<>(Assize,currents),queryWrapper);
        if (size > list.size())
        {
            return R.ok(page, "输入的大小太大，显示所有信息");
        }
        if (current > (list.size() / size)+1)
        {
            return R.failed("输入的页码太大，重新输入");
        }
        return page.getTotal() == 0 ? R.failed("无信息") : R.ok(page, "返回信息");
    }

    @Override
    public R findPageDakaRecordsById(Long current, Long size, Integer jobId) {
        QueryWrapper<Attendance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("job_id", jobId);
        List<Attendance> list = attendanceMapper.selectList(queryWrapper);
        Long Assize = Math.min(list.size(), size);
        if (current <= (list.size() / size)+1)
        {
            currents = current;
        }
        Page<Attendance> page = attendanceMapper.selectPage(new Page<>(Assize,currents),queryWrapper);
        if (size > list.size())
        {
            return R.ok(page, "输入的大小太大，显示所有信息");
        }
        if (current > (list.size() / size)+1)
        {
            return R.failed("输入的页码太大，重新输入");
        }
        return page.getTotal() == 0 ? R.failed("无信息") : R.ok(page, "返回信息");

    }
}
