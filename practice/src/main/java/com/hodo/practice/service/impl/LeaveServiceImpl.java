package com.hodo.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.R;
import com.hodo.practice.entity.po.TLeave;
import com.hodo.practice.service.LeaveService;
import org.springframework.stereotype.Service;

/**
 * @anthor :zyy
 * @description:
 * @param:
 * @return :
 */
@Service
public class LeaveServiceImpl extends ServiceImpl implements LeaveService {

    @Override
    public Integer getIdByName(String name) {
    return (Integer) baseMapper.selectOne(new QueryWrapper());

    }

    @Override
    public R<TLeave> process(TLeave leave) {
        return R.ok(leave, CommonConstants.LEAVESUCCESS);
    }
}
