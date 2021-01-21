package com.zxj.inaction.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zhaoxiujie
 * @Date 2021/1/21 17:27
 * @Description: TODO
 */

@Controller
@Slf4j
public class IndexController extends BaseController {
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
