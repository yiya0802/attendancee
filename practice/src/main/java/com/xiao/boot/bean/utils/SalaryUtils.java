package com.xiao.boot.bean.utils;

import com.xiao.boot.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @anthor :zyy
 * @description:
 * @Date 2021/8/27 15:02
 * @param:
 * @return :
 */

public class SalaryUtils {
    @Autowired
    SalaryService salaryService;
    public static BigDecimal realSalary(Integer earlyLeave, Integer lateMoney, Integer bascimoney, Integer bonus) {
    BigDecimal early=new BigDecimal(earlyLeave);
    BigDecimal late=new BigDecimal(lateMoney);
    BigDecimal basic=new BigDecimal(bascimoney);
    BigDecimal bon=new BigDecimal(bonus);
    return basic.add(bon).subtract(early).subtract(late);
    }
}
