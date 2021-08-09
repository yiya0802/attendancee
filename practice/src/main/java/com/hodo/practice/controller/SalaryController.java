package com.hodo.practice.controller;

import com.hodo.practice.constant.CommonConstants;
import com.hodo.practice.entity.R;
import com.hodo.practice.service.SalaryService;
import com.hodo.practice.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @anthor :zyy
 * @description: 查看自己的薪资
 * @param: :name id
 * @return :R
 */
@Controller
@RequestMapping("/salary")
@AllArgsConstructor
@Getter
@Setter
public class SalaryController
{
    private SalaryService salaryService;
    
    @RequestMapping("mySalary")
    private R MySalaryByName(String name)
    {
        if (StringUtils.isEmpity(name))
        {
            return R.failed(CommonConstants.NAMENOTEXITS);
        }
        if (null==salaryService.findSalaryByName(name))
        {
            return R.failed(CommonConstants.NOSALARYRECORDS);
        }
        return R.ok(salaryService.findSalaryByName(name),CommonConstants.FINDSALARYSUCCESS);
    }
    @PostMapping("")
    private R MySalaryById(Integer id)
    {
        if (null==id)
        {
            return R.failed();
        }
        if (null==salaryService.findSalaryById(id))
        {
            return R.failed(CommonConstants.NOSALARYRECORDS);

        }
        return R.ok(salaryService.findSalaryById(id),CommonConstants.FINDSALARYSUCCESS);

    }
}
