package com.hodo.practice.controller;

import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.R;
import com.hodo.practice.entity.po.Staff;
import com.hodo.practice.service.StaffService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @anthor :zyy
 * @description: 用户管理界面 查看,修改，删除自己的信息
 * @param:
 * @return :
 */
@Controller
@AllArgsConstructor
@RequestMapping("/index")
@Slf4j
public class SettingController
{
    private StaffService staffService;
    
    /**
     * 跳转到用户信息的用户管理
     *
     * @return
     */
    @RequestMapping("/setting")
   public Map<String,Object> userSetting(Staff staff)
    {
        return staffService.userList(staff);
    }
    
    /**
     * 用户信息的修改
     */
    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(Staff staff)
    {
        staffService.updateUserInfo(staff);
        R.ok(staff, CommonConstants.UPDATESUCCESS);
        return "setting";
    }

    /**
     * 用户新增
     */
    @RequestMapping("addUser")
    public  String AddStaff(Staff staff)
    {
        //更新
        if (null==staff.getJobId())
        {
            staffService.addUser(staff);

            return "setting";

        }
        return "setting";

    }

    /**
     * 用户删除 支持单记录删除，多记录删除
     */
    @RequestMapping("delete")
    public R delete(Integer[] ids)
    {
        staffService.deleteStaff(ids);
        return  R.ok(CommonConstants.DELETESUCCESS);
    }
}
