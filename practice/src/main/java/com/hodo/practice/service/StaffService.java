package com.hodo.practice.service;

import com.hodo.practice.entity.Staff;
import org.springframework.stereotype.Service;

/**
 * @anthor :zyy
 * @description:  用户登陆方法
 * @param:
 * @return :
 */
@Service
public interface StaffService {
    /**
     * 登录的逻辑
     * @param username
     * @param password
     * @return
     */
    public Staff login(String username, String password) ;

    /**
     * 根据用户名来查找id
     * @param username
     * @return
     */
    public Staff findByUserName(String username);







}
