package com.example.webtest.dao;

import com.example.webtest.service.impl.TestSqlServiceImpl;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

/**
 * @author luotong
 * @description TestMapper
 * @date 2020/3/26 14:20
 */
public interface TestMapper1 {
    Integer getDate();

//    @InsertProvider(type = TestSqlServiceImpl.class,method = "testActiveSql")
//    public void insert();
}
