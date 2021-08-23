package com.xiao.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiao.boot.bean.dto.PageStaff;
import com.xiao.boot.bean.po.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PageStaffMapper extends BaseMapper<PageStaff> {

    <T> Page<PageStaff> selectPageAndSalary(Page<PageStaff> tPage);
    
    Page<PageStaff> selectPageAndSalaryByStatus(Page<Object> objectPage,@Param("staff") Integer staff);

    Page<PageStaff> selectPageAndSalaryByName(Page<Object> objectPage,@Param("name") String name);
}
