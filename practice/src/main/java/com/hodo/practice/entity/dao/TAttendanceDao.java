package com.hodo.practice.entity.dao;

import com.hodo.practice.entity.po.TAttendance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 缺勤表(TAttendance)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-03 16:46:37
 */
public interface TAttendanceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param attennum 主键
     * @return 实例对象
     */
    TAttendance queryById(Integer attennum);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TAttendance> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tAttendance 实例对象
     * @return 对象列表
     */
    List<TAttendance> queryAll(TAttendance tAttendance);

    /**
     * 新增数据
     *
     * @param tAttendance 实例对象
     * @return 影响行数
     */
    int insert(TAttendance tAttendance);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TAttendance> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TAttendance> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TAttendance> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<TAttendance> entities);

    /**
     * 修改数据
     *
     * @param tAttendance 实例对象
     * @return 影响行数
     */
    int update(TAttendance tAttendance);

    /**
     * 通过主键删除数据
     *
     * @param attennum 主键
     * @return 影响行数
     */
    int deleteById(Integer attennum);

}

