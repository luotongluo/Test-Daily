package com.lt.demo.service.impl;

import java.util.Date;

import com.lt.demo.bean.TestTable;
import com.lt.demo.mapper.TestTableMapper;
import com.lt.demo.service.DbSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tong.luo
 * @description DbSericeImpl
 * @date 2021/1/22 15:56
 */
@Service("DbSerice")
public class DbSericeImpl implements DbSerice {
    @Autowired
    private TestTableMapper tableMapper;

    /**
     * testInsert
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testInsert() {
        TestTable testTable = new TestTable();
        testTable.setName("test");
        testTable.setCreateTime(new Date());
        testTable.setAge(221);
        testTable.setCreateId("223");
        testTable.setUpdateTime(new Date());

        this.tableMapper.insert(testTable);
    }
}
