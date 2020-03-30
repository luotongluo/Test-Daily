package com.example.webtest.service;

import com.example.webtest.po.ElectronicInvoiceShop;

import java.util.Map;

/**
 * @author luotong
 * @description TestSqlService
 * @date 2020/3/26 14:19
 */
public interface TestSqlService {

    public String testActiveSql(Map map);

    public void getdate();

    public String insert(ElectronicInvoiceShop electronicInvoiceShopResVo);

    public Integer getDate();
}
