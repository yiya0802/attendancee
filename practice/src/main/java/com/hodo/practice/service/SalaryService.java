package com.hodo.practice.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface SalaryService {
    /**
     * @anthor :zyy
     * @description:通过姓名查找薪资
     * @param: name
     * @return :BigDecimal
     */
    BigDecimal findSalaryByName(String name);
    /**
     *
     * @description: 通过id查找薪资
     * @param: id
     * @return: BigDecimal
     * @date: 2021/8/9
     */
    BigDecimal findSalaryById(Integer id);
}
