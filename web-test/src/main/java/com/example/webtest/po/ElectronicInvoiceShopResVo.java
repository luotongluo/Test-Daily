package com.example.webtest.po;

/**
 * @Author: LT
 * @Date: 2020/2/18 14:15
 * @Description:
 * @Version 1.0
 */
public class ElectronicInvoiceShopResVo {
    private Integer id;
    private String shopName;
    private String identificationNumber;
    private String placeCode;
    private Integer type;

    public ElectronicInvoiceShopResVo() {
    }

    public ElectronicInvoiceShopResVo(String shopName, String identificationNumber, String placeCode, Integer type,Integer id) {
        this.shopName = shopName;
        this.identificationNumber = identificationNumber;
        this.placeCode = placeCode;
        this.type = type;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
