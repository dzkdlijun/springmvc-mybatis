package com.leeo.springdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lijun on 2016/12/16.
 */
@Controller
public class HomeController {
    public static final String SSO_URL = "http://www.mysso.com:8763";

    @RequestMapping(value = {"","/","home"})
    public String home(){
        return "index";
    }

}
