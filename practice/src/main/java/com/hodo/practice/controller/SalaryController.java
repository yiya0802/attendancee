package com.hodo.practice.controller;

import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.R;
import com.hodo.practice.service.SalaryService;
import com.hodo.practice.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @anthor :zyy
 * @description: 查看自己的薪资
 * @param:
 * @return :
 */
@Controller
@RequestMapping("/salary")
@AllArgsConstructor
@Getter
@Setter
public class SalaryController
{
    private SalaryService salaryService;
    
    @RequestMapping("MySalary")
    private R MySalary(Integer id, String name)
    {
        if (StringUtils.isEmpity(name))
        {
            return R.failed(CommonConstants.NAMENOTEXITS);
        }
        salaryService.findSalaryByName(name);

        
        return R.ok(salaryService,CommonConstants.FINDSALARYSUCCESS);
    }
}
