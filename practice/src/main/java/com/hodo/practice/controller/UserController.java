package com.hodo.practice.controller;

import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.R;
import com.hodo.practice.entity.po.Staff;
import com.hodo.practice.service.StaffService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @anthor :zyy
 * @description: 登陆界面 信息展示
 * @param: :username password
 * @return :R
 */
@Controller
@AllArgsConstructor
@RequestMapping("/login")
@Slf4j
public class UserController
{
    private StaffService staffService;
    
    @RequestMapping("/toLogin")

    public R<Staff> login(@Param("username") String username, @Param("password") String password)
    {
        try
        {
            Staff staff = staffService.login(username, password);
             return R.ok(staff, CommonConstants.LOGIN_SUCCESS);


        }
        catch (Exception e)
        {
            e.printStackTrace();
             return R.failed(CommonConstants.LOGIN_ERROR);

        }
    }





}
