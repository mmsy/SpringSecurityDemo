package com.nfky.datacenter.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by lyr on 2017/6/8.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.nfky.datacenter", "com.nfky.mdc"})
public class DataCenterApiApp extends SpringBootServletInitializer{

    public final static String API_VERSION = "/api/v1.0";

    public static void main(String[] args) {
        SpringApplication.run(DataCenterApiApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(DataCenterApiApp.class);
        return super.configure(builder);
    }
}
