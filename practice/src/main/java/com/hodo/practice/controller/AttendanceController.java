package com.hodo.practice.controller;

import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.R;
import com.hodo.practice.entity.po.Staff;
import com.hodo.practice.entity.po.TAttendance;
import com.hodo.practice.service.AttendanceService;
import com.hodo.practice.service.StaffService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @anthor :zyy
 * @description: 打卡界面，以及查看打卡记录
 * @param:
 * @return :
 */

@Controller
@AllArgsConstructor
@Getter
@Setter
/**
 *
 * @description: 打卡
 * @param: String name, Integer type
 * @return: R
 * @date: 2021/8/9
 */

public class AttendanceController
{
    private AttendanceService attendanceService;
    
    private StaffService staffService;
    
    @RequestMapping("/daka")
    
    public R<Integer> daka(String name, Integer type)
    {
        Staff staff = (Staff)staffService.findByUserName(name);
        TAttendance tAttendance = new TAttendance(null, null, name, (String)attendanceService.getDepartIdByName(name),
            type, staff.getJobId(), attendanceService.getTime());
        if (0 == attendanceService.daka(tAttendance))
        {
            return R.failed(CommonConstants.NODAKARECORDS);
        }
        return R.ok(attendanceService.daka(tAttendance), CommonConstants.DAKASUCCESS);
        
    }
    
    /**
     * 通过name查询打卡记录
     * 
     * @param name
     * @return 如果没有记录，返回打卡界面，如果有记录，显示记录
     */
    @RequestMapping("/fidnDakaRecord")
    public R<Object> findDakaRecord(String name)
    {
        List<TAttendance> records = attendanceService.findRecords(name);
        if (records.isEmpty())
        {
            return R.failed();
        }
        return R.ok(records);
    }
    /**
     *
     * @description: 寻找打卡记录 通过id
     * @param: id
     * @return:  R
     * @date: 2021/8/9
     */
    @PostMapping("/findRecords")
    public R findDakaRecordByID(Integer id)
    {
        List<TAttendance> records = attendanceService.findRecordsByID(id);
        if (records.isEmpty())
        {
            return R.failed(CommonConstants.FINDDAKARECORD);
        }
        return R.ok(records);
    }
    
}
