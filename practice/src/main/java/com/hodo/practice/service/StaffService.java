package com.hodo.practice.service;

import com.hodo.practice.entity.R;
import com.hodo.practice.entity.po.Staff;
import org.springframework.stereotype.Service;

/**
 * @anthor :zyy
 * @description: 用户登陆方法
 * @param:
 * @return :
 */
@Service
public interface StaffService
{
    /**
     * 登录的逻辑
     * 
     * @param username
     * @param password
     * @return
     */
    Staff login(String username, String password);
    
    /**
     * 根据用户名来查找id
     * 
     * @param username
     * @return
     */
    Staff findByUserName(String username);

    /**
     *
     * 用户更新操作
     * @param staff
     * @return
     */

    R updateUserInfo(Staff staff);
}
