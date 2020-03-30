package com.example.webtest.dao;

import com.example.webtest.po.ElectronicInvoiceShop;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author luotong
 * @description TestMapper
 * activesql
 * @date 2020/3/26 14:20
 */
@Mapper
@Component
public interface TestMapper {
    final static Logger LOGGER = LoggerFactory.getLogger(TestMapper.class);

    @SelectProvider(type = BuilderActiveSql.class, method = "getqueryActiveSql")
    public List<ElectronicInvoiceShop> getInfo();

    @InsertProvider(type = BuilderActiveSql.class, method = "insertSql")
    public Integer insertDb();

    @UpdateProvider(type = BuilderActiveSql.class, method = "updateActiveSql")
    public Integer updateSql(ElectronicInvoiceShop electronicInvoiceShopResVo);

    /**
     * 成员内部类可以直接访问外部类的成员字段和成员方法，因为它是关联着一个外部类实例的
     * 静态内部类
     */
    public static class BuilderActiveSql {

        public String getqueryActiveSql() {
            return "select * from apollo_electronic_invoice_shop";
        }

        public String insertSql() {
            return "insert into apollo_electronic_invoice_shop (shop_name,identification_number,type)values ('123','222',2);";
        }

        public String updateActiveSql(final ElectronicInvoiceShop electronicInvoiceShopResVo) {
            StringBuffer sql = new StringBuffer();
            sql.append("update apollo_electronic_invoice_shop set ");
            if (StringUtils.isNotEmpty(electronicInvoiceShopResVo.getPlaceCode())) {
                sql.append("place_code = \'" + electronicInvoiceShopResVo.getPlaceCode() + "\',");
            }
            if (StringUtils.isNotEmpty(electronicInvoiceShopResVo.getShopName())) {
                sql.append("shop_name = \'" + electronicInvoiceShopResVo.getShopName() + "\',");
            }
            if (StringUtils.isNotEmpty(electronicInvoiceShopResVo.getIdentificationNumber())) {
                sql.append("identification_number = \'" + electronicInvoiceShopResVo.getIdentificationNumber() + "\'");
            }
            sql.append(" where id = " + electronicInvoiceShopResVo.getId());
            LOGGER.info("updateActiveSql sql:{}", sql);
            return sql.toString();
        }
    }
}
