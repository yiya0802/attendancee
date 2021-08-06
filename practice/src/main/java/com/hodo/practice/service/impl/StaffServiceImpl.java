package com.hodo.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.R;
import com.hodo.practice.entity.po.Staff;
import com.hodo.practice.service.StaffService;
import com.hodo.practice.utils.AssertUtil;
import com.hodo.practice.utils.StringUtils;
import com.hodo.practice.utils.md5Utile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        
        Staff staff = (Staff)this.findByUserName(username);
        AssertUtil.isTrue(null == staff, "用户不存在");
        String salt = staff.getSalt();
        
        String NewPass = md5Utile.inputPassToDBPass(password, salt);
        AssertUtil.isTrue(!NewPass.equals(password), "密码错误");
        
        return staff;
    }
    
    @Override
    public List<Staff> findByUserName(String username)
    {
        Staff staff = new Staff();
        staff.setName(username);
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        return baseMapper.selectList(queryWrapper);
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
        Staff temp = (Staff)this.findByUserName(staff.getName());
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
    
    @Override
    public List<Staff> findAllStaff()
    {
        return baseMapper.selectList(null);
    }
    
    /**
     * 分页list
     * 
     * @param staff
     * @return
     */
    @Override
    public Map<String, Object> userList(Staff staff)
    {
        IPage<Staff> page = new Page<Staff>(staff.getJobId(), staff.getPost());
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<Staff>();
        if (StringUtils.isNotEmpity(staff.getName()))
        {
            queryWrapper.like("name", staff.getName());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", page.getRecords());
        map.put("count", page.getTotal());
        map.put("code", 0);
        return map;
    }

    /**
     * 添加用户
     * @param staff
     * @return
     */
    
    @Override
    public R addUser(Staff staff)
    {
        baseMapper.insert(staff);
        return R.ok(staff, CommonConstants.ADDSUCCESS);
    }

    /**
     * 删除用户
     * @param ids
     */
    
    @Override
    public void deleteStaff(Integer[] ids)
    {
        Assert.isTrue(ids == null || ids.length == 0, "请选择你要选择的用户id", null);
        List<Staff> list = new ArrayList<Staff>();
        for (Integer id : ids)
        {
            Staff staff = this.findById(id);
            list.add(staff);
        }
        baseMapper.deleteBatchIds(list);
    }
    
    
    @Override
    public Staff findById(Integer id)
    {
        return (Staff)this.baseMapper.selectById(id);
    }
    
}
