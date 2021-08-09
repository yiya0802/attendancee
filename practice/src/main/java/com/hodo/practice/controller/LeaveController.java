package com.hodo.practice.controller;

import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.R;
import com.hodo.practice.entity.dto.Circuit;
import com.hodo.practice.entity.dto.check;
import com.hodo.practice.entity.po.TLeave;
import com.hodo.practice.service.LeaveService;
import com.hodo.practice.service.StaffService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * @anthor :zyy
 * @description: 事务表，请假，离开，离职
 * @param:  Circuit
 * @return :R
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

    
    @RequestMapping("circuit")
    private R circuit(Circuit c)
    {
        if (leaveService.addLeaveProcess(c)==0)
        {
            return R.failed(CommonConstants.INSERTERROR);
        }
        return R.ok(leaveService.addLeaveProcess(c));
        
    }
    
    /**
     * 查看自己的事务信息
     * @param:  name
     * @return  R
     */
    
    @RequestMapping("guanli")
    private R<? extends Object> guanli(String name)
    {
        leaveService.getMyService(name);
        if (CollectionUtils.isEmpty(leaveService.getMyService(name)))
        {
            return R.failed(CommonConstants.NOUSER);
        }

        return R.ok(leaveService.getMyService(name));
    }
    
    /**
     * 审核流程 审核（是否通过、意见）
     * @param:  check
     *
     * @return  R<List<String>>
     */
    @RequestMapping("shenhe")
    private R<List<String>> shenhe(check check)
    {
        List<String>list =leaveService.checkProcess(check);
        if (CollectionUtils.isEmpty(list))
        {
            return R.failed(CommonConstants.NOCHECK);
        }
        return R.ok(list);
    }
}
