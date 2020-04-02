package com.example.webtest.dao;

import com.example.webtest.pojo.ApolloElectronicInvoiceShop;

public interface ApolloElectronicInvoiceShopMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApolloElectronicInvoiceShop record);

    int insertSelective(ApolloElectronicInvoiceShop record);

    ApolloElectronicInvoiceShop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApolloElectronicInvoiceShop record);

    int updateByPrimaryKey(ApolloElectronicInvoiceShop record);
}