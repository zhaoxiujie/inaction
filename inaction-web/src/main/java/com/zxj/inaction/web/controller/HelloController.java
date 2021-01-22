package com.zxj.inaction.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhaoxiujie
 * @Date 2021/1/22 11:18
 * @Description: TODO
 */
@Slf4j
@RestController
public class HelloController {
    @Value("${system.name}")
    private String systemName;
    @Value("${system.author}")
    private String systemAuthor;
    @Value("${system.env}")
    private String systemEnv;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello , Spring Boot! This is " + systemName + "_" + systemEnv + " by " + systemAuthor;
    }
}
