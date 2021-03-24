package com.example.webtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * @author luotong
 * @description FreemarkerController
 * @date 2020/3/25 17:03
 */
@Controller
public class FreemarkerController {

    @RequestMapping("/getUser")
    public String StringgetUser(Integer id, Model model) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", "123");
        hashMap.put("age", "222");
        hashMap.put("sex", "1232");
        model.addAttribute("user", hashMap);
        return "getUser";

    }

}
