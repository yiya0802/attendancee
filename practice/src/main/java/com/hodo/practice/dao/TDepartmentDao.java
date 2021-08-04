package com.hodo.practice.dao;

import com.hodo.practice.entity.TDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门岗位表(TDepartment)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-03 16:46:38
 */
public interface TDepartmentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param departmentid 主键
     * @return 实例对象
     */
    TDepartment queryById(Integer departmentid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TDepartment> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tDepartment 实例对象
     * @return 对象列表
     */
    List<TDepartment> queryAll(TDepartment tDepartment);

    /**
     * 新增数据
     *
     * @param tDepartment 实例对象
     * @return 影响行数
     */
    int insert(TDepartment tDepartment);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TDepartment> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TDepartment> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TDepartment> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TDepartment> entities);

    /**
     * 修改数据
     *
     * @param tDepartment 实例对象
     * @return 影响行数
     */
    int update(TDepartment tDepartment);

    /**
     * 通过主键删除数据
     *
     * @param departmentid 主键
     * @return 影响行数
     */
    int deleteById(Integer departmentid);

}

