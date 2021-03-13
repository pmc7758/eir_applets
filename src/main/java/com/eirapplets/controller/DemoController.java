package com.eirapplets.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pangjian
 * @ClassName DemoController
 * @Description 测试
 * @date 2021/3/13 22:17
 */
@RequestMapping
@RestController
public class DemoController {

    @RequestMapping("sayHello")
    public String sayHello() {
        return "Success";
    }

}