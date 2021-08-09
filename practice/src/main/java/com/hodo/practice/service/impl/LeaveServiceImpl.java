package com.hodo.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.dto.Circuit;
import com.hodo.practice.entity.dto.check;
import com.hodo.practice.entity.po.Staff;
import com.hodo.practice.entity.po.TLeave;
import com.hodo.practice.service.LeaveService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @anthor :zyy
 * @description: 离职，请假说明表
 * @param: name
 * @return: integer
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
    /**
     *
     * @description: 流程
     * @param: leave
     * @return: List
     * @date: 2021/8/9
     */

    @Override
    public List<String> process(TLeave leave)
    {
        
        List<String> list = new ArrayList<>();
        int type = leave.getLeavetype();
        // li zhi
        if (type == 0)
        {
            list.add(leave.getDays(), CommonConstants.LIZHI);
        }
        // diao xiu
        if (type == 1)
        {
            list.add(leave.getLeavetype(), CommonConstants.DIAOXIU);
        }
        return list;
        
    }
    /**
     *
     * @description: 获得自己的流程信息
     * @param: name
     * @return: list
     * @date: 2021/8/9
     */

    @Override
    public List<TLeave> getMyService(String name)
    {
        TLeave leave = new TLeave();
        leave.setName(name);
        QueryWrapper<TLeave> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Name", name);
        
        return baseMapper.selectList(queryWrapper);
    }
    /**
     *
     * @description: 审核
     * @param: check
     * @return: list
     * @date: 2021/8/9
     */

    @Override
    public List<String> checkProcess(check check)
    {
        List<String> list = new ArrayList<>();
        // 0 tongguo
        if (check.getType() == 0)
        {
            list.add(0, "审核通过");
            list.add(check.getOpinion());
        }
        else
        {
            list.add(1, "审核不通过");
            list.add(check.getOpinion());
            
        }
        return list;
    }
    /**
     *
     * @description: 添加离开事件
     * @param: c
     * @return: integer
     * @date: 2021/8/9
     */

    
    @Override
    public Integer addLeaveProcess(Circuit c)
    {
        TLeave tLeave = new TLeave(null, c.getType(), null, c.getName(), c.getReason(), c.getLeaveTime(),
            c.getBackTime(), c.getDays(), null);
        return baseMapper.insert(tLeave);

    }
}
