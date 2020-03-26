package com.example.webtest.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.webtest.dao.TestMapper1;
import com.example.webtest.po.ElectronicInvoiceShopResVo;
import com.example.webtest.service.TestSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author luotong
 * @description TestSqlServiceImpl
 * @date 2020/3/26 14:20
 */
@Service
public class TestSqlServiceImpl implements TestSqlService {
//    @Autowired
//    private TestMapper1 testMapper1;

    @Override
    public String testActiveSql(Map map) {
        return "select * from apollo_electronic_invoice_shop ";
    }

    public void getdate() {
//        Object info = testMapper.getInfo();
//        String s = JSON.toJSONString(info);
//        System.out.println(s);
    }

    @Override
    public String insert(ElectronicInvoiceShopResVo electronicInvoiceShopResVo) {
        String sql = "insert into apollo_electronic_invoice_shop (shop_name,identification_number,type)values ('123','222',2);";
        return sql;
    }

    @Override
    public Integer getDate() {
//        return testMapper1.getDate();
        return null;
    }
}
