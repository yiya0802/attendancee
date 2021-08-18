package com.xiao.boot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.xiao.boot.bean.po.Salary;
import com.xiao.boot.service.SalaryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.xiao.boot.bean.po.Attendance;
import com.xiao.boot.bean.po.R;
import com.xiao.boot.bean.po.Staff;
import com.xiao.boot.service.AttendanceService;
import com.xiao.boot.service.DepartmentService;
import com.xiao.boot.service.StaffService;

@Controller
@RequestMapping("attendance")
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;
    @Autowired
    StaffService staffService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    SalaryService salaryService;
    // 上下班打卡
    @GetMapping("/daka")
    @ResponseBody
    /**
     *
     * @description: 打卡
     * @param: [jobId, t]
     * @return: com.xiao.boot.bean.po.R
     * @date: 2021/8/16
     */

    public R start(@RequestParam("jobId") Integer jobId, @RequestParam("type") Integer t){
        Staff staff = staffService.findStaffById(jobId);
        if (staff==null)
        {
            return R.failed("打卡人员不存在");
        }
      //上班打卡
        if (t==0)
        {
            SimpleDateFormat sf=new SimpleDateFormat("HH:mm");
            Date nowTime=null;
            Date BeginTime=null;
            Date EndTime=null;
            try {
                nowTime=sf.parse(sf.format(new Date()));
                BeginTime=sf.parse("08:00");
                EndTime=sf.parse("08:30");

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (nowTime.after(BeginTime)&& nowTime.before(EndTime))
            {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                Attendance attendance=new Attendance();
                attendance.setJobId(jobId);
                attendance.setAttendanceTime(time);
                attendance.setAttendanceType(t);
                attendance.setName(staff.getName());
                attendance.setSex(staff.getSex());
                attendance.setBirthday(staff.getBirthday());
                attendance.setDepartmentId(staff.getDepartmentId());
                attendance.setName(staff.getName());
                int num=attendanceService.daKa(attendance);
                if (num==0)
                    return R.failed("打卡失败");
                else
                    return R.ok(attendance,"打卡成功");
            }
            if (nowTime.before(BeginTime))
            {
                return R.failed("太早了，晚点来打卡把");
            }
            if (nowTime.after(EndTime))
            {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                Attendance attendance=new Attendance();
                attendance.setJobId(jobId);
                attendance.setAttendanceTime(time);
                attendance.setAttendanceType(t);
                attendance.setName(staff.getName());
                attendance.setSex(staff.getSex());
                attendance.setBirthday(staff.getBirthday());
                attendance.setDepartmentId(staff.getDepartmentId());
                attendance.setName(staff.getName());
                Salary salary=salaryService.findSalaryById(staff.getJobId());
                salary.setLateMoney(salary.getLateMoney()+50);
                int num=salaryService.updateSalary(salary);
                if (num==0)
                {
                    return R.failed();
                }
                return R.ok(attendanceService.daKa(attendance),"您已经超过打卡时间，会被扣费50");
            }
        }
        //下班打卡
       if (t==1)
       {
           SimpleDateFormat sf=new SimpleDateFormat("HH:mm");
           Date nowTime=null;
           Date BeginTime=null;
           Date EndTime=null;
           try {
               nowTime=sf.parse(sf.format(new Date()));
               BeginTime=sf.parse("18:00");
               EndTime=sf.parse("18:30");
           } catch (ParseException e) {
               e.printStackTrace();
           }
           //早退
           if (nowTime.before(BeginTime))
           {
               Date date = new Date();
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String time = sdf.format(date);
               Attendance attendance=new Attendance();
               attendance.setJobId(jobId);
               attendance.setAttendanceTime(time);
               attendance.setAttendanceType(t);
               attendance.setName(staff.getName());
               attendance.setSex(staff.getSex());
               attendance.setBirthday(staff.getBirthday());
               attendance.setDepartmentId(staff.getDepartmentId());
               attendance.setName(staff.getName());
               Salary salary=salaryService.findSalaryById(staff.getJobId());
               salary.setEarlyLeave(salary.getEarlyLeave()+100);
               int num=salaryService.updateSalary(salary);
               if (num==0)
               {
                   return R.failed();
               }
               return R.ok(attendanceService.daKa(attendance),"您早退了，会被扣费100");

           }
               Date date = new Date();
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String time = sdf.format(date);
               Attendance attendance=new Attendance();
               attendance.setJobId(jobId);
               attendance.setAttendanceTime(time);
               attendance.setAttendanceType(t);
               attendance.setName(staff.getName());
               attendance.setSex(staff.getSex());
               attendance.setBirthday(staff.getBirthday());
               attendance.setDepartmentId(staff.getDepartmentId());
               attendance.setName(staff.getName());
               int num=attendanceService.daKa(attendance);
               if (num==0)
                   return R.failed("打卡失败");
               else
                   return R.ok(attendance,"打卡成功");

       }
        return R.failed("请输入正确的type值（0或者1）");



    }

    // 查看打卡记录
    @GetMapping("/find_daka_record")
    @ResponseBody
    /**
     *
     * @description: 查看打卡记录
     * @param: [jobId]
     * @return: com.xiao.boot.bean.po.R<java.util.List<com.xiao.boot.bean.po.Attendance>>
     * @date: 2021/8/16
     */

    public R<List<Attendance>> findDakaRecord(@Param("jobId") Integer jobId){
        List<Attendance> records = attendanceService.findDakaRecord(jobId);
       if (records==null)
       {
           return R.failed("jobid 不存在！");
       }
       return R.ok(records);
    }

    // 查看部门打卡记录
    @GetMapping("/find_dep_daka_record")
    @ResponseBody
    /**
     *
     * @description: 查看部门打卡记录
     * @param: [departmentId]
     * @return: com.xiao.boot.bean.po.R<java.util.List<com.xiao.boot.bean.po.Attendance>>
     * @date: 2021/8/16
     */

    public R<List<Attendance>> findDepDakaRecord(@Param("departmentId") String departmentId){

        List<Attendance> list=attendanceService.findDepDakaRecord(departmentId);
        if (list==null)
        {
            return R.failed();
        }
        return R.ok(list);
        }



    // 按姓名和部门号查找打卡记录
    @GetMapping("/find_record_by_name")
    @ResponseBody
    /**
     *
     * @description: 按照名字找到打卡记录
     * @param: [name]
     * @return: com.xiao.boot.bean.po.R<java.util.List<com.xiao.boot.bean.po.Attendance>>
     * @date: 2021/8/16
     */

    public R<List<Attendance>> findDakaRecordByName(@RequestParam("name")String name){
        String departmentId= staffService.findDepartIdByName(name);
        List<Attendance> records = attendanceService.findDakaRecordByName(departmentId,name);
        if (records.size() == 0){
            return R.failed();
        }
        return R.ok(records);
    }

    @GetMapping("/find_record_by_type")
    @ResponseBody
    /**
     *
     * @description: 通过类型差打卡记录
     * @param: [type]
     * @return: com.xiao.boot.bean.po.R
     * @date: 2021/8/17
     */

    public R findRecordByType(Integer type)
    {
        List<Attendance> records=attendanceService.findDakaRecordByType(type);
        if (records==null)
        {
            return R.failed();
        }
        return R.ok(records);
    }

}
