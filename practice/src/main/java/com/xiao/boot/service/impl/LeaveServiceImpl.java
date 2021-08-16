package com.xiao.boot.service.impl;

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

/**
 * @anthor :zyy
 * @description:
 * @Date 2021/8/13 15:23
 * @param:
 * @return :
 */
@Service
public class LeaveServiceImpl implements LeaveService{
    @Autowired
    LeaveMapper leaveMapper;
    @Autowired
    CheckMapper checkMapper;

    @Override
    public Leavetable getMyLeaveService(String name) {
      //  return leaveMapper.selectOne(new QueryWrapper<Leave>().lambda().eq(Leave::getName,name));
        QueryWrapper<Leavetable>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return leaveMapper.selectOne(queryWrapper);
    }

    @Override
    public int addLeaveProcess(Circuit c) {

        Leavetable leave=new Leavetable(0,c.getLeavetype(),null,c.getName(),
                c.getReason(),c.getLeaveTime(),c.getBackTime(),c.getDays(),0.0);
        return leaveMapper.insert(leave);
    }



    @Override
    public int addResignProcess(Circuit c) {
        Leavetable leavetable=new Leavetable(null,c.getLeavetype(),null,c.getName(),c.getReason(),
                null,null,null,null);
        return leaveMapper.insert(leavetable);
    }

    @Override
    public int addReimburseProcess(Circuit c) {
        Leavetable leavetable=new Leavetable(null,c.getLeavetype(),null,c.getName(),c.getReason(),
                null,null,null,c.getAmmount());
        return leaveMapper.insert(leavetable);
    }

    @Override
    public Integer checkProcess(Checktable check) {
        Checktable check1=new Checktable();
        BeanUtils.copyProperties(check,check1);
        int num=checkMapper.insert(check1);
        return num;
    }
}
