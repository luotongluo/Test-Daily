package com.example.webtest.service.impl;

import com.example.webtest.dao.TestMapper;
import com.example.webtest.po.ElectronicInvoiceShop;
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
    @Autowired
    private TestMapper testMapper;

    @Override
    public String testActiveSql(Map map) {
        ElectronicInvoiceShop electronicInvoiceShopResVo = null;
        electronicInvoiceShopResVo =new ElectronicInvoiceShop("shopname","indet_name","placr-code",2,12);

        Integer integer = testMapper.updateSql(electronicInvoiceShopResVo);
        return String.valueOf(integer);
    }

    @Override
    public void getdate() {

    }

    @Override
    public String insert(ElectronicInvoiceShop electronicInvoiceShopResVo) {
        return null;
    }

    @Override
    public Integer getDate() {
//        return testMapper1.getDate();
        return null;
    }
}
