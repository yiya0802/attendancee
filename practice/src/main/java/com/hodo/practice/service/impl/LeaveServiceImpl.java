package com.hodo.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.R;
import com.hodo.practice.entity.po.Staff;
import com.hodo.practice.entity.po.TLeave;
import com.hodo.practice.service.LeaveService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @anthor :zyy
 * @description: 离职，请假说明表
 * @param:
 * @return :
 */
@Service
public class LeaveServiceImpl extends ServiceImpl implements LeaveService
{
    
    @Override
    public Integer getIdByName(String name)
    {
        TLeave tLeave = new TLeave();
        tLeave.setName(name);
        QueryWrapper<TLeave> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Name", name);
        Staff staff = (Staff)baseMapper.selectOne(queryWrapper);
        return staff.getJobId();
    }
    
    @Override
    public R<TLeave> process(TLeave leave)
    {
        return R.ok(leave, CommonConstants.LEAVESUCCESS);
        
    }
    
    @Override
    public List<TLeave> getMyService(String name)
    {
        TLeave leave = new TLeave();
        leave.setName(name);
        QueryWrapper<TLeave> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Name", name);
        
        return baseMapper.selectList(queryWrapper);
    }
}
