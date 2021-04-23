package com.eirapplets.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "框架成功构建测试控制器")
public class DemoController {

    @ApiOperation(value = "测试方法")
    @RequestMapping("sayHello")
    public String sayHello() {
        return "Success";
    }

}