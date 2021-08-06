package com.hodo.practice.controller;

import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.R;
import com.hodo.practice.entity.po.Staff;
import com.hodo.practice.entity.po.TLeave;
import com.hodo.practice.service.LeaveService;
import com.hodo.practice.service.StaffService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @anthor :zyy
 * @description: 事务表，请假，离开，离职
 * @param:
 * @return :
 */
@Controller
@RequestMapping("/Leave")
@AllArgsConstructor
@Getter
@Setter
public class LeaveController
{
    private LeaveService leaveService;
    
    private StaffService staffService;
    
    @RequestMapping("liucheng")
    private String liucheng(String name, Integer type, String reason, Integer days, Date LeaveTime, Date BackTime)
    {
        
        TLeave leave =
            new TLeave(null, type, leaveService.getIdByName(name), name, reason, LeaveTime, BackTime, days, null);
        
        leaveService.process(leave);
        
        return "liucheng";
        
    }
    
    /**
     * 查看自己的事务信息
     * 
     * @return
     */
    
    @RequestMapping("guanli")
    private String guanli(String name)
    {
        leaveService.getMyService(name);
        return "guanli";
    }
    
    /**
     * 审核流程
     *
     * @return
     */
    @RequestMapping("shenhe")
    private String shenhe()
    {
        return "shenhe";
        
    }
}
