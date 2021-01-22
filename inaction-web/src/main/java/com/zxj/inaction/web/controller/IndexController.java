package com.zxj.inaction.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zhaoxiujie
 * @Date 2021/1/21 17:27
 * @Description: TODO
 */
@Slf4j
@Controller
public class IndexController extends BaseController {

    @RequestMapping(name = "/index")
    public String index() {
        return "index";
    }
}
