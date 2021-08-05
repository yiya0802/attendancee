package com.hodo.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodo.practice.entity.po.TSalary;
import com.hodo.practice.service.SalaryService;
import org.springframework.stereotype.Service;

/**
 * @anthor :zyy
 * @description:
 * @param:
 * @return :
 */
@Service
public class SalaryServiceImpl extends ServiceImpl implements SalaryService {



    @Override
    public Object findSalaryByName(String name) {
        return this.baseMapper.selectOne(new QueryWrapper());
    }
}
