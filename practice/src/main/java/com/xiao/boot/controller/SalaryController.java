package com.xiao.boot.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.xiao.boot.bean.po.Department;
import com.xiao.boot.bean.po.R;
import com.xiao.boot.bean.po.Salary;
import com.xiao.boot.service.DepartmentService;
import com.xiao.boot.service.SalaryService;

/**
 * @anthor :zyy
 * @description: 薪资
 * @Date 2021/8/12 16:00
 * @param:
 * @return :
 */
@Controller
@RequestMapping("/salary")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/mysalary")
    @ResponseBody
    /**
     *
     * @description: 通过名字查询薪资
     * @param: [name]
     * @return: com.xiao.boot.bean.po.R
     * @date: 2021/8/16
     */

    public R MySalaryByName(@Param("name") String name)
    {
        if (StringUtils.isEmpty(name))
        {
            return R.failed("名字为空");
        }
       List<Salary> salary= salaryService.findSalaryByName(name);
       if (salary==null)
       {
           return R.failed();
       }
       return R.ok(salary);
    }

    /**
     *
     * @description: 岗位薪资 新增 修改 删除
     * @param:
     * @return:
     * @date: 2021/8/13
     */
    @GetMapping("/postsalary")
    @ResponseBody
    public R postSalary(String post)
    {
        List<Department> department=departmentService.findSalaryByPost(post);
        if (department.size()==0)
        {
            return R.failed("查无此岗位");
        }
        return R.ok(department,"岗位薪资");
    }
    @GetMapping("/addpostsalary")
    @ResponseBody
    /**
     *
     * @description: 新增岗位薪资
     * @param: [department]
     * @return: com.xiao.boot.bean.po.R
     * @date: 2021/8/16
     */

    public R addPostSalary(Department department)
    {
        if (StringUtils.isEmpty(department.getDepartmentId()) || StringUtils.isEmpty(department.getPost()
        )  || StringUtils.isEmpty(department.getName() ) ||department.getSalary()==null)
        {
            return R.failed("不能为空!");
        }
        Department temp=departmentService.findDepartmentById(department.getDepartmentId());
        if (temp!=null)
        {
            return R.failed("已经存在此部门，不能重复插入");
        }
        Department department1=new Department();
        BeanUtils.copyProperties(department,department1);
        int num=salaryService.addPostSalary(department1);
        if (num==0)
        {
            return R.failed("新增失败");
        }
        return R.ok(num);
    }

    @GetMapping("/deletepostsalary")
    @ResponseBody
    /**
     *
     * @description: 删除岗位薪资
     * @param: [post]
     * @return: com.xiao.boot.bean.po.R
     * @date: 2021/8/16
     */

    public R deletePostSalary(String post)
    {
        if (StringUtils.isEmpty(post))
        {
            return R.failed("岗位不能为空");
        }
        int num=salaryService.deletePostSalary(post);
        if (num==0)
        {
            return R.failed("删除失败");
        }
        return R.ok(num);

    }

    @GetMapping("/findpagesalary")
    @ResponseBody
/**
 *
 * @description: 返回所有薪资
 * @param: [current, size]
 * @return: com.xiao.boot.bean.po.R
 * @date: 2021/8/24
 */

    public R findPageSalary(Long current,Long size)
    {
        if (current==null || size==null)
        {
            return R.failed("current 和 size不能为空");
        }
        return salaryService.findPageSalary(current,size);
    }



}
