package com.xiao.boot.service.impl;

import java.util.Calendar;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiao.boot.bean.dto.AddStaff;
import com.xiao.boot.bean.po.R;
import com.xiao.boot.bean.po.Staff;
import com.xiao.boot.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.boot.bean.po.Department;
import com.xiao.boot.bean.po.Salary;
import com.xiao.boot.mapper.DepartmentMapper;
import com.xiao.boot.mapper.SalaryMapper;
import com.xiao.boot.service.SalaryService;

/**
 * @anthor :zyy
 * @description:
 * @Date 2021/8/12 16:02
 * @param:
 * @return :
 */
@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    SalaryMapper salaryMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    StaffMapper staffMapper;

    private long currents = 1;

    @Override
    public List<Salary> findSalaryByName(String name) {
        QueryWrapper<Salary>query=new QueryWrapper<>();
        query.eq("Name",name);
        List<Salary> salary=salaryMapper.selectList(query);
        return salary;
    }

    @Override
    public int addPostSalary(Department department1) {
        return  departmentMapper.insert(department1);
    }

    @Override
    public int deletePostSalary(String post) {
        QueryWrapper<Department> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("post",post);
        return departmentMapper.delete(queryWrapper);
    }

    @Override
    public Salary findSalaryById(Integer jobId) {
        QueryWrapper<Salary>queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userid",jobId);
        return salaryMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer updateSalary(Salary salary) {
        return salaryMapper.updateById(salary);
    }

    @Override
    public Integer addStaffSalary(AddStaff staff, Integer salary) {
        QueryWrapper<Salary> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userid",staff.getJobId());
        Salary salary1=salaryMapper.selectOne(queryWrapper);
        if (salary1!=null)
        {
            return 0;
        }
        Calendar calendar=Calendar.getInstance();
        Salary salary2=new Salary();
        salary2.setName(staff.getName());
        salary2.setBascimoney(salary);
        salary2.setUserid(staff.getJobId());
        salary2.setPost(staff.getPost());
        salary2.setMonth(calendar.get(Calendar.MONTH));
        return salaryMapper.insert(salary2);
    }

    @Override
    public R findPageSalary(Long current, Long size) {
        List<Salary>list=salaryMapper.selectList(null);
        Long Assize = Math.min(list.size(), size);
        if (current <= (list.size() / size)+1)
        {
            currents = current;
        }
        Page<Salary>page=salaryMapper.selectPage(new Page<>(currents, Assize),null);
        if (size > list.size())
        {
            return R.ok(page, "输入的大小太大，显示所有信息");
        }
        if (current > (list.size() / size)+1)
        {
            return R.failed("输入的页码太大，重新输入");
        }
        return page.getTotal()==0?R.failed("无信息"):R.ok(page,"输出信息");
    }

    @Override
    public Salary findSalByName(String name) {
        QueryWrapper<Salary>query=new QueryWrapper<>();
        query.eq("Name",name);
        Salary salary=salaryMapper.selectOne(query);
        return salary;
    }
}
