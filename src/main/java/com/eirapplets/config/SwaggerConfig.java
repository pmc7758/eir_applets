package com.eirapplets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author pangjian
 * @ClassName SwaggerConfig
 * @Description 接口文档配置类
 * @date 2021/4/15 13:50
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    /**
     * @Description: 创建Docke类型的对象，并交给spring容器托管，Docket是Swagger中的全局配置对象
     * @return springfox.documentation.spring.web.plugins.Docket
     * @date 2021/4/15 13:55
    */
    @Bean
    public Docket docket(){
        // 创建哪一个版本的全局配置对象
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        // API帮助文档的描述信息对象
        ApiInfo apiInfo =
            new ApiInfoBuilder()
                .contact(
                        new Contact(
                                "软件工程12组-庞坚", // 发布的作者
                                "null",// 发布企业的网站
                                "614039502@qq.com"// 发布者的电子邮箱
                        )
                )
                .title("疫情信息上报系统接口文档") // 接口文档标题
                .description("用来疫情信息上报系统前后端开发人员接口交互的的文档") // 接口文档描述
                .version("1.0") // 接口文档版本号
                .build();

        // 给docket上下文配置api描述信息
        docket.apiInfo(apiInfo);

        // 构建规则
        docket
                .select() // 获取Docket中的选择器。返回 ApiSelectorBuilder。构建选择器
                .apis(RequestHandlerSelectors.basePackage("com.eirapplets.controller")) // 设定扫描哪个包（包含子包）
                .build();

        return docket.groupName("pangjian");
    }


}
