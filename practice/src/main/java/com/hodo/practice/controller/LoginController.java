package com.hodo.practice.controller;

import com.hodo.practice.entity.Staff;
import com.hodo.practice.service.StaffService;
import com.hodo.practice.vo.RestBean;
import com.hodo.practice.vo.RestBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @anthor :zyy
 * @description:
 * @param:
 * @return :
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Resource
    private StaffService staffService;



    @RequestMapping("/toLogin")
    @ResponseBody
    public RestBean login(String username, String password, HttpSession session)
    {
        try{
        Staff staff=staffService.login(username,password);
        session.setAttribute("staff",staff);
        return RestBean.success("用户登陆成功");}
        catch (Exception e)
        {
        e.printStackTrace();
        return  RestBean.error(RestBeanEnum.ERROR);
        }
    }

}
