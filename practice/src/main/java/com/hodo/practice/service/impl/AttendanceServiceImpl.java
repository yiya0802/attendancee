package com.hodo.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodo.practice.entity.dao.TAttendanceDao;
import com.hodo.practice.entity.po.Staff;
import com.hodo.practice.entity.po.TAttendance;
import com.hodo.practice.service.AttendanceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.TimeZone;

/**
 * @anthor :zyy
 * @description: 打卡的实现类
 * @param:
 * @return:
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl implements AttendanceService
{
    /**
     * 获取打卡的时间
     * 
     * @return date
     */
    @Override
    public String getTime()
    {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        simpleDateFormat.format(date);
        return date.toString();
    }
    
    /**
     * 通过名字查询departid
     * 
     * @param name
     * @return jobid
     */
    @Override
    public Integer getDepartIdByName(String name)
    {
        TAttendance tAttendance = new TAttendance();
        tAttendance.setName(name);
        QueryWrapper<TAttendance> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        Staff staff = (Staff)baseMapper.selectOne(wrapper);
        return staff.getJobId();
        
    }
    
    /**
     * 打卡
     * 
     * @param tAttendance
     * @return int
     */
    @Override
    public int daka(TAttendance tAttendance)
    {
        return this.baseMapper.insert(tAttendance);
    }
    
    /**
     * 通过名字找到打卡记录
     * 
     * @param name
     * @return list
     */
    @Override
    public List<TAttendance> findRecords(String name)
    {
        TAttendance tAttendance = new TAttendance();
        tAttendance.setName(name);
        QueryWrapper<TAttendance> wrapper = new QueryWrapper<>(tAttendance);
        return this.baseMapper.selectList(wrapper);
        
    }

    /**
     * 通过id找到打卡记录
     * @param id
     * @return list
     */

    @Override
    public List<TAttendance> findRecordsByID(Integer id) {
        return this.baseMapper.selectList(new QueryWrapper<TAttendance>().eq("num",id));
    }

}
