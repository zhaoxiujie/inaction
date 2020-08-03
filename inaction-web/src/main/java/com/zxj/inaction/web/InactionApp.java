package com.zxj.inaction.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 启动器
 * @Author zhaoxiujie1
 * @CreateDate: 2020/08/03
 */
@SpringBootApplication(scanBasePackages = {"com.zxj.inaction"})
@PropertySources({
        @PropertySource("classpath:properties/application.properties")
})
@ImportResource({
        "classpath:spring/spring-config-jsf.xml"
})
@RestController
public class InactionApp {

    @Value("${system.name}")
    private String systemName;
    @Value("${system.author}")
    private String systemAuthor;

    @RequestMapping("/")
    public String index() {
        return "Hello , Spring Boot! This is " + systemName + " by "+systemAuthor;
    }

    public static void main(String[] args) {
        SpringApplication.run(InactionApp.class, args);
    }

}
