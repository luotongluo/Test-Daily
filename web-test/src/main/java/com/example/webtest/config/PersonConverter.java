package com.example.webtest.config;

import com.example.webtest.po.ElectronicInvoiceShop;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author tong.luo
 * @description PersonConverter
 * @date 2020/8/10 22:44
 */
public interface PersonConverter {
    PersonConverter INSTANCE = Mappers.getMapper(PersonConverter.class);

    @Mappings(@Mapping(source = "name", target = "userName"))
    ElectronicInvoiceShop do2dto(ElectronicInvoiceShop person);
}
