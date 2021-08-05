package com.hodo.practice.controller;

import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.R;
import com.hodo.practice.entity.po.Staff;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @anthor :zyy
 * @description: 用户管理界面 查看自己的信息，可修改
 * @param:
 * @return :
 */
@Controller
@AllArgsConstructor
@RequestMapping("/login")
@Slf4j
public class SettingController
{
    private SettingController staffService;
    
    /**
     * 跳转到用户信息的用户管理
     *
     * @return
     */
    @RequestMapping("/setting")
    public String setting()
    {
        return "login/setting";
    }
    
    /**
     * 用户信息的修改 前端传入想修改的staff信息进来
     */
    @RequestMapping("/updateUserInfo")
    public R updateUserInfo(Staff staff)
    {
        staffService.updateUserInfo(staff);
        return R.ok(staff, CommonConstants.UPDATESUCCESS);
    }
    
}
