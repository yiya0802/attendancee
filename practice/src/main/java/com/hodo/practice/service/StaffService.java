package com.hodo.practice.service;

import com.hodo.practice.entity.R;
import com.hodo.practice.entity.dto.UpdateStaff;
import com.hodo.practice.entity.po.Staff;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @anthor :zyy
 * @description: 用户登陆方法
 * @param:
 * @return :
 */
@Service
public interface StaffService {
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
    List<Staff> findByUserName(String username);

    /**
     * 用户更新操作
     *
     * @param updateStaff
     * @return
     */

    Integer updateUserInfo(UpdateStaff updateStaff);

    /**
     * 查找所有员工
     * @return
     */
    List<Staff> findAllStaff();

    /**
     * 用户列表展示
     * @param staff
     * @return
     */

    Map<String, Object> userList(Staff staff);

    /**
     * 添加用户
     * @param staff
     * @return
     */

    R addUser(Staff staff);

    /**
     * 删除用户
     * @param ids
     */

    int deleteStaff(Integer[] ids);

    /**
     * 通过id找用户
     * @param id
     * @return
     */

    Staff findById(Integer id);
}
