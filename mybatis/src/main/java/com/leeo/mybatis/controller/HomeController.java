package com.leeo.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lijun on 2016/5/17.
 */
@Controller
public class HomeController {
    public static final String SSO_URL = "http://www.mysso.com:8763";
    @RequestMapping(value = {"","/","home"})
    @ResponseBody
    public String home(){
        return "home index";
    }
}
