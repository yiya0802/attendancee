package com.xiao.boot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiao.boot.bean.po.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.boot.bean.dto.Checktable;
import com.xiao.boot.bean.dto.Circuit;
import com.xiao.boot.bean.dto.Leavetable;
import com.xiao.boot.mapper.CheckMapper;
import com.xiao.boot.mapper.LeaveMapper;
import com.xiao.boot.service.LeaveService;

import java.util.List;

/**
 * @anthor :zyy
 * @description:
 * @Date 2021/8/13 15:23
 * @param:
 * @return :
 */
@Service
public class LeaveServiceImpl implements LeaveService
{
    @Autowired
    LeaveMapper leaveMapper;
    
    @Autowired
    CheckMapper checkMapper;

    private long currents = 1;

    @Override
    public Leavetable getMyLeaveService(String name)
    {
        // return leaveMapper.selectOne(new QueryWrapper<Leave>().lambda().eq(Leave::getName,name));
        QueryWrapper<Leavetable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return leaveMapper.selectOne(queryWrapper);
    }
    
    @Override
    public int addLeaveProcess(Circuit c)
    {
        QueryWrapper<Checktable> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",c.getName());
        Checktable checktable=checkMapper.selectOne(queryWrapper);

        Leavetable leave = new Leavetable(0, c.getLeavetype(), null, c.getName(), c.getReason(), c.getLeaveTime(),
            c.getBackTime(), c.getDays(), 0.0,0,null);
        return leaveMapper.insert(leave);
    }
    
    @Override
    public int addResignProcess(Circuit c)
    {
        QueryWrapper<Checktable> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",c.getName());
        Checktable checktable=checkMapper.selectOne(queryWrapper);
        Leavetable leavetable =
            new Leavetable(0, c.getLeavetype(), null, c.getName(), c.getReason(), null, null, null, null,0,null);
        return leaveMapper.insert(leavetable);
    }
    
    @Override
    public int addReimburseProcess(Circuit c)
    {
        QueryWrapper<Checktable> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",c.getName());
        Checktable checktable=checkMapper.selectOne(queryWrapper);
        Leavetable leavetable =
            new Leavetable(0, c.getLeavetype(), null, c.getName(), c.getReason(), null, null, null, c.getAmmount(),0,null);
        return leaveMapper.insert(leavetable);
    }

    @Override
    public R findPageCircuit(Long current, Long size) {
        if (current==null || size==null)
        {
            return R.failed("current 和 size不能为空");
        }
        List<Leavetable> list=leaveMapper.selectList(null);
        Long Assize = Math.min(list.size(), size);
        if (current <= (list.size() / size)+1)
        {
            currents = current;
        }
        Page<Leavetable> page=leaveMapper.selectPage(new Page<>(currents, Assize),null);
        if (size > list.size())
        {
            return R.ok(page, "输入的大小太大，显示所有信息");
        }
        if (current > (list.size() / size)+1)
        {
            return R.failed("输入的页码太大，重新输入");
        }
        return page.getTotal()==0?R.failed("无信息"):R.ok(page,"输出信息");
    }


    @Override
    public Integer checkProcess(Checktable check)
    {
        Checktable check1 = new Checktable();
        BeanUtils.copyProperties(check, check1);
        QueryWrapper<Leavetable>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",check.getName());
        Leavetable leavetable=leaveMapper.selectOne(queryWrapper);
        if (leavetable==null)
        {
            return 0;
        }
        leavetable.setOpinion(check.getOpinion());
        leavetable.setStatus(check.getStatus());
        leaveMapper.updateById(leavetable);
        int num = checkMapper.insert(check1);
        return num;
    }
}
