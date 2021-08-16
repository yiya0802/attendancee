package com.xiao.boot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
           return R.failed();
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
}
