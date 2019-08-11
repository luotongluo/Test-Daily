package com.lt.demo.bean;

import java.io.Serializable;

/**
 * @Author: LT
 * @Date: 2019/4/9 16:38
 * @Description:
 * @Version 1.0
 */

public class UserEntity implements Serializable {
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
