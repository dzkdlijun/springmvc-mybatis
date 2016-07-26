package com.leeo.springdata.controller;

import com.leeo.springdata.domain.BaseDomain;
import com.leeo.springdata.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lijun on 2016/7/26.
 */
@Controller
@RequestMapping("/test/")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "get",method = RequestMethod.GET)
    public BaseDomain getTest(@RequestParam String username,@RequestParam String password){
        return new User(username,password);
    }

    @ResponseBody
    @RequestMapping(value = "post",method = RequestMethod.POST)
    public BaseDomain postTest(@RequestBody User user){
        return user;
    }
}
