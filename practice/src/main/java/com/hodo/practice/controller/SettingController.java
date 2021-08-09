package com.hodo.practice.controller;

import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.R;
import com.hodo.practice.entity.dto.UpdateStaff;
import com.hodo.practice.entity.po.Staff;
import com.hodo.practice.service.StaffService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @anthor :zyy
 * @description: 用户管理界面 查看,修改，删除自己的信息
 * @param: staff
 * @return :R
 */
@Controller
@AllArgsConstructor
@RequestMapping("/index")
@Slf4j
public class SettingController
{
    private StaffService staffService;
    
   /**
    *
    * @description: 用户的信息
    * @param: staff
    * @return: R
    * @date: 2021/8/9
    */

    @RequestMapping("/setting")
   public R userSetting(Staff staff)
    {
        return R.ok(staffService.userList(staff));
    }

    /**
     * @description: 更新用户信息
     * @param updateStaff
     * @return
     */
    @RequestMapping("/updateUserInfo")
    public R updateUserInfo(UpdateStaff updateStaff)
    {
        Integer integer=staffService.updateUserInfo(updateStaff);
        if (integer==0)
        {
            return R.failed();
        }
        return R.ok(staffService.updateUserInfo(updateStaff), CommonConstants.UPDATESUCCESS);

    }

    /**
     * @description:用户新增
     * @param staff
     * @return
     */
    @RequestMapping("addUser")
    public R<R> AddStaff(Staff staff)
    {
        //更新
        if (null==staff.getJobId())
        {
            return R.ok(staffService.addUser(staff));

        }
        return R.failed();

    }

    /**
     * 用户删除 支持单记录删除，多记录删除
     * @param ids
     * @return R
     */
    @RequestMapping("delete")
    public R delete(Integer[] ids)
    {
        int delete=0;
        delete=staffService.deleteStaff(ids);
        if (delete==0) {
            return R.failed("no delete type");
        }
        return  R.ok(delete,CommonConstants.DELETESUCCESS);
    }
}
