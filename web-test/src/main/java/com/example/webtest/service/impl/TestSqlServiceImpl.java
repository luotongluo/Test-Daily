package com.example.webtest.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.webtest.dao.TestMapper;
import com.example.webtest.dao.TestMapper1;
import com.example.webtest.po.ElectronicInvoiceShopResVo;
import com.example.webtest.service.TestSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author luotong
 * @description TestSqlServiceImpl
 * @date 2020/3/26 14:20
 */
@Service
public class TestSqlServiceImpl implements TestSqlService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public String testActiveSql(Map map) {
        ElectronicInvoiceShopResVo electronicInvoiceShopResVo = null;
        electronicInvoiceShopResVo =new ElectronicInvoiceShopResVo("shopname","indet_name","placr-code",2,12);

        Integer integer = testMapper.updateSql(electronicInvoiceShopResVo);
        return String.valueOf(integer);
    }

    @Override
    public void getdate() {

    }

    @Override
    public String insert(ElectronicInvoiceShopResVo electronicInvoiceShopResVo) {
        return null;
    }

    @Override
    public Integer getDate() {
//        return testMapper1.getDate();
        return null;
    }
}
