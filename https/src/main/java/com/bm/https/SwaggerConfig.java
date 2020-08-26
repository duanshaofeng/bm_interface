package com.bm.https;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {



    private ApiInfo apiInfo(String name, String description, String version) {
        return new ApiInfoBuilder().title(name).description(description).version(version).build();
    }

    /**
     * 按照路由来分组
     *
     * @return
     */

    @Bean
    public Docket restApi23() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("自来水统一受理测试接口")
                .apiInfo(apiInfo("zlstysl-api","自来水统一受理测试接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.controllerTest"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }


    @Bean
    public Docket web_api_admin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("GPSX-api", "高频事项", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/gpsx/**"))
                .build()
                .groupName("高频事项:接口文档V1.0")
                .pathMapping("/");
    }

    @Bean
    public Docket all() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("all-api", "数据共享平台所有接口", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/**"))
                .build()
                .groupName("所有接口文档V1.0")
                .pathMapping("/");
    }



    @Bean
    public Docket restApi22() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("省新冠测试接口")
                .apiInfo(apiInfo("sxgt-api","省新冠测试接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.controller.province.tyzy"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    @Bean
    public Docket restApi21() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("省新冠正式接口")
                .apiInfo(apiInfo("sxg-api","省新冠正式接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.accessToken.province.tyzy"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }


    @Bean
    public Docket restApi20() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("省住房和城乡建设厅测试接口")
                .apiInfo(apiInfo("szjt-api","省住房和城乡建设厅测试接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.controller.province.zhujian"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    @Bean
    public Docket restApi19() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("公共资源交易中心正式接口")
                .apiInfo(apiInfo("ggzyjyzx-api","公共资源交易中心正式接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.accessToken.ggzyjyzx"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    @Bean
    public Docket restApi18() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("民政部测试接口")
                .apiInfo(apiInfo("mzb-api","民政部测试接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.controller.province.minzhengbu"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    @Bean
    public Docket restApi17() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("公共资源交易中心测试接口")
                .apiInfo(apiInfo("ggzyjyzx-api","公共资源交易中心测试接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.controller.ggzyjyzx"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    @Bean
    public Docket restApi16() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("发改委正式接口")
                .apiInfo(apiInfo("fagaiwei-api","发改委正式接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.accessToken.fagaiwei"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    @Bean
    public Docket restApi15() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("发改委测试接口")
                .apiInfo(apiInfo("fagaiwei-api","发改委测试接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.controller.fagaiwei"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    @Bean
    public Docket restApi14() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("基础库正式接口")
                .apiInfo(apiInfo("jck-api","基础库正式接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.accessToken.jck"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    @Bean
    public Docket restApi13() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("基础库测试接口")
                .apiInfo(apiInfo("jck-api","基础库测试接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.controller.jck"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    @Bean
    public Docket restApi12() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("统一受理正式接口")
                .apiInfo(apiInfo("tysl-api","统一受理正式接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.controller.tysl"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("省里正式接口")
                .apiInfo(apiInfo("province-api","省里正式接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.accessToken.province"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    @Bean
    public Docket restApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("省里测试接口")
                .apiInfo(apiInfo("province-api","省里测试接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.controller.province"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    @Bean
    public Docket restApi3() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("不动产正式接口")
                .apiInfo(apiInfo("bdc-api","不动产正式接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.accessToken.bdc"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    @Bean
    public Docket restApi4() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("不动产测试接口")
                .apiInfo(apiInfo("bdc-api","不动产测试接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.controller.bdc"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }


    @Bean
    public Docket restApi5() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("自来水正式接口")
                .apiInfo(apiInfo("zilaishui-api","自来水正式接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.accessToken.chengguan.zilaishui"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    @Bean
    public Docket restApi6() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("自来水测试接口")
                .apiInfo(apiInfo("zilaishui-api","自来水测试接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.controller.zilaishui"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    @Bean
    public Docket restApi7() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("燃气测试接口")
                .apiInfo(apiInfo("ranqi-api","燃气测试接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.controller.ranqi"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    @Bean
    public Docket restApi8() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("燃气正式接口")
                .apiInfo(apiInfo("ranqi-api","燃气正式接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.accessToken.ranqi"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    @Bean
    public Docket restApi9() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("公积金正式接口1")
                .apiInfo(apiInfo("gongjijin-api","公积金正式接口1", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.accessToken"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }

    @Bean
    public Docket restApi10() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("公积金正式接口2")
                .apiInfo(apiInfo("gongjijin-api","公积金正式接口2", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.accessToken"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/gjj.*"))
                .build();
    }

    @Bean
    public Docket restApi11() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("公积金测试接口")
                .apiInfo(apiInfo("gongjijin-api","公积金测试接口", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bm.https.controller"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.regex("/gjjZ.*"))
                .build();
    }
}