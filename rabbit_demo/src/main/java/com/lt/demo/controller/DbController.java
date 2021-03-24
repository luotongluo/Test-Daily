package com.lt.demo.controller;

import com.lt.demo.service.DbSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tong.luo
 * @description DbController
 * @date 2021/1/22 15:56
 */
@RestController
@RequestMapping("db")
public class DbController {

    @Autowired
    private DbSerice dbSerice;

    @RequestMapping("testInsert")
    public String testInsert() {
        this.dbSerice.testInsert();
        return "success";
    }
}
