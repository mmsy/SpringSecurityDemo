package com.nfky.datacenter.api.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 *
 * Created by lyr on 2017/6/8.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public static ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("数据中心API")
                .description("数据中心API")
                .version("1.0.0")
                .build();

        return apiInfo;
    }

    @Bean
    public static Docket docket(ApiInfo apiInfo) {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        return docket.groupName("api")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build().apiInfo(apiInfo);
    }

}
