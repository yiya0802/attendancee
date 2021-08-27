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
@RequestMapping("/attendance")
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
     * @author: zyy
     * 如果是上班打卡，提前打卡的话提示不能提前打卡，8：00-8：30正常打卡，如果在这段时间内连续打卡，提示您已经打过卡了，如果在8：30
     * 以后第一次打卡，提示迟到了，如果连续在8：30以后打卡，提示已经打过卡了，如果第一次正常打卡，第二次在8：30以后打卡也会提示已经打过卡了
     *
     * 如果是下班打卡，在12：00-6：00提示早退，如果在早退时间内连续打卡，不给他打，并提示已经打过卡了,扣一次钱，如果在6：00以后第一次打卡，
     * 提示正常打卡。
     *
     * 如果在6：00以后连续打卡，更新打卡时间，如果第一次在早退时间内打卡，然后在正常打卡时间内打卡，就更新打卡时间，提示您在正常时间内打卡，
     * 并把早退的钱还给他.
     *
     * 扣钱之前先去salary表中查找工资，如果工资小于迟到早退的钱了，不再扣钱，最多扣到工资是0；
     * @param: [jobId, t]
     * @return: com.xiao.boot.bean.po.R
     * @date: 2021/8/26
     */

    public R start(@RequestParam("jobId") Integer jobId, @RequestParam("type") Integer t,@RequestParam("nowTime") String nowtime){
        Staff staff = staffService.findStaffById(jobId);
        if (staff==null)
        {
            return R.failed("打卡人员不存在");
        }
      //上班打卡
        if (t==0)
        {
            SimpleDateFormat sf=new SimpleDateFormat("HH:mm");
            SimpleDateFormat snf=new SimpleDateFormat("yyyy-MM-dd");
            Date nowDate=null;
            Date nowTime=null;
            Date BeginTime=null;
            Date EndTime=null;
            Date DinnerTime=null;
            Date date=new Date();
            try {
     //         nowTime=sf.parse(sf.format(nowTime));
                nowTime=sf.parse(nowtime);
                BeginTime=sf.parse("08:00");
                EndTime=sf.parse("08:30");
                DinnerTime=sf.parse("12:00");
                nowDate=snf.parse(snf.format(date));

            } catch (ParseException e) {
                e.printStackTrace();
            }
            //提早打卡
            if (nowTime.before(BeginTime))
            {
                return R.failed("太早了，晚点来打卡吧");

            }
            //正常打卡
            if (nowTime.after(BeginTime)&& nowTime.before(EndTime))
            {
                //如果打很多次卡，就返回不能重复打卡
                if (attendanceService.findDakaRecordById(jobId,nowDate)>=1)
                {
                    return R.failed("您已经打过卡了！");
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time=sdf.format(date);
                Attendance attendance=new Attendance();
                attendance.setJobId(jobId);
                attendance.setAttendanceTime(time);
                attendance.setAttendanceType(1);
                attendance.setAttendanceDate(nowDate);
                attendance.setName(staff.getName());
                attendance.setDepartmentId(staff.getDepartmentId());
                attendance.setName(staff.getName());
                attendance.setAttendanceStatus(1);
                int num=attendanceService.daKa(attendance);
                if (num==0)
                    return R.failed("打卡失败");
                else
                    return R.ok(attendance,"打卡成功");
            }
            //迟到
            if (nowTime.after(EndTime)&&nowTime.before(DinnerTime))
            {
                if (attendanceService.findDakaRecordById(jobId,nowDate)>=1)
                {
                    return R.failed("您已经打过卡了!");
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(date);
                Attendance attendance=new Attendance();
                attendance.setJobId(jobId);
                attendance.setAttendanceTime(time);
                attendance.setAttendanceType(0);
                attendance.setName(staff.getName());
                attendance.setAttendanceStatus(1);
                attendance.setAttendanceDate(nowDate);
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
           SimpleDateFormat snf=new SimpleDateFormat("yyyy-MM-dd");
           Date nowDate=null;
           Date DinnerTime=null;
           try {
               nowTime=sf.parse(nowtime);
               BeginTime=sf.parse("18:00");
               nowDate=snf.parse(snf.format(new Date()));
               DinnerTime= sf.parse("12:00");
           } catch (ParseException e) {
               e.printStackTrace();
           }
           if (nowTime.before(DinnerTime))
           {

               return R.failed("不能在中午前打下班卡");
           }
           //早退
           if (nowTime.after(DinnerTime)&& nowTime.before(BeginTime))
           {
               if (attendanceService.findDakaRecordById(jobId,nowDate)>=2)
               {
                   return R.failed("您已经打过卡了!");
               }
               Date date = new Date();
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String time = sdf.format(date);
               Attendance attendance=new Attendance();
               attendance.setJobId(jobId);
               attendance.setAttendanceTime(time);
               attendance.setAttendanceType(2);
               attendance.setName(staff.getName());
               attendance.setAttendanceDate(nowDate);
               attendance.setAttendanceStatus(1);
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
           //正常6点后打卡
           if (nowTime.after(BeginTime))
           {
               //更新一下打卡时间
               if (attendanceService.findDakaRecordById(jobId,nowDate)>=2)
               {
                   Date date = new Date();
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   String time = sdf.format(date);
                   List<Attendance> attendance=attendanceService.findDakaRecordByIdAndDate(jobId,nowDate);
                   Attendance updateAttendance=attendance.get(1);
                   updateAttendance.setAttendanceTime(time);
                   updateAttendance.setAttendanceType(1);
                   attendanceService.updateAttendance(updateAttendance);
                   //如果打卡时间是在早退时间内，把钱还给他
//                   if (updateAttendance.getAttendanceDate().after(DinnerTime) &&updateAttendance.getAttendanceDate().before(BeginTime))
//                   {
//                       Salary salary=salaryService.findSalaryById(staff.getJobId());
//                       salary.setEarlyLeave(salary.getEarlyLeave()-100);
//                       salaryService.updateSalary(salary);
//                   }
                   return R.ok("更新了打卡时间");
               }
               Date date = new Date();
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String time = sdf.format(date);
               Attendance attendance=new Attendance();
               attendance.setJobId(jobId);
               attendance.setAttendanceTime(time);
               attendance.setAttendanceType(1);
               attendance.setName(staff.getName());
               attendance.setAttendanceDate(nowDate);
               attendance.setAttendanceStatus(1);
               attendance.setDepartmentId(staff.getDepartmentId());
               attendance.setName(staff.getName());
               return R.ok(attendanceService.daKa(attendance));
           }

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
