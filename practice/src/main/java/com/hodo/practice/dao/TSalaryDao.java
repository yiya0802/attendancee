package com.hodo.practice.dao;

import com.hodo.practice.entity.TSalary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工薪资(TSalary)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-03 16:46:39
 */
public interface TSalaryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TSalary queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TSalary> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tSalary 实例对象
     * @return 对象列表
     */
    List<TSalary> queryAll(TSalary tSalary);

    /**
     * 新增数据
     *
     * @param tSalary 实例对象
     * @return 影响行数
     */
    int insert(TSalary tSalary);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TSalary> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TSalary> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TSalary> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TSalary> entities);

    /**
     * 修改数据
     *
     * @param tSalary 实例对象
     * @return 影响行数
     */
    int update(TSalary tSalary);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

