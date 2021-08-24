package com.xiao.boot.controller;

import com.xiao.boot.bean.po.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.xiao.boot.bean.dto.Checktable;
import com.xiao.boot.bean.dto.Circuit;
import com.xiao.boot.bean.po.R;
import com.xiao.boot.service.LeaveService;
import com.xiao.boot.service.StaffService;

import lombok.AllArgsConstructor;

/**
 * @anthor :zyy
 * @description: 事务表，请假，离开，离职
 * @param: Circuit
 * @return :R
 */
@Controller
@RequestMapping("/leave")
@AllArgsConstructor
public class LeaveController
{
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private StaffService staffService;
    
    @GetMapping("/addcircuit")
    @ResponseBody
    /**
     *
     * @description: 0请假，1离职，2报销
     * @param: [c]
     * @return: com.xiao.boot.bean.po.R
     * @date: 2021/8/13
     */

    private R circuit(Circuit c) {
        if (c.getLeavetype()!=0 && c.getLeavetype()!=1 &&c.getLeavetype()!=2)
        {
            return R.failed("leavetype只能为0，1，2");
        }
        if (c.getLeavetype() == 0) {
            return R.ok(leaveService.addLeaveProcess(c));
        }

        else if (c.getLeavetype()==1)
        {
            Staff staff=staffService.findStaffByName(c.getName());
            if (staff!=null)
            {
                staffService.deleteStaffById(staff.getJobId());
            }

            return  staff==null?R.failed("员工不存在"):R.ok(leaveService.addResignProcess(c));
        }

       return R.ok(leaveService.addReimburseProcess(c));


    }

    /**
     * 查看自己的事务信息
     * 
     * @param: name
     * @return R
     */
    
    @GetMapping("/mycircuit")
    @ResponseBody
    private R guanli(String name)
    {
        if (StringUtils.isEmpty(name))
        {
            return R.failed("名字不能为空");
        }
        leaveService.getMyLeaveService(name);
        if ((leaveService.getMyLeaveService(name))==null)
        {
            return R.failed("暂无此员工的事务");
        }
        
        return R.ok(leaveService.getMyLeaveService(name));
    }
    
    /**
     * 审核流程 审核（是否通过、意见）
     * 
     * @param: check
     *
     * @return R
     */
    @GetMapping("/check")
    @ResponseBody

   private R shenhe(Checktable check)
    {
        if (check.getType()!=0 && check.getType()!=1 &&check.getType()!=2)
        {
            return R.failed("请输入正确的类型");
        }
        Integer num=leaveService.checkProcess(check);
        if (num==0)
        {
            return R.failed("审核不通过");
        }
        return R.ok(num);
    }

    @GetMapping("/findpagecircuit")
    @ResponseBody
    private R findPageCircuit(Long current,Long size)
    {
        return leaveService.findPageCircuit(current,size);
    }
}
