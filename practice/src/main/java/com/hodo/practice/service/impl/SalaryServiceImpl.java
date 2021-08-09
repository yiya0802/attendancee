package com.hodo.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodo.practice.entity.po.TSalary;
import com.hodo.practice.service.SalaryService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @anthor :zyy
 * @description:通过姓名查找薪资
 * @param: name
 * @return :BigDecimal
 */
@Service
public class SalaryServiceImpl extends ServiceImpl implements SalaryService
{
    @Override
    public BigDecimal findSalaryByName(String name)
    {
        QueryWrapper<TSalary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Name", name);
        TSalary tSalary = (TSalary)baseMapper.selectOne(queryWrapper);
        return BigDecimal.valueOf(tSalary.getBascimoney())
            .subtract(new BigDecimal(tSalary.getLateMoney()))
            .add(new BigDecimal(tSalary.getBonus()))
            .subtract(new BigDecimal(tSalary.getAbsenteeismMoney()));
    }
    /**
     *
     * @description: 通过id查找薪资
     * @param: id
     * @return: BigDecimal
     * @date: 2021/8/9
     */


    @Override
    public BigDecimal findSalaryById(Integer id) {
        TSalary salary= (TSalary) this.baseMapper.selectById(id);
        return BigDecimal.valueOf(salary.getBascimoney())
                .subtract(new BigDecimal(salary.getLateMoney()))
                .add(new BigDecimal(salary.getBonus()))
                .subtract(new BigDecimal(salary.getAbsenteeismMoney()));
    }
}
