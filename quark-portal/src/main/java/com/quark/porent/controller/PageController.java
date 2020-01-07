package com.quark.porent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author LHR
 * Create By 2017/8/23
 */
@Controller
public class PageController {

    @RequestMapping("/index")
    public String indexPage() {
        //System.out.println("首页");
        return "index";

    }

    @RequestMapping("/label")
    public String labelhome() {
        return "label/home";
    }

}
