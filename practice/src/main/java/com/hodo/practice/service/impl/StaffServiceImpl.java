package com.hodo.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.R;
import com.hodo.practice.entity.po.Staff;
import com.hodo.practice.service.StaffService;
import com.hodo.practice.utils.AssertUtil;
import com.hodo.practice.utils.StringUtils;
import com.hodo.practice.utils.md5Utile;
import org.springframework.stereotype.Service;

/**
 * @anthor :zyy
 * @description: 用户service实现层
 * @param:
 * @return :
 */
@Service
public class StaffServiceImpl extends ServiceImpl implements StaffService
{
    @Override
    public Staff login(String username, String password)
    {
        AssertUtil.isTrue(StringUtils.isEmpity(username), "用户名不能为空！");
        AssertUtil.isTrue(StringUtils.isEmpity(password), "密码不能为空！");
        
        Staff staff = this.findByUserName(username);
        AssertUtil.isTrue(null == staff, "用户不存在");
        String salt = staff.getSalt();
        
        String NewPass = md5Utile.inputPassToDBPass(password, salt);
        AssertUtil.isTrue(!NewPass.equals(password), "密码错误");
        
        return staff;
    }
    
    @Override
    public Staff findByUserName(String username)
    {
        return (Staff)this.baseMapper.selectOne(new QueryWrapper<Staff>().eq("username", username));
    }
    
    /**
     * 更新 用户名更新唯一 非空
     * 
     * @param staff
     */
    @Override
    public R updateUserInfo(Staff staff)
    {
        // 判断非空
        if (StringUtils.isEmpity(staff.getName()))
        {
            return R.failed(CommonConstants.UPDATEERROR);
        }
        Staff temp = this.findByUserName(staff.getName());
        // 判断唯一性
        if (temp != null)
        {
            return R.failed(CommonConstants.UPDATEEXITS);
        }
        if (!this.updateById(staff))
        {
            return R.failed(CommonConstants.UPDATEFAILURE);
        }
        return R.ok(staff, CommonConstants.UPDATESUCCESS);
        
    }
    
}
