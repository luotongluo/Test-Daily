package com.lt.demo.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * test_table(TestTable)实体类
 *
 * @author tong.luo
 * @since 2021-01-22 15:53:10
 */
public class TestTable implements Serializable {
    private static final long serialVersionUID = -20377456545187590L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * name
     */
    private String name;
    /**
     * date_time
     */
    private Date createTime;
    /**
     * age
     */
    private Integer age;
    /**
     * create_id
     */
    private String createId;
    /**
     * update_time
     */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}