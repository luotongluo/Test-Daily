package com.lt.demo.mapper;

import com.lt.demo.bean.TestTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * test_table(TestTable)表数据库访问层
 *
 * @author tong.luo
 * @since 2021-01-22 15:53:13
 */
@Mapper
public interface TestTableMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TestTable queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TestTable> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param testTable 实例对象
     * @return 对象列表
     */
    List<TestTable> queryAll(TestTable testTable);

    /**
     * 新增数据
     *
     * @param testTable 实例对象
     * @return 影响行数
     */
    int insert(TestTable testTable);

    /**
     * 修改数据
     *
     * @param testTable 实例对象
     * @return 影响行数
     */
    int update(TestTable testTable);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}