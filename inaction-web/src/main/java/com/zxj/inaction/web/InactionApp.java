package com.zxj.inaction.web;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 启动器，继承类SpringBootServletInitializer，可以使用外置的tomcat启动
 * @Author zhaoxiujie1
 * @CreateDate: 2020/08/03
 */
@Slf4j
@ImportResource(value = {"classpath:spring-config.xml"})
@SpringBootApplication(scanBasePackages = {"com.zxj.inaction"})
public class InactionApp extends SpringBootServletInitializer {

    private final static Logger logger = LoggerFactory.getLogger(InactionApp.class);



    public static void main(String[] args) {

        logger.info("-----------------");
        SpringApplication.run(InactionApp.class, args);
        logger.info("*********");
        log.info("############################################");
        log.error("############   inaction启动完毕  ############");
        log.error("############################################");
    }

    /**
     * 重写类SpringBootServletInitializer的configure方法
     *
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(InactionApp.class);
    }
}
