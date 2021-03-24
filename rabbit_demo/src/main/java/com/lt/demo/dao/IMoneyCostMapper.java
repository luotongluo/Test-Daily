package com.lt.demo.dao;

import com.lt.demo.bean.IMoneyCost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (IMoneyCost)表数据库访问层
 *
 * @author tong.luo
 * @since 2021-01-22 16:44:08
 */
@Mapper
public interface IMoneyCostMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    IMoneyCost queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<IMoneyCost> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param iMoneyCost 实例对象
     * @return 对象列表
     */
    List<IMoneyCost> queryAll(IMoneyCost iMoneyCost);

    /**
     * 新增数据
     *
     * @param iMoneyCost 实例对象
     * @return 影响行数
     */
    int insert(IMoneyCost iMoneyCost);

    /**
     * 修改数据
     *
     * @param iMoneyCost 实例对象
     * @return 影响行数
     */
    int update(IMoneyCost iMoneyCost);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}